package com.hospital.wx.service.impl;

import com.hospital.wx.dao.MedicalDeptSubDao;
import com.hospital.wx.service.MedicalDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;

@Service
public class MedicalDeptSubService implements com.hospital.wx.service.MedicalDeptSubService {
    @Autowired
    private MedicalDeptSubDao medicalDeptSubDao;
    @Override
    public ArrayList<HashMap> selectMedicalDeptSubList(int deptId) {
        return medicalDeptSubDao.selectMedicalDeptSubList(deptId);
    }
}
