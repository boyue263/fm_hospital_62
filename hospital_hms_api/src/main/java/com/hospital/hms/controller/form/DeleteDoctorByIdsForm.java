package com.hospital.hms.controller.form;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Schema(description = "删除医生表单")
@Data
public class DeleteDoctorByIdsForm {
    @NotEmpty(message = "ids不能为空")
    @Schema(description = "医生ID")
    private Integer[] ids;
}