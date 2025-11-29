package com.hospital.hms.controller.form;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Schema(description = "添加科室表单")
@Data
public class InsertMedicalDeptForm {
    @NotBlank(message = "name不能为空")
    @Pattern(regexp = "^[a-zA-Z0-9\\u4e00-\\u9fa5]{2,10}$",message = "name内容不正确")
    private String name;

    @NotNull(message = "outpatient不能为空")
    private Boolean outpatient;

    @NotNull(message = "recommended不能为空")
    private Boolean recommended;

    @NotNull(message = "description不能为空")
    private String description;

}
