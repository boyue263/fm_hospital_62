package com.hospital.hms.controller.form;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.*;

@Schema(description = "修改患者信息表单")
@Data
public class UpdatePatientUserInfoForm {

    @NotNull(message = "id不能为空")
    @Min(value = 1, message = "id不能小于1")
    private Integer id;

    @NotBlank(message = "name不能为空")
    @Pattern(regexp = "^[\\u4e00-\\u9fa5]{2,15}$", message = "name内容不正确")
    private String name;

    @NotBlank(message = "sex不能为空")
    @Pattern(regexp = "^男$|^女$", message = "性别内容不正确")
    private String sex;

    @NotBlank(message = "pid不能为空")
    private String pid;

    @NotBlank(message = "tel不能为空")
    @Pattern(regexp = "^1[0-9]{10}$", message = "tel内容不正确")
    private String tel;

    @NotBlank(message = "birthday不能为空")
    private String birthday;

    @NotEmpty(message = "medicalHistory不能为空")
    private String[] medicalHistory;

    @NotBlank(message = "insuranceType不能为空")
    private String insuranceType;

}
