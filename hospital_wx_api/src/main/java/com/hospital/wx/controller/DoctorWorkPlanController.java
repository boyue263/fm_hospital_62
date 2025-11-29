package com.hospital.wx.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.hutool.core.bean.BeanUtil;
import com.hospital.wx.common.CommonResult;
import com.hospital.wx.controller.form.mobile.SelectCanRegisterInDateRangeForm;
import com.hospital.wx.controller.form.mobile.SelectDoctorShiftDetailsBySubIdAndDateForm;
import com.hospital.wx.exception.WxErrorCode;
import com.hospital.wx.service.DoctorWorkPlanService;
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
@Tag(name = "DoctorWorkPlanMobileController", description = "移动端出诊信息接口")
@Slf4j
public class DoctorWorkPlanController {
    @Autowired
    private DoctorWorkPlanService doctorWorkPlanService;
    @PostMapping("/selectDoctorScheduleBySubIdAndDate")
//    @SaCheckLogin
    @Operation(summary = "查询医生排班信息")
    public CommonResult selectDoctorScheduleBySubIdAndDate(@RequestBody @Valid SelectCanRegisterInDateRangeForm form) {
        try {
            // 将form转为Map
            Map<String, Object> param = BeanUtil.beanToMap(form);
            // 调用服务方法获取医生排班数据
            ArrayList<String> list = doctorWorkPlanService.selectDoctorScheduleBySubIdAndDate(param);
            // 返回结果
            return CommonResult.ok().put(CommonResult.RETURN_RESULT, list);
        } catch (Exception e) {
            // 记录异常日志
            log.error("查询医生排班信息时出错，form: {}", form, e);
            // 返回通用的失败信息
            return CommonResult.failed(WxErrorCode.PLAN_INFO_QUERY_FAILED);
        }
    }
    @PostMapping("/getDoctorShiftDetailsBySubIdAndDate")
    @SaCheckLogin
    @Operation(summary = "查询指定日期的出诊医生")
    public CommonResult getDoctorShiftDetailsBySubIdAndDate(@RequestBody @Valid SelectDoctorShiftDetailsBySubIdAndDateForm form) {
        try {
            Map param = BeanUtil.beanToMap(form);
            ArrayList<HashMap> list = doctorWorkPlanService.getDoctorShiftDetailsBySubIdAndDate(param);
            return CommonResult.ok().put(CommonResult.RETURN_RESULT, list);
        } catch (Exception e) {
            // 记录异常日志，便于排查问题
            log.error("查询医生排班详情信息时出错，form: {}", form, e);
            // 返回失败信息
            return CommonResult.failed(WxErrorCode.DOCTOR_SCHEDULE_QUERY_FAILED);
        }
    }

}
