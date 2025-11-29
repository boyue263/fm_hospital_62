package com.hospital.hms.controller.form;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@Schema(description = "患者登录表单")
public class LoginOrRegisterForm {

    /**
     * 微信登录临时授权码
     * */
    @NotBlank(message = "code不能为空")
    private String code;

    /**
     * 微信名称
     * */
    @NotBlank(message = "nickname不能为空")
    private String nickname;

    /**
     * 微信的头像
     * */
    @NotBlank(message = "photo不能为空")
    private String photo;

    @NotBlank(message = "sex不能为空")
    @Pattern(regexp = "^男$|^女$",message = "sex内容不正确")
    private String sex;
}
