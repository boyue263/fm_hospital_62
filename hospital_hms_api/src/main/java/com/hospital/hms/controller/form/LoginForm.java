package com.hospital.hms.controller.form;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@Schema(description = "登录表单")
public class LoginForm {

    @Schema(description = "用户名")
    @NotBlank(message = "姓名不能为空")
    @Pattern(regexp = "^[a-zA-Z0-9]{4,15}$",message = "姓名不符合格式要求")
    private String username;


    @Schema(description = "密码")
    @NotBlank(message = "密码不能为空")
    @Pattern(regexp = "^[a-zA-Z0-9]{4,15}$",message = "密码不符合格式要求")
    private String password;
}
