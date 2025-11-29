package com.hospital.hms.pojo;
import lombok.Data;

@Data
public class PatientUser {
    private Integer id;
    private String openId;
    private String nickname;
    private String photo;
    private String sex;
    private byte status;
    private String createTime;
}
