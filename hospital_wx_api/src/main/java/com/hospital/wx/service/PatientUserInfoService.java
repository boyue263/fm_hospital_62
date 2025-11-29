package com.hospital.wx.service;
import com.hospital.wx.pojo.PatientUserInfo;

import java.util.HashMap;

public interface PatientUserInfoService {
    void insert(PatientUserInfo patientUserInfo);
    HashMap selectPatientUserInfoByUserId(int userId);
    void update(PatientUserInfo entity);
    Boolean checkPatientExistsByUserId(int userId);

}
