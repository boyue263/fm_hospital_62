package com.hospital.wx.controller.form;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@Schema(description = "根据ID查询医生详细信息")
public class SelectDoctorDetailByIdForm {

    @NotNull(message = "id不能为空")
    @Min(value = 1,message = "id不能小于1")
    private Integer id;
}
