package com.hospital.wx.controller.form;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@Schema(description = "根据诊室ID查询医生")
public class SelectDoctorsBySubIdForm {

    @NotNull(message = "id不能为空")
    @Schema(description = "诊室编号")
    private Integer deptSubId;
}
