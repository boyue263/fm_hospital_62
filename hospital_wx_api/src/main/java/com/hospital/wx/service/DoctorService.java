package com.hospital.wx.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;


public interface DoctorService {
    HashMap selectDoctorInfoById(int id);
}
