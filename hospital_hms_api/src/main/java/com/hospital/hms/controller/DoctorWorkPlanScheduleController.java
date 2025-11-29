package com.hospital.hms.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.annotation.SaMode;
import cn.hutool.core.bean.BeanUtil;
import com.hospital.hms.common.CommonResult;
import com.hospital.hms.controller.form.DeleteWorkPlanForm;
import com.hospital.hms.controller.form.SelectDoctorScheduleByDeptSubIdAndDateForm;
import com.hospital.hms.controller.form.SelectScheduleByWorkPlanIdForm;
import com.hospital.hms.controller.form.UpdateDoctorScheduleForm;
import com.hospital.hms.sercive.DoctorWorkPlanScheduleService;
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
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/doctor/work_plan/schedule")
@Tag(name = "DoctorWorkPlanScheduleController", description = "出诊管理接口")
@Slf4j
public class DoctorWorkPlanScheduleController {
    @Autowired
    DoctorWorkPlanScheduleService doctorWorkPlanScheduleService;
    @Operation(summary = "通过诊室ID和日期查询医生出诊信息")
    @PostMapping("/selectDoctorScheduleByDeptSubIdAndDate")
    @SaCheckPermission(value = {"ROOT", "SCHEDULE:SELECT"}, mode = SaMode.OR)
    public CommonResult selectDoctorScheduleByDeptSubIdAndDate(@RequestBody @Valid SelectDoctorScheduleByDeptSubIdAndDateForm form) {

        try {
            // 将表单数据转换为 Map
            Map<String, Object> param = BeanUtil.beanToMap(form);
            // 查询医生出诊排班数据
            ArrayList<HashMap<String,Object>> scheduleData = doctorWorkPlanScheduleService.selectDoctorScheduleByDeptSubIdAndDate(param);
            // 返回成功结果
            return CommonResult.ok().put(CommonResult.RETURN_RESULT, scheduleData);
        } catch (Exception e) {
            // 记录异常日志
            log.error("查询医生出诊排班时出错，form: {}", form, e);
            // 返回统一的失败信息
            return CommonResult.failed("查询医生出诊排班时出错");
        }
    }
    @Operation(summary = "通过WorkPlanId查询医生出诊排班时出错")
    @PostMapping("/selectScheduleByWorkPlanId")
    @SaCheckPermission(value = {"ROOT", "SCHEDULE:SELECT"}, mode = SaMode.OR)
    public CommonResult selectDoctorScheduleByWorkPlanId(@RequestBody @Valid SelectScheduleByWorkPlanIdForm form) {
        try {
            HashMap<String,Object>  scheduleData = doctorWorkPlanScheduleService.selectDoctorScheduleByWorkPlanId(form.getWorkPlanId());
            return CommonResult.ok().put(CommonResult.RETURN_RESULT, scheduleData!=null?scheduleData: Collections.emptyMap());
        } catch (Exception e) {
            // 记录异常日志
            log.error("通过WorkPlanId查询医生出诊排班时出错，form: {}", form, e);
            // 返回统一的失败信息
            return CommonResult.failed("通过WorkPlanId查询医生出诊排班时出错");
        }
    }
    @Operation(summary = "更新医生出诊计划")
    @PostMapping("/updateSchedule")
    @SaCheckPermission(value = {"ROOT", "SCHEDULE:UPDATE"}, mode = SaMode.OR)
    public CommonResult updateSchedule(@RequestBody @Valid UpdateDoctorScheduleForm form) {
        try {
            // 将表单数据转换为 Map
            Map<String, Object> param = BeanUtil.beanToMap(form);
            // 更新医生排班信息
            doctorWorkPlanScheduleService.updateSchedule(param);
            // 返回成功结果
            return CommonResult.ok();
        } catch (Exception e) {
            // 记录异常日志
            log.error("更新医生排班时出错，form: {}", form, e);
            return CommonResult.failed("更新医生排班时出错");
        }
    }
    @Operation(summary = "删除出诊计划")
    @PostMapping("/deleteWorkPlan")
    @SaCheckLogin
    @SaCheckPermission(value = {"ROOT", "SCHEDULE:DELETE"}, mode = SaMode.OR)
    public CommonResult deleteWorkPlan(@RequestBody @Valid DeleteWorkPlanForm form) {
        try {
            doctorWorkPlanScheduleService.deleteWorkPlan(form.getWorkPlanId());
            return CommonResult.ok();
        } catch (Exception e) {
            log.error("删除医生出诊计划时出错，form: {}", form, e);
            return CommonResult.failed("删除医生出诊计划时出错");
        }
    }

}
