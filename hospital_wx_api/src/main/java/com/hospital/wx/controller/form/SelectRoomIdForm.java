package com.hospital.wx.controller.form;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@Schema(description = "xxx")
public class SelectRoomIdForm {

    @NotNull(message = "doctorId不能为空")
    @Min(value = 1, message = "doctorId不能小于1")
    private Integer doctorId;
}
