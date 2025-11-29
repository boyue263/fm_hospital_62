package com.hospital.wx.pojo;
import lombok.Data;

@Data
public class DoctorConsultationReport {
    private Integer id;
    private String uuid;
    private Integer patientCardId;
    private String diagnosis;
    private Integer subDeptId;
    private Integer doctorId;
    private Integer registrationId;
    private String rp;
}
