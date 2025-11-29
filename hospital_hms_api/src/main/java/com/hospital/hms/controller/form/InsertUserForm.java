package com.hospital.hms.controller.form;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.*;

@Schema(description = "添加用户表单")
@Data
public class InsertUserForm {
    @NotBlank(message = "username不能为空")
    @Pattern(regexp = "^[a-zA-Z0-9]{5,20}$", message = "username内容不正确")
    @Schema(description = "用户名")
    private String username;

    @NotBlank(message = "password不能为空")
    @Pattern(regexp = "^[a-zA-Z0-9]{6,20}$", message = "password内容不正确")
    @Schema(description = "密码")
    private String password;

    @NotBlank(message = "name不能为空")
    @Pattern(regexp = "^[\\u4e00-\\u9fa5]{2,10}$", message = "name内容不正确")
    @Schema(description = "姓名")
    private String name;

    @NotBlank(message = "sex不能为空")
    @Pattern(regexp = "^男$|^女$", message = "sex内容不正确")
    @Schema(description = "性别")
    private String sex;

    @NotBlank(message = "tel不能为空")
    @Pattern(regexp = "^1\\d{10}$", message = "tel内容不正确")
    @Schema(description = "电话")
    private String tel;

    @NotBlank(message = "email内容不正确")
    @Email(message = "email内容不正确")
    @Schema(description = "邮箱")
    private String email;

    @NotBlank(message = "hiredate不能为空")
    @Pattern(regexp = "^((((1[6-9]|[2-9]\\d)\\d{2})-(0?[13578]|1[02])-(0?[1-9]|[12]\\d|3[01]))|(((1[6-9]|[2-9]\\d)\\d{2})-(0?[13456789]|1[012])-(0?[1-9]|[12]\\d|30))|(((1[6-9]|[2-9]\\d)\\d{2})-0?2-(0?[1-9]|1\\d|2[0-8]))|(((1[6-9]|[2-9]\\d)(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00))-0?2-29-))$", message = "hiredate内容不正确")
    @Schema(description = "入职日期")
    private String hiredate;

    @NotEmpty(message = "role不能为空")
    @Schema(description = "角色")
    private Integer[] role;

    @NotNull(message = "deptId不能为空")
    @Min(value = 1, message = "deptId不能小于1")
    @Schema(description = "部门")
    private Integer deptId;

}
