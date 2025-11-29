package com.hospital.wx.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.bean.BeanUtil;
import com.hospital.wx.common.CommonResult;
import com.hospital.wx.controller.form.mobile.CheckRegisterConditionForm;
import com.hospital.wx.controller.form.mobile.PatientRegistrationAppointmentForm;
import com.hospital.wx.controller.form.mobile.SelectRegistrationByPageForm;
import com.hospital.wx.controller.form.mobile.SelectRegistrationInfoForm;
import com.hospital.wx.exception.WxErrorCode;
import com.hospital.wx.service.PatientRegistrationService;
import com.hospital.wx.utils.PageUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/patient/registration")
@Tag(name = "PatientRegistrationController", description = "患者挂号接口")
@Slf4j
public class PatientRegistrationController {
    @Autowired
    private PatientRegistrationService patientRegistrationService;
    @PostMapping("/checkRegisterCondition")
    @SaCheckLogin
    @Operation(summary = "检查挂号条件是否满足")
    public CommonResult checkRegisterCondition(@RequestBody @Valid CheckRegisterConditionForm form) {
        try {
            int userId = StpUtil.getLoginIdAsInt();
            form.setUserId(userId);
            Map param = BeanUtil.beanToMap(form);
            String result = patientRegistrationService.checkRegisterCondition(param);
            return CommonResult.ok().put(CommonResult.RETURN_RESULT, result);
        } catch (Exception e) {
            // 记录异常日志，便于排查问题
            log.error("检查挂号条件时出错，form: {}", form, e);
            // 返回失败信息
            return CommonResult.failed(WxErrorCode.CHECK_REGISTER_CONDITION_FAILED);
        }
    }
    @PostMapping("/registerMedicalAppointment")
    @SaCheckLogin
    @Operation(summary = "患者挂号")
    public CommonResult registerMedicalAppointment(@RequestBody @Valid PatientRegistrationAppointmentForm form) {
        try {
            int userId = StpUtil.getLoginIdAsInt();
            Map param = BeanUtil.beanToMap(form);
            param.put("userId", userId);
            HashMap result = patientRegistrationService.registerMedicalAppointment(param);
            return CommonResult.ok(result != null ? result : new HashMap<>());
        } catch (Exception e) {
            log.error("预约挂号时出错，form: {}", form, e);
            return CommonResult.failed(WxErrorCode.REGISTER_MEDICAL_APPOINTMENT_FAILED);
        }
    }
    @PostMapping("/selectRegistrationByPage")
    @SaCheckLogin
    public CommonResult selectRegistrationByPage(@RequestBody @Valid SelectRegistrationByPageForm form) {
        try {
            // 获取当前用户ID
            int userId = StpUtil.getLoginIdAsInt();
            form.setUserId(userId);
            Map param = BeanUtil.beanToMap(form);
            param.put("start", (form.getPage() - 1) * form.getLength());
            PageUtils pageUtils = patientRegistrationService.selectRegistrationByPage(param);
            return CommonResult.ok().put("result", pageUtils);
        } catch (Exception e) {
            // 记录异常日志
            log.error("查询挂号信息时出错，form: {}", form, e);
            // 返回通用失败信息
            return CommonResult.failed(WxErrorCode.REGISTRATION_QUERY_FAILED);
        }
    }
    @PostMapping("/selectRegistrationInfo")
    @SaCheckLogin
    public CommonResult selectRegistrationInfo(@RequestBody @Valid SelectRegistrationInfoForm form) {
        try {
            int userId = StpUtil.getLoginIdAsInt();
            form.setUserId(userId);
            Map param = BeanUtil.beanToMap(form);
            HashMap map = patientRegistrationService.selectRegistrationInfo(param);
            return CommonResult.ok(map);
        } catch (Exception e) {
            // 记录异常日志
            log.error("查询挂号详情时出错，form: {}", form, e);
            return CommonResult.failed(WxErrorCode.REGISTRATION_DETAIL_QUERY_FAILED);
        }
    }


}