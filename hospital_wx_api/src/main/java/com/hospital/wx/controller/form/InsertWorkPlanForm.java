package com.hospital.wx.controller.form;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Schema(description = "添加出诊计划表单")
@Data
public class InsertWorkPlanForm {

    @NotNull(message = "deptSubId不能为空")
    @Min(value = 1, message = "deptSubId不能小于1")
    private Integer deptSubId;

    @NotNull(message = "doctorId不能为空")
    @Min(value = 1, message = "doctorId不能小于1")
    private Integer doctorId;

    @NotBlank(message = "date不能为空")
    private String date;

    /**
     * 能够接待的最大人数：默认为时段人数 * 3
     * */
    @NotNull(message = "totalMaximum不能为空")
    @Range(min = 1, max = 100, message = "totalMaximum内容不正确")
    private Integer totalMaximum;

    /**
     * 指定时间段对应的人数：时段人数
     * */
    @NotNull(message = "slotMaximum不能为空")
    @Range(min = 1, max = 30, message = "slotMaximum内容不正确")
    private Integer slotMaximum;

    /**
     * 从08：00 - 17：00总共是15个时段：
     *   对每个时段进行编号：
     *      08：00 - 08：30 ->  1
     *      08：30 - 09：00 ->  2
     *      16：30 - 17: 00 -> 15
     *      前端会提交时段对应的编号记录后后端。
     *      最后前端显示的时候会按照编号进行时段的映射，然后显示。
     * */
    @NotEmpty(message = "slots不能为空")
    private Integer[] slots;
}
