package com.hospital.hms.controller.form;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@Schema(description = "查询医生出诊时间段")
public class selectScheduleByDeptSubForm {

    @NotNull(message = "date不能为空")
    @Schema(description = "日期")
    private String date;

    @NotNull(message = "deptSubId不能为空")
    @Min(value = 1, message = "deptSubId不能小于1")
    private Integer deptSubId;
}
