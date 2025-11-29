package com.hospital.hms.pojo;

import lombok.Data;

@Data
public class DoctorWorkPlanSchedule {
    private Integer id;
    private Integer workPlanId;
    private Integer slot;
    private Integer maximum;
    private Integer num;

}