package com.hospital.hms.pojo;

import lombok.Data;

@Data
public class PatientUserInfo {
    private Integer id;
    private Integer userId;
    private String uuid;
    private String name;
    private String sex;
    private String pid;
    private String tel;
    private String birthday;
    private String medicalHistory;
    private String insuranceType;
    private Boolean existFaceModel;
}
