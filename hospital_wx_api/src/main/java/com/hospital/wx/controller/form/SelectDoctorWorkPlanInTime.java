package com.hospital.wx.controller.form;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
@Schema(description = "查询出诊计划")
public class SelectDoctorWorkPlanInTime {

    @NotBlank(message = "startDate不能为空")
    private String startDate;

    @NotBlank(message = "endDate不能为空")
    private String endDate;

    @Min(value = 1, message = "deptId不能为空")
    private Integer deptId;

    private String doctorName;
}
