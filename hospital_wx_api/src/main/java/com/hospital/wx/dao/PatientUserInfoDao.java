package com.hospital.wx.dao;
import com.hospital.wx.pojo.PatientUserInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.Map;

@Mapper
public interface PatientUserInfoDao {



    String selectTelById(int userId);
    int insert(PatientUserInfo patientUserInfo);
    HashMap selectPatientUserInfoByUserId(int userId);
    int update(PatientUserInfo entity);
    Integer checkPatientExistsByUserId(int userId);
    Boolean existsFaceModel(int userId);
    void updateExistFaceModel(Map param);
}