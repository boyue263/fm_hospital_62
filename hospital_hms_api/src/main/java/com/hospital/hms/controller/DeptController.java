package com.hospital.hms.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.annotation.SaMode;
import cn.hutool.json.JSONUtil;
import com.hospital.common.utils.PageUtils;
import com.hospital.hms.common.CommonResult;
import com.hospital.hms.controller.form.*;
import com.hospital.hms.pojo.Dept;
import com.hospital.hms.sercive.DeptService;
import com.hospital.hms.sercive.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.models.security.SecurityScheme;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;

@RestController
@RequestMapping("/dept")
@Slf4j
@Tag(name="DeptController" ,description="部门管理接口")
public class DeptController {
    @Autowired
    private DeptService deptService;
    @Autowired
    private UserService userService;

    @GetMapping("/selectAllDept")
    @Operation(summary = "查询所有部门")
    @SaCheckPermission(value = {"ROOT", "DEPT:SELECT"}, mode = SaMode.OR)
    public CommonResult selectAllDept() {
        ArrayList<HashMap> list = deptService.selectAll();
        return CommonResult.ok().put("list", list);

    }
    @PostMapping("/selectDeptByCondition")
    @Operation(summary = "根据条件查询")
    @SaCheckPermission(value = {"ROOT", "DEPT:SELECT"}, mode = SaMode.OR)
    public CommonResult selectDeptByCondition(@Valid @RequestBody SelectDeptByConditionForm form) {
        int page = form.getPage();
        int length = form.getLength();
        int start = (page-1)*length;

        HashMap param = JSONUtil.parse(form).toBean(HashMap.class);
        param.put("start", start);

        PageUtils pageUtils = deptService.selectDeptByCondition(param);
        return CommonResult.ok().put("page", pageUtils);
    }

    @PostMapping("/insert")
    @Operation(summary = "插入部门")
    @SaCheckPermission(value = {"ROOT", "DEPT:INSERT"}, mode = SaMode.OR)
    public CommonResult insertDept(@Valid @RequestBody InsertDeptForm form) {
        Dept dept = JSONUtil.parse(form).toBean(Dept.class);
        int rows = deptService.insertDept(dept);
        return CommonResult.ok().put("rows", rows);

    }
    @PostMapping("/selectById")
    @Operation(summary = "根据id查询部门")
    @SaCheckPermission(value = {"ROOT", "DEPT:SELECT"}, mode = SaMode.OR)
    public CommonResult selectById(@Valid @RequestBody SelectDeptByIdForm form) {
        Integer deptId = form.getId();
        HashMap map = deptService.selectDeptById(deptId);
        return CommonResult.ok(map);
    }
    @PostMapping("/update")
    @Operation(summary = "更新部门")
    @SaCheckPermission(value = {"ROOT", "DEPT:UPDATE"}, mode = SaMode.OR)
    public CommonResult updateDept(@Valid @RequestBody UpdateDeptForm form) {
        Dept dept = JSONUtil.parse(form).toBean(Dept.class);
        int rows = deptService.updateDept(dept);
        return CommonResult.ok().put("rows", rows);
    }
    @PostMapping("/deleteDeptByIds")
    @Operation(summary = "删除部门记录")
    @SaCheckPermission(value = {"ROOT", "DEPT:DELETE"}, mode = SaMode.OR)
    public CommonResult deleteDeptByIds(@Valid @RequestBody DeleteDeptByIdsForm form) {
        Integer rows = deptService.deleteDeptById(form.getIds());
        return CommonResult.ok().put("rows", rows);
    }



}
