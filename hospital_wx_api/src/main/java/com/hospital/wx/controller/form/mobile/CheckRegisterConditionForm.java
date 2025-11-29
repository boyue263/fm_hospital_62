package com.hospital.wx.controller.form.mobile;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Schema(description = "校验是否满足挂号条件表单")
public class CheckRegisterConditionForm {

    /**
     * 前端无需传递，后端可以获取到
     * */
    private Integer userId;

    @NotBlank(message = "date不能为空")
    private String date;

    @NotNull(message = "deptSubId不能为空")
    @Min(value = 1, message = "deptSubId不能小于1")
    private int deptSubId;
}
