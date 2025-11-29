package com.hospital.wx.controller.form.mobile;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@Schema(description = "人脸识别表单")
public class CreateFaceModelForm {
    private Integer userId;

    @NotBlank(message = "photo不能为空")
    private String photo;
}
