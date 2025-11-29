package com.hospital.wx.controller.form;


import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UpdateCanRegisterForm {

    @NotNull(message = "open不能为空")
    private Boolean open;
}
