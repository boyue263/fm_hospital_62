package com.hospital.wx.controller;

import cn.hutool.core.bean.BeanUtil;
import com.hospital.wx.common.CommonResult;
import com.hospital.wx.controller.form.SelectMedicalDeptListForm;
import com.hospital.wx.service.MedicalDeptService;
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
@RequestMapping("/medical/dept")
@Tag(name = "MedicalDeptController", description = "科室管理接口")
public class MedicalDeptController {
    @Autowired
    private MedicalDeptService medicalDeptService;
    @Operation(summary = "查询所有科室列表")
    @PostMapping("/selectMedicalDeptList")
    public CommonResult selectMedicalDeptList(@RequestBody @Valid SelectMedicalDeptListForm form) {
        Map param = BeanUtil.beanToMap(form);
        ArrayList<HashMap> list = medicalDeptService.selectMedicalDeptList(param);
        return CommonResult.ok().put(CommonResult.RETURN_RESULT, list);
    }
}
