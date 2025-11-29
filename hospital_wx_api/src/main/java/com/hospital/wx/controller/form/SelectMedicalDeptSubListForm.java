package com.hospital.wx.controller.form;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@Schema(description = "根据科室ID查询诊室信息表单")
public class SelectMedicalDeptSubListForm {

    @NotNull(message = "deptId不能为空")
    @Min(value = 1, message = "deptId不能小于1")
    private Integer deptId;

}
