package com.hospital.wx.service.impl;

import cn.hutool.core.map.MapUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
import com.hospital.wx.dao.PatientUserInfoDao;
import com.hospital.wx.pojo.PatientUserInfo;
import com.hospital.wx.service.PatientUserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;

@Service
@Slf4j
public class PatientUserInfoServiceImpl implements PatientUserInfoService {
    @Autowired
    PatientUserInfoDao patientUserInfoDao;
    @Override
    @Transactional
    public void insert(PatientUserInfo patientUserInfo) {
        patientUserInfoDao.insert(patientUserInfo);
    }

    @Override
    public HashMap selectPatientUserInfoByUserId(int userId) {
        HashMap map = patientUserInfoDao.selectPatientUserInfoByUserId(userId);
        if (map != null) {
            //将medicalHistory转换为json数组
            String medicalHistory = MapUtil.getStr(map, "medicalHistory");
            JSONArray array = JSONUtil.parseArray(medicalHistory);
            map.replace("medicalHistory", array);
        }
        return map;
    }

    @Override
    @Transactional
    public void update(PatientUserInfo patientUserInfo) {
        patientUserInfoDao.update(patientUserInfo);
    }

    @Override
    public Boolean checkPatientExistsByUserId(int userId) {
        return patientUserInfoDao.checkPatientExistsByUserId(userId) != null;
    }
}
