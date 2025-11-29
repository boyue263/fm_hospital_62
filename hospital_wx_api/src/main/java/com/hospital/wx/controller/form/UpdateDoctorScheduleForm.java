package com.hospital.wx.controller.form;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;

@Schema(description = "修改医生出诊信息表单")
@Data
public class UpdateDoctorScheduleForm {

    @NotNull(message = "workPlanId不能为空")
    @Min(value = 1, message = "workPlanId内容不正确")
    private Integer workPlanId;

    @NotNull(message = "maximum不能为空")
    @Range(min = 1, max = 30, message = "maximum内容不正确")
    private Integer maximum;

    @Valid
    @NotEmpty(message = "slots不能为空")
    private ArrayList<DoctorScheduleSlotVO> slots;


}
