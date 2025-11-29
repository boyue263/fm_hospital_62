package com.hospital.hms.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.annotation.SaMode;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.json.JSONUtil;
import com.hospital.common.utils.PageUtils;
import com.hospital.hms.common.HMSErrorCode;
import com.hospital.hms.common.CommonResult;
import com.hospital.hms.controller.form.*;
import com.hospital.hms.pojo.Doctor;
import com.hospital.hms.sercive.DoctorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/doctor")
@Tag(name = "DoctorController" ,description = "医生管理接口")
@Slf4j
public class DoctorController {
    @Autowired
    private DoctorService doctorService;
    @PostMapping("/selectConditionByPage")
    @Operation(summary = "根据分页展示医生信息")
    @SaCheckPermission(value = {"ROOT", "DOCTOR:SELECT"}, mode = SaMode.OR)
    public CommonResult selectConditionByPage(@Valid @RequestBody SelectDoctorByPageForm form) {
        HashMap<String, Object> map = JSONUtil.parse(form).toBean(HashMap.class);
        Integer page = form.getPage();
        Integer length = form.getLength();
        Integer start = (page-1)*length;
        map.put("start", start);
        PageUtils pageUtils = doctorService.selectDoctorByPage(map);
        return  CommonResult.ok().put("result", pageUtils);
    }
    @PostMapping("/selectDoctorDetailById")
    @Operation(summary = "根据id查看医生个人信息")
    @SaCheckPermission(value = {"ROOT", "DOCTOR:SELECT"}, mode = SaMode.OR)
    public CommonResult selectDoctorDetailById(@Valid @RequestBody SelectDoctorDetailByIdForm form) {
        HashMap<String,Object> map = doctorService.selectDoctorDetailById(form.getId());
        return CommonResult.ok().put("doctor", map);
    }
    @PostMapping("/insert")
    @SaCheckLogin
    @SaCheckPermission(value = {"ROOT", "DOCTOR:INSERT"}, mode = SaMode.OR)
    @Operation(summary = "添加医生信息")
    public CommonResult insert(@Valid @RequestBody InsertDoctorForm form) {
     Map<String, Object> map = JSONUtil.parse(form).toBean(HashMap.class);
     String json = JSONUtil.parseArray(form.getTag()).toString();
     map.replace("tag", json);
     map.put("uuid", IdUtil.simpleUUID().toUpperCase());
     doctorService.insert(map);
     return CommonResult.ok();
    }
    @PostMapping("/selectById")
    @SaCheckLogin
    @SaCheckPermission(value = {"ROOT", "DOCTOR:SELECT"}, mode = SaMode.OR)
    @Operation(summary = "通过ID查询医生信息")
    public CommonResult searchById(@RequestBody @Valid SelectDoctorByIdForm form) {
        HashMap map = doctorService.selectById(form.getId());
        return CommonResult.ok(map);
    }
    @PostMapping("/update")
    @SaCheckLogin
    @SaCheckPermission(value = {"ROOT", "DOCTOR:UPDATE"}, mode = SaMode.OR)
    @Operation(summary = "修改医生信息")
    public CommonResult update(@RequestBody @Valid UpdateDoctorForm form) {
        try {
            Map<String, Object> param = BeanUtil.beanToMap(form);
            // 将 tag 转换为 JSON 数组的字符串
            String json = JSONUtil.parseArray(form.getTag()).toString();
            param.replace("tag", json);
            // 调用 service 层进行更新操作
            doctorService.update(param);
            // 返回成功结果
            return CommonResult.ok();
        } catch (Exception e) {
            // 记录异常信息并返回失败的 CommonResult
            log.error("Error updating doctor information: ", e);
            return CommonResult.error("更新医生信息失败，请稍后再试。");
        }
    }
    @PostMapping("/deleteDoctorByIds")
    @SaCheckLogin
    @SaCheckPermission(value = {"ROOT", "DOCTOR:DELETE"}, mode = SaMode.OR)
    @Operation(summary = "删除医生信息")
    public CommonResult deleteByIds(@RequestBody @Valid DeleteDoctorByIdsForm form) {
        try {
            // 调用服务层删除操作
            doctorService.deleteDoctorByIds(form.getIds());
            // 返回成功响应
            return CommonResult.ok();
        } catch (Exception e) {
            // 捕获异常并记录日志
            log.error("Error occurred while deleting doctors with IDs: {}", form.getIds(), e);
            // 使用枚举返回错误响应
            return CommonResult.error(1001,"删除医生信息失败");
        }
    }
    @Operation(summary = "根据诊室ID查询医生信息")
    @PostMapping("/selectDoctorsBySubId")
    @SaCheckLogin
    public CommonResult selectDoctorsBySubId(@RequestBody @Valid SelectDoctorsBySubIdForm form) {
        try {
            ArrayList<HashMap<String,Object>> result = doctorService.selectDoctorBySubId(form.getDeptSubId());
            return CommonResult.ok().put(CommonResult.RETURN_RESULT, result);
        } catch (Exception e) {
            // 捕获异常并记录中文日志
            log.error("查询诊室ID为 {} 的医生信息时发生错误", form.getDeptSubId(), e);
            return CommonResult.failed(HMSErrorCode.INVALID_DOCTOR_ID);
        }
    }
    @PostMapping("/updatePhoto")
    @SaCheckLogin
    @SaCheckPermission(value = {"ROOT", "DOCTOR:UPDATE"}, mode = SaMode.OR)
    @Operation(summary = "照片上传")
    public CommonResult updatePhoto(@Param("file") MultipartFile file, @Param("doctorId") Integer doctorId) {
        doctorService.updatePicture(file,doctorId);

        return CommonResult.ok();
    }

}
