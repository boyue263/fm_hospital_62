package com.hospital.wx.dao;

import com.hospital.wx.pojo.PatientRegistration;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Mapper
public interface PatientRegistrationDao {
    long getTodayRegistrationCount(Map param);
    Integer hasRegisteredToday(Map param);
    int insert(PatientRegistration patientRegistration);
    int updatePaymentStatus(Map param);
    HashMap getDoctorScheduleIdByOutTradeNo(String outTradeNo);
    int closeUnpaidOrder(String outTradeNo);
    long selectRegistrationCount(Map param);
    ArrayList<HashMap> selectRegistrationByPage(Map param);
    HashMap selectRegistrationInfo(Map param);
}
