package com.hospital.wx.controller.form.mobile;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Schema(description = "查询医生出诊日期表单")
public class SelectCanRegisterInDateRangeForm {
    @NotNull(message = "deptSubId不能为空")
    @Min(value = 1, message = "deptSubId不能小于1")
    private Integer deptSubId;

    @NotBlank(message = "startDate不能为空")
    private String startDate;

    @NotBlank(message = "endDate不能为空")
    private String endDate;
}
