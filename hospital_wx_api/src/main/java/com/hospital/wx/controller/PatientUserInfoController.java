package com.hospital.wx.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.json.JSONUtil;
import com.hospital.wx.common.CommonResult;
import com.hospital.wx.controller.form.InsertPatientUserInfoForm;
import com.hospital.wx.controller.form.UpdatePatientUserInfoForm;
import com.hospital.wx.exception.WxErrorCode;
import com.hospital.wx.pojo.PatientUserInfo;
import com.hospital.wx.service.PatientUserInfoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;

@RestController
@RequestMapping("/patient/user/info")
@Tag(name = "PatientUserInfoController", description = "患者详细信息记录接口")
@Slf4j
public class PatientUserInfoController {
    @Autowired
    private PatientUserInfoService patientUserInfoService;
    @PostMapping("/insert")
    @SaCheckLogin
    @Operation(summary = "就诊卡信息保存")
    public CommonResult insert(@RequestBody @Valid InsertPatientUserInfoForm form) {

        try {
            // 将表单对象转化为实体
            PatientUserInfo entity = BeanUtil.toBean(form, PatientUserInfo.class);

            // 通过 Sa-Token 框架获取当前的 userId，若未登录则抛出异常
            int userId = StpUtil.getLoginIdAsInt();
            entity.setUserId(userId);
            entity.setUuid(IdUtil.simpleUUID());

            //将 medicalHistory 数组转换为 JSON 字符串
            String json = JSONUtil.parseArray(form.getMedicalHistory()).toString();

            // 是否录入人脸信息，默认值 false
            entity.setMedicalHistory(json);
            // 插入数据库
            patientUserInfoService.insert(entity);
            return CommonResult.ok();
        } catch (Exception e) {
            // 记录异常日志
            log.error("就诊卡信息保存失败", e);
            return CommonResult.failed(WxErrorCode.SAVE_PATIENT_INFO_FAILED);
        }
    }
    @GetMapping("/selectPatientUserInfoByUserId")
    @SaCheckLogin
    @Operation(summary = "诊卡信息查询接口开发")
    public CommonResult selectPatientUserInfoByUserId() {
        try {
            // 基于 token 获得 userId
            int userId = StpUtil.getLoginIdAsInt();

            // 查询患者信息
            HashMap<String, Object> map = patientUserInfoService.selectPatientUserInfoByUserId(userId);

            // 判断查询结果是否为空
            if (MapUtil.isEmpty(map)) {
                return CommonResult.ok(CommonResult.failed(WxErrorCode.CARD_INFO_QUERY_FAILED));
            }
            return CommonResult.ok(map);
        } catch (Exception e) {
            // 记录异常日志
            log.error("查询患者信息时出错，userId: {}", StpUtil.getLoginIdAsInt(), e);

            // 返回通用的失败信息
            return CommonResult.failed(WxErrorCode.CARD_INFO_QUERY_FAILED);
        }
    }
    @PostMapping("/update")
    @SaCheckLogin
    @Operation(summary = "就诊卡信息更新")
    public CommonResult update(@RequestBody @Valid UpdatePatientUserInfoForm form) {
        try {
            // 将表单数据转换为 PatientUserInfo 对象
            PatientUserInfo patientUserInfo = BeanUtil.toBean(form, PatientUserInfo.class);
            // 将 MedicalHistory 由数组转换为字符串
            String json = JSONUtil.parseArray(form.getMedicalHistory()).toString();
            patientUserInfo.setMedicalHistory(json);
            // 执行更新操作
            patientUserInfoService.update(patientUserInfo);
            // 返回成功结果
            return CommonResult.ok();
        } catch (Exception e) {
            // 记录异常日志
            log.error("更新患者信息时出错，userId: {}", StpUtil.getLoginIdAsInt(), e);
            // 返回统一的失败信息
            return CommonResult.failed(WxErrorCode.UPDATE_PATIENT_INFO_FAILED);
        }
    }
    @SaCheckLogin
    @GetMapping("/checkPatientExistsByUserId")
    @Operation(summary = "判断患者是否存在")
    public CommonResult userExists() {
        try {
            // 获取当前登录用户的 ID
            int userId = StpUtil.getLoginIdAsInt();
            // 调用服务层判断用户是否存在
            boolean exists = patientUserInfoService.checkPatientExistsByUserId(userId);
            // 返回结果
            return CommonResult.ok().put(CommonResult.RETURN_RESULT, exists);
        } catch (Exception e) {
            // 记录异常日志
            log.error("检查用户是否存在时出错，userId: {}", StpUtil.getLoginIdAsInt(), e);
            // 返回失败信息
            return CommonResult.failed(WxErrorCode.USER_EXISTS_QUERY_FAILED);
        }
    }

}
