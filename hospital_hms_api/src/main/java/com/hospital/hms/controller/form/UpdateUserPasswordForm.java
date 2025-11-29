package com.hospital.hms.controller.form;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Schema(description = "修改用户密码")
@Data
public class UpdateUserPasswordForm {
    @Schema(description = "旧密码")
    @NotBlank(message = "旧密码不能为空")
    @Pattern(regexp = "^[a-zA-Z0-9]{4,15}$", message = "旧密码格式不正确")
    private String oldPassword;

    @Schema(description = "新密码")
    @NotBlank(message = "新密码不能为空")
    @Pattern(regexp = "^[a-zA-Z0-9]{4,15}$", message = "新密码格式不正确")
    private String newPassword;
}