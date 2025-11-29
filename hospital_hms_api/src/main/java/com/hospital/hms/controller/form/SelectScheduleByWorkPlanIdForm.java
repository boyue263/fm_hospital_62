package com.hospital.hms.controller.form;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@Schema(description = "根据WorkPlanId查询出诊计划")
public class SelectScheduleByWorkPlanIdForm {

    @NotNull(message = "id不能为空")
    @Min(value = 1, message = "workPlanId不能小于1")
    private Integer workPlanId;
}
