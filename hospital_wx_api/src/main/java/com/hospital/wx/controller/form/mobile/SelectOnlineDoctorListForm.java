package com.hospital.wx.controller.form.mobile;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.Pattern;

@Data
@Schema(description = "xxx表单")
public class SelectOnlineDoctorListForm {
    private String subName;
    @Pattern(regexp = "^主任医师$|^副主任医师$|^主治医师$|^副主治医师$", message = "job参数错误")
    private String job;
}
