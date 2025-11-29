package com.hospital.hms.controller.form;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "查询所有科室列表表单")
public class SelectMedicalDeptListForm {
    private Boolean recommended;
    private Boolean outpatient;
}
