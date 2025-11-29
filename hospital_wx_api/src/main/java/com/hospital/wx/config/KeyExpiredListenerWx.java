package com.hospital.wx.config;

import com.hospital.wx.dao.DoctorWorkPlanDao;
import com.hospital.wx.dao.DoctorWorkPlanScheduleDao;
import com.hospital.wx.dao.PatientRegistrationDao;
import com.hospital.wx.utils.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.KeyExpirationEventMessageListener;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Slf4j
@Component
public class KeyExpiredListenerWx extends KeyExpirationEventMessageListener {

    @Autowired
    private PatientRegistrationDao patientRegistrationDao;

    @Autowired
    private DoctorWorkPlanScheduleDao doctorWorkPlanScheduleDao;

    @Autowired
    private DoctorWorkPlanDao doctorWorkPlanDao;

    @Autowired
    private RedisTemplate redisTemplate;

    public KeyExpiredListenerWx(RedisMessageListenerContainer listenerContainer) {
        super(listenerContainer);
    }

    /**
     * Redis里面缓存数据过期之后会自动调用onMessage函数，在该函数进行具体的逻辑代码编写即可。
     * */
    @Override
    @Transactional
    public void onMessage(Message message, byte[] pattern) {
        // 获取 Redis 过期数据的 Key
        String key = message.toString();

        // 判断过期数据是否为挂号单
        if (!key.startsWith(Constants.REGISTRATION_PAYMENT)) {
            return;
        }

        try {
            // 从 Key 中提取挂号单流水号（第三个部分）
            String outTradeNo = key.split("_")[2];

            // 关闭未支付的挂号订单（更新数据库状态）
            patientRegistrationDao.closeUnpaidOrder(outTradeNo);

            // 释放数据库表的挂号数量
            releaseRegistrationLock(outTradeNo);

            // 获取scheduleId
            Map<String, Integer> map = getDoctorScheduleId(outTradeNo);
            if (map != null) {
                // 更新 Redis 缓存中的已挂号人数
                updateRedisCache(map.get("doctorScheduleId"));
            }

        } catch (Exception e) {
            log.error("处理过期挂号订单失败，Key：{}", key, e);
        }
    }


    /**
     * 释放出诊计划时段
     */
    private void releaseRegistrationLock(String outTradeNo) {
        doctorWorkPlanDao.releaseRegistrationLock(outTradeNo);
        doctorWorkPlanScheduleDao.releaseRegistrationLock(outTradeNo);
    }

    /**
     * 获取DoctorScheduleId
     */
    private Map<String, Integer> getDoctorScheduleId(String outTradeNo) {
        return patientRegistrationDao.getDoctorScheduleIdByOutTradeNo(outTradeNo);
    }

    /**
     * 更新 Redis 缓存中的已挂号人数
     */
    private void updateRedisCache(int scheduleId) {
        String key = Constants.WORK_PLAN_SCHEDULE_KEY + scheduleId;
        try {
            redisTemplate.opsForHash().increment(key, "num", -1);
        } catch (Exception e) {
            log.warn("Redis 更新失败，Key：{}", key, e);
        }
    }
}
