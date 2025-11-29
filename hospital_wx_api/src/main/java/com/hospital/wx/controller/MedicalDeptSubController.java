package com.hospital.wx.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import com.hospital.wx.common.CommonResult;
import com.hospital.wx.controller.form.SelectMedicalDeptSubListForm;
import com.hospital.wx.service.MedicalDeptSubService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
@RestController
@RequestMapping("/medical/dept/sub")
@Tag(name = "MedicalDeptSubController", description = "诊室管理接口")
public class MedicalDeptSubController {
    @Autowired
    MedicalDeptSubService medicalDeptSubService;
    @PostMapping("/selectMedicalDeptSubList")
//    @SaCheckLogin
    @Operation(summary = "根据科室ID查询诊室信息")
    public CommonResult selectMedicalDeptSubList(@RequestBody @Valid SelectMedicalDeptSubListForm form) {
        ArrayList<HashMap> list = medicalDeptSubService.selectMedicalDeptSubList(form.getDeptId());
        return CommonResult.ok().put(CommonResult.RETURN_RESULT, list);
    }
}
