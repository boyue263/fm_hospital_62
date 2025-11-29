package com.hospital.wx.controller.form.mobile;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@Schema(description = "")
public class SelectPaymentResultForm {

    @NotBlank(message = "outTradeNo不能为空")
    @Pattern(regexp = "^[a-zA-Z0-9]{32}$", message = "outTradeNo内容不正确")
    private String outTradeNo;
}
