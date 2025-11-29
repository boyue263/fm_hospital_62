package com.hospital.hms.controller.form;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
@Schema(description = "按科室和日期查询医生排班表单")
public class SelectDoctorScheduleByDeptSubIdAndDateForm {

    @NotNull(message = "date不能为空")
    @Schema(description = "日期，格式：yyyy-MM-dd", example = "2025-02-12")
    private LocalDate date;
//
//    @NotNull(message = "deptSubId不能为空")
    @Min(value = 1, message = "deptSubId不能小于1")
    @Schema(description = "科室ID", example = "1")
    private Integer deptSubId;

}