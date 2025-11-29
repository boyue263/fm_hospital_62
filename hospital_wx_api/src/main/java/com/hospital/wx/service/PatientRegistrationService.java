package com.hospital.wx.service;

import com.hospital.wx.utils.PageUtils;

import java.util.HashMap;
import java.util.Map;

public interface PatientRegistrationService {
    String checkRegisterCondition(Map param);
    HashMap registerMedicalAppointment(Map param);
    void updatePaymentStatus(Map param);
    PageUtils selectRegistrationByPage(Map param);
    HashMap selectRegistrationInfo(Map param);
}
