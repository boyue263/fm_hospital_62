package com.hospital.wx.service;
import java.util.HashMap;
import java.util.Map;

public interface PatientUserService {
    Map<String, Object> loginOrRegister(String code, String nickname, String photo, String sex);

}
