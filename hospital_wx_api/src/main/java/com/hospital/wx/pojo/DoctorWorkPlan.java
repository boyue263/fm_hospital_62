package com.hospital.wx.pojo;

import lombok.Data;

@Data
public class DoctorWorkPlan {
    private Integer id;
    private Integer doctorId;
    private Integer deptSubId;
    private String date;
    private Integer maximum;
    private Integer num;

}