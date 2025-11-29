package com.hospital.wx.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import com.hospital.wx.common.CommonResult;
import com.hospital.wx.controller.form.mobile.SelectDoctorInfoByIdForm;
import com.hospital.wx.exception.WxErrorCode;
import com.hospital.wx.service.DoctorService;
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

@RestController
@RequestMapping("/doctor")
@Tag(name = "DoctorController", description = "移动端医生管理接口")
@Slf4j
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @PostMapping("/selectDoctorInfoById")
    @Operation(summary = "查询医生详情信息")
    @SaCheckLogin
    public CommonResult selectDoctorInfoById(@RequestBody @Valid SelectDoctorInfoByIdForm form) {
        try {
            HashMap doctorInfo  = doctorService.selectDoctorInfoById(form.getId());
            return CommonResult.ok(doctorInfo);
        } catch (Exception e) {
            log.error("查询医生信息时出错，form: {}", form, e);
            return CommonResult.failed(WxErrorCode.DOCTOR_INFO_QUERY_FAILED);
        }
    }
}