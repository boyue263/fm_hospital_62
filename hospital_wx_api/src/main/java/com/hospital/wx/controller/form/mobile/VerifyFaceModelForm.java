package com.hospital.wx.controller.form.mobile;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@Schema(description = "验证人脸识别表单")
public class VerifyFaceModelForm {

    @NotBlank(message = "photo不能为空")
    private String photo;
}
