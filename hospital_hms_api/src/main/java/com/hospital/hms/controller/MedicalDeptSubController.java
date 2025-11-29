package com.hospital.hms.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.annotation.SaMode;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.json.JSONUtil;
import com.hospital.common.utils.PageUtils;
import com.hospital.hms.common.CommonResult;
import com.hospital.hms.controller.form.*;
import com.hospital.hms.dao.MedicalDeptSubDao;
import com.hospital.hms.pojo.MedicalDeptSub;
import com.hospital.hms.sercive.MedicalDeptSubService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/medical/dept/sub")
@Tag(name = "MedicalDeptSubController" ,description = "诊室管理接口")
public class MedicalDeptSubController {
    @Autowired
    MedicalDeptSubService medicalDeptSubService;

    @PostMapping("/selectConditionByPage")
    @Operation(summary = "根据分页查询")
    @SaCheckPermission(value = {"ROOT", "MEDICAL_DEPT_SUB:SELECT"}, mode = SaMode.OR)
    public CommonResult selectConditionByPage(@RequestBody @Valid SelectMedicalDeptSubByPageForm form) {
        HashMap<String, Object> map = JSONUtil.parse(form).toBean(HashMap.class);
        Integer page = form.getPage();
        Integer length = form.getLength();
        Integer start = (page-1)*length;
        map.put("start", start);
        PageUtils pageUtils = medicalDeptSubService.selectMedicalDeptSubConditionByPage(map);
        return CommonResult.ok().put("result", pageUtils);
    }
    @PostMapping("/insert")
    @Operation(summary="插入医疗诊室")
    @SaCheckPermission(value = {"ROOT", "MEDICAL_DEPT_SUB:INSERT"}, mode = SaMode.OR)
    public CommonResult insert(@RequestBody @Valid InsertMedicalDeptSubForm form) {
        MedicalDeptSub medicalDeptSub = JSONUtil.parse(form).toBean(MedicalDeptSub.class);
        Integer rows =medicalDeptSubService.insertMedicalDeptSub(medicalDeptSub);
        return CommonResult.ok().put("rows", rows);
    }
    @PostMapping("selectById")
    @Operation(summary = "根据诊室id获取信息")
    @SaCheckPermission(value = {"ROOT", "MEDICAL_DEPT_SUB:SELECT"}, mode = SaMode.OR)
    public CommonResult selectById(@RequestBody @Valid SelectMedicalDeptSubByIdForm form) {
        HashMap<String, Object> map = medicalDeptSubService.selectMedicalDeptSubById(form.getId());
        return CommonResult.ok(map);
    }
    @PostMapping("/update")
    @Operation(summary = "根据id更新诊室")
    @SaCheckPermission(value = {"ROOT","MEDICAL_DEPT_SUB:UPDA"}, mode = SaMode.OR)
    public CommonResult update(@RequestBody @Valid UpdateMedicalDeptSubForm form) {
        MedicalDeptSub medicalDeptSub =JSONUtil.parse(form).toBean(MedicalDeptSub.class);
        Integer rows = medicalDeptSubService.updateMedicalDeptSub(medicalDeptSub);
        return CommonResult.ok();
    }

}
