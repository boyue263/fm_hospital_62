package com.hospital.wx.controller.form;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
@Schema(description = "根据ID删除诊室表单")
public class DeleteMedicalDeptSubByIdsForm {

    @NotEmpty(message = "ids不能为空")
    private Integer[] ids;

}