package com.hospital.wx.controller.form.mobile;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@Schema(description = "xxx")
public class SelectImageByVideoDiagnoseIdForm {
    @NotNull(message = "videoDiagnoseId不能为空")
    @Min(value = 1, message = "videoDiagnoseId不能小于1")
    private Integer videoDiagnoseId;
}
