package com.hospital.wx.service.impl;

import com.hospital.wx.dao.DoctorDao;
import com.hospital.wx.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class DoctorServiceImpl implements DoctorService {
    @Autowired
    private DoctorDao doctorDao;
    @Override
    public HashMap selectDoctorInfoById(int id) {
        return doctorDao.selectDoctorInfoById(id);
    }
}
