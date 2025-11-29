package com.hospital.wx.controller.form.mobile;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Schema(description = "移动端医生出诊表单")
public class SelectDoctorWorkPlanScheduleForm {
    @NotNull(message = "doctorId不能为空")
    @Min(value = 1, message = "doctorId不能小于1")
    private Integer doctorId;

    @NotBlank(message = "date不能为空")
    private String date;

}
