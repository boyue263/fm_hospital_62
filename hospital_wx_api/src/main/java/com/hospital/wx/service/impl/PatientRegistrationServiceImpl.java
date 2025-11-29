package com.hospital.wx.service.impl;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.NumberUtil;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.hospital.wx.dao.*;
import com.hospital.wx.exception.WxErrorCode;
import com.hospital.wx.pojo.PatientRegistration;
import com.hospital.wx.service.DoctorService;
import com.hospital.wx.service.PatientRegistrationService;
import com.hospital.wx.service.PaymentService;
import com.hospital.wx.utils.Constants;
import com.hospital.wx.utils.PageUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.hospital.wx.utils.Constants.WORK_PLAN_SCHEDULE_KEY;

@Service
@Slf4j
public class PatientRegistrationServiceImpl implements PatientRegistrationService {
   @Autowired
   PatientRegistrationDao patientRegistrationDao;
   @Autowired
   PatientUserInfoDao patientUserInfoDao;
   @Autowired
    PatientFaceDao patientFaceDao;
   @Autowired
   RedisTemplate redisTemplate;
   @Autowired
   PatientUserDao patientUserDao;
   @Autowired
    PaymentService paymentService;
    @Autowired
    DoctorWorkPlanDao doctorWorkPlanDao;
    @Autowired
    DoctorWorkPlanScheduleDao doctorWorkPlanScheduleDao;
    private String WORK_PLAN_SCHEDULE_KEY = Constants.WORK_PLAN_SCHEDULE_KEY;
    private String  REGISTRATION_PAYMENT_ = Constants.REGISTRATION_PAYMENT;
    private String notifyUrl = "/patient/registration/handleWechatPaymentCallback";



    @Override
    public String checkRegisterCondition(Map param) {
        // 获取当前系统时间
        param.put("today", DateUtil.today());

        // 条件1: 检查当天用户是否已经挂号3次以上
        if (isRegistrationLimitReached(param)) {
            return WxErrorCode.DAILY_REGISTRATION_LIMIT_REACHED.getMessage().toString();
        }

        // 条件2: 检查当天是否已经挂过该门诊的号
        if (hasRegisteredForRoom(param)) {
            return WxErrorCode.ROOM_ALREADY_REGISTERED.getMessage().toString();
        }

//        // 条件3: 检查是否存在人脸面部数据
//        if (!hasFaceModel(param)) {
//            return WxErrorCode.FACE_MODEL_NOT_FOUND.getMessage().toString();
//        }
//
//        // 条件4: 检查今日是否存在挂号用户的面部识别记录
//        if (!checkFaceAuthRecordForToday(param)) {
//            return WxErrorCode.NO_FACE_VERIFICATION_RECORD.getMessage().toString();
//        }

        // 所有条件满足
        return WxErrorCode.REGISTRATION_ELIGIBLE.getMessage().toString();
    }
    private boolean isRegistrationLimitReached(Map<String, Object> param) {
        long count = patientRegistrationDao.getTodayRegistrationCount(param);
        return count >= 3;
    }
    private boolean hasRegisteredForRoom(Map<String, Object> param) {
        Integer id = patientRegistrationDao.hasRegisteredToday(param);
        return id != null;
    }
    private boolean hasFaceModel(Map<String, Object> param) {
        int userId = MapUtil.getInt(param, "userId");
        Boolean bool = patientUserInfoDao.existsFaceModel(userId);
        return bool != null && bool;
    }
    private boolean checkFaceAuthRecordForToday(Map<String, Object> param) {
        return patientFaceDao.checkFaceAuthRecordForToday(param) != null;
    }

    @Override
    @Transactional
    public HashMap registerMedicalAppointment(Map param) {
        int workPlanId = MapUtil.getInt(param, "workPlanId");
        int scheduleId = MapUtil.getInt(param, "scheduleId");
        String key = WORK_PLAN_SCHEDULE_KEY + scheduleId;
        if (!Boolean.TRUE.equals(redisTemplate.hasKey(key))) {
            return null;
        }

        try {
            int userId = MapUtil.getInt(param, "userId");
            HashMap<String, Object> userMap = patientUserDao.selectOpenIdByUserId(userId);
            String openId = MapUtil.getStr(userMap, "openId");
            int patientCardId = MapUtil.getInt(userMap, "patientCardId");
            String amountStr = MapUtil.getStr(param, "amount");
            int total = NumberUtil.mul(amountStr, "100").intValue();
            String outTradeNo = IdUtil.simpleUUID().toUpperCase();
            ObjectNode objectNode = createPaymentOrder(outTradeNo, openId, total);
            String prepayId = objectNode.get("prepay_id").textValue();
            saveRegistrationRecord(param, patientCardId, outTradeNo, prepayId);
            updateAppointmentCount(workPlanId, scheduleId);
            // 缓存付款信息
            cachePaymentInfo(outTradeNo, workPlanId, scheduleId);
            return buildPaymentResponse(outTradeNo, objectNode);
        } catch (Exception e) {
            // 记录错误日志，包含异常信息和关键参数
            log.error("微信支付挂号异常， 错误信息: {}", e.getMessage(), e);
            // 回滚医生排班的预约名额
            rollbackScheduleSlot(key);
            throw e;
        }
    }
    private ObjectNode createPaymentOrder(String outTradeNo, String openId, int total) {
        //学习过程中测试代码调用mockCreatePaymentOrder，实际开发调用createPaymentOrder
        return paymentService.mockCreatePaymentOrder(outTradeNo, openId, total, "挂号费", notifyUrl, null);
    }
    private void saveRegistrationRecord(Map<String, Object> param, int patientCardId, String outTradeNo, String prepayId) {
        PatientRegistration entity = new PatientRegistration();
        // 设置排班计划 ID（医院的工作计划 ID）
        entity.setWorkPlanId(MapUtil.getInt(param, "workPlanId"));
        // 设置医生的排班 ID（医生当天的排班记录）
        entity.setDoctorScheduleId(MapUtil.getInt(param, "scheduleId"));
        // 绑定患者就诊卡 ID（唯一标识患者）
        entity.setPatientCardId(patientCardId);
        // 设置医生 ID
        entity.setDoctorId(MapUtil.getInt(param, "doctorId"));
        // 设置诊室 ID
        entity.setDeptSubId(MapUtil.getInt(param, "deptSubId"));
        // 设置挂号日期（格式如 YYYY-MM-DD）
        entity.setDate(MapUtil.getStr(param, "date"));
        // 设置预约时间段（slot 表示时间段编号）
        entity.setSlot(MapUtil.getInt(param, "slot"));
        // 设置挂号费用（转换为 BigDecimal 类型）
        entity.setAmount(new BigDecimal(MapUtil.getStr(param, "amount")));
        //挂号单流水号（UUID）
        entity.setOutTradeNo(outTradeNo);
        //微信付款单预支付ID，prepay_id 用于后续支付确认
        entity.setPrepayId(prepayId);
        // 将挂号信息插入数据库
        patientRegistrationDao.insert(entity);
    }

    private void updateAppointmentCount(int workPlanId, int scheduleId) {
        doctorWorkPlanDao.updateNumById(Map.of("id", workPlanId, "n", 1));
        doctorWorkPlanScheduleDao.updateNumById(Map.of("id", scheduleId, "n", 1));
    }
    private void cachePaymentInfo(String outTradeNo, int workPlanId, int scheduleId) {
        String redisKey = REGISTRATION_PAYMENT_ + outTradeNo;
        redisTemplate.opsForHash().putAll(redisKey, Map.of(
                "workPlanId", workPlanId,
                "scheduleId", scheduleId,
                "outTradeNo", outTradeNo
        ));
        //实际开发
//        redisTemplate.expireAt(redisKey, new DateTime().offset(DateField.MINUTE, 6));
        //测试代码
        redisTemplate.expireAt(redisKey, new DateTime().offset(DateField.MINUTE, 1));

    }
    private HashMap<String, String> buildPaymentResponse(String outTradeNo, ObjectNode objectNode) {
        return new HashMap<>(Map.of(
                "outTradeNo", outTradeNo,
                "prepayId", objectNode.get("prepay_id").asText(),
                "timeStamp", objectNode.get("timeStamp").asText(),
                "nonceStr", objectNode.get("nonceStr").asText(),
                "package", objectNode.get("package").asText(),
                "signType", objectNode.get("signType").asText(),
                "paySign", objectNode.get("paySign").asText()
        ));
    }

    @Override
    @Transactional
    public void updatePaymentStatus(Map param) {
        String outTradeNo = MapUtil.getStr(param, "outTradeNo");
        //挂号费支付成功，删除Redis中对应的挂号缓存
        redisTemplate.delete(Constants.REGISTRATION_PAYMENT + outTradeNo);
        //更新挂号单付款状态为已付款（payment_status = 2）
        patientRegistrationDao.updatePaymentStatus(param);
    }
    private void rollbackScheduleSlot(String key) {
        if (Boolean.TRUE.equals(redisTemplate.hasKey(key))) {
            redisTemplate.opsForHash().increment(key, "num", -1);
        }
    }
    @Override
    public PageUtils selectRegistrationByPage(Map param) {
        ArrayList list = null;
        long count = patientRegistrationDao.selectRegistrationCount(param);
        if (count > 0) {
            list = patientRegistrationDao.selectRegistrationByPage(param);
        } else {
            list = new ArrayList();
        }
        int page = MapUtil.getInt(param, "page");
        int length = MapUtil.getInt(param, "length");
        PageUtils pageUtils = new PageUtils(list, count, page, length);
        return pageUtils;
    }

    @Override
    public HashMap selectRegistrationInfo(Map param) {
        return patientRegistrationDao.selectRegistrationInfo(param);
    }
}
