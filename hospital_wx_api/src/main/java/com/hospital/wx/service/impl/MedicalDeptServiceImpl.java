package com.hospital.wx.service.impl;

import com.hospital.wx.dao.MedicalDeptDao;
import com.hospital.wx.service.MedicalDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service
public class MedicalDeptServiceImpl implements MedicalDeptService {
    @Autowired
    MedicalDeptDao medicalDeptDao;
    @Override
    public ArrayList<HashMap> selectMedicalDeptList(Map param) {
        return medicalDeptDao.selectMedicalDeptList(param);
    }
}
