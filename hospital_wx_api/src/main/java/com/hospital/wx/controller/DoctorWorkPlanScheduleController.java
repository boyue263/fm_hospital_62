package com.hospital.wx.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.annotation.SaMode;
import cn.hutool.core.bean.BeanUtil;
import com.hospital.wx.common.CommonResult;
import com.hospital.wx.controller.form.mobile.SelectDoctorWorkPlanScheduleForm;
import com.hospital.wx.controller.form.selectScheduleByDeptSubForm;
import com.hospital.wx.exception.WxErrorCode;
import com.hospital.wx.service.DoctorWorkPlanScheduleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/doctor/work/plan")
@Tag(name = "DoctorWorkPlanScheduleController", description = "出诊管理接口")
@Slf4j
public class DoctorWorkPlanScheduleController {

    @Autowired
    private DoctorWorkPlanScheduleService doctorWorkPlanScheduleService;
//    http://127.0.0.1:9092/wx/doctor/work/plan/selectDoctorWorkPlanSchedule
    @PostMapping("/selectDoctorWorkPlanSchedule")
    @SaCheckLogin
    @Operation(summary = "挂号时段列表展示接口")
    public CommonResult selectDoctorWorkPlanSchedule(@RequestBody @Valid SelectDoctorWorkPlanScheduleForm form) {
        try {
            Map param = BeanUtil.beanToMap(form);
            ArrayList<HashMap> list = doctorWorkPlanScheduleService.selectDoctorWorkPlanSchedule(param);
            return CommonResult.ok().put(CommonResult.RETURN_RESULT, list);
        } catch (Exception e) {
            log.error("查询医生挂号时段信息出错，form: {}", form, e);
            return CommonResult.failed(WxErrorCode.DOCTOR_REGISTRATION_SLOT_QUERY_FAILED);
        }
    }
}