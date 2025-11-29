package com.hospital.hms.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.annotation.SaMode;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.PageUtil;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.hospital.common.utils.PageUtils;
import com.hospital.hms.common.CommonResult;
import com.hospital.hms.controller.form.*;
import com.hospital.hms.pojo.MedicalDept;
import com.hospital.hms.sercive.MedicalDeptService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.models.security.SecurityScheme;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/medical/dept")
@Tag(name = "MedicalDeptController", description = "科室管理接口")
public class MedicalDeptController {
    @Autowired
    private MedicalDeptService medicalDeptService;
    @Operation(summary = "分页显示科室信息接口")
    @PostMapping("/selectConditionByPage")
    @SaCheckPermission(value = {"ROOT", "MEDICAL_DEPT:SELECT"}, mode = SaMode.OR)
    public CommonResult selectConditionByPage(@RequestBody @Valid SelectMedicalDeptByPageForm form) {
        Map<String,Object> map = BeanUtil.beanToMap(form);
        Integer page = form.getPage();
        Integer length = form.getLength();
        Integer start = (page-1)*length;
        map.put("start", start);
        PageUtils pageUtils = medicalDeptService.selectConditionByPage(map);
        return CommonResult.ok().put(CommonResult.RETURN_RESULT, pageUtils);
    }
    @Operation(summary = "添加科室信息接口")
    @PostMapping("/insert")
    @SaCheckPermission(value = {"ROOT", "MEDICAL_DEPT:INSERT"}, mode = SaMode.OR)
    public CommonResult insert(@RequestBody @Valid InsertMedicalDeptForm form) {
        MedicalDept medicalDept = BeanUtil.toBean(form, MedicalDept.class);
        medicalDeptService.insertMedicalDept(medicalDept);
        return CommonResult.ok();
    }
    @Operation(summary = "通过ID查询科室信息接口")
    @PostMapping("/selectById")
    @SaCheckPermission(value = {"ROOT", "MEDICAL_DEPT:SELECT"}, mode = SaMode.OR)
    public CommonResult selectById(@RequestBody @Valid SelectMedicalDeptByIdForm form) {
        HashMap<String, Object> map = medicalDeptService.selectMedicalDeptById(form.getId());
        return CommonResult.ok(map);

    }
    @Operation(summary = "更新科室信息")
    @PostMapping("/update")
    @SaCheckPermission(value = {"ROOT", "MEDICAL_DEPT:UPDATE"}, mode = SaMode.OR)
    public CommonResult update(@RequestBody @Valid UpdateMedicalDeptForm form) {
        MedicalDept medicalDept = BeanUtil.toBean(form, MedicalDept.class);
        Integer rows = medicalDeptService.updateMedicalDept(medicalDept);
        return CommonResult.ok().put("rows", rows);
    }

    @Operation(summary="获取所有科室名称和id")
    @GetMapping("/selectAllDeptNameAndId")
    @SaCheckPermission(value = {"ROOT", "MEDICAL_DEPT:SELECT"}, mode = SaMode.OR)
    public CommonResult selectAllDeptNameAndId() {
        ArrayList<HashMap<String, Object>> hashMaps = medicalDeptService.selectAllMedicalDeptNameAndId();
        return CommonResult.ok().put(CommonResult.RETURN_RESULT, hashMaps);
    }
    @Operation(summary="根据id批量删除")
    @PostMapping("/deleteByIds")
    @SaCheckPermission(value = {"ROOT", "MEDICAL_DEPT:DELETE"}, mode = SaMode.OR)
    public CommonResult deleteByIds(@RequestBody @Valid DeleteMedicalDeptByIdsForm form) {
        Integer[] ids = form.getIds();
        Integer rows = medicalDeptService.deleteMedicalDeptById(ids);
        return CommonResult.ok().put("rows", rows);
    }
    @Operation(summary="获取所有科室名称和")
    @GetMapping("/selectDeptAndSub")
    @SaCheckPermission(value = {"ROOT", "MEDICAL_DEPT:SELECT"}, mode = SaMode.OR)
    public CommonResult selectDeptAndSub(){
        Map<String, List<HashMap<String, Object>>> stringListMap = medicalDeptService.selectDeptAndSup();
        return CommonResult.ok().put(CommonResult.RETURN_RESULT, stringListMap);
    }
}
