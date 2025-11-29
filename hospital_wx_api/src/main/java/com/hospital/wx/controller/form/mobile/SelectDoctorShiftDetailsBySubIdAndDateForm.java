package com.hospital.wx.controller.form.mobile;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Schema(description = "查询医生出诊信息表单")
public class SelectDoctorShiftDetailsBySubIdAndDateForm {

    @NotNull(message = "deptSubId不能为空")
    @Min(value = 1, message = "deptSubId不能小于1")
    private Integer deptSubId;

    @NotBlank(message = "date不能为空")
    private String date;

}
