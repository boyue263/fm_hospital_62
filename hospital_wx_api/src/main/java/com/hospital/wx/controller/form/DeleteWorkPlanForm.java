package com.hospital.wx.controller.form;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Schema(description = "删除出诊计划")
@Data
public class DeleteWorkPlanForm {
    @NotNull(message = "workPlanId不能为空")
    @Min(value = 1, message = "workPlanId不能小于1")
    private Integer workPlanId;

}