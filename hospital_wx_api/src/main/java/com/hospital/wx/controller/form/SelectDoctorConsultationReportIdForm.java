package com.hospital.wx.controller.form;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Schema(description = "据挂号单ID查询患者电子处方")
@Data
public class SelectDoctorConsultationReportIdForm {
    private Integer userId;

    @NotNull(message = "registrationId不能为空")
    @Min(value = 1L, message = "registrationId不能小于1")
    private Integer registrationId;
}
