package com.hospital.hms.sercive.impl;

import com.hospital.common.utils.PageUtils;
import com.hospital.hms.dao.MedicalDeptSubDao;
import com.hospital.hms.pojo.MedicalDeptSub;
import com.hospital.hms.sercive.MedicalDeptSubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;

@Service
public class MedicalDeptSubServiceImpl implements MedicalDeptSubService {
    @Autowired
    MedicalDeptSubDao medicalDeptSubDao;

    @Override
    public Integer insertMedicalDeptSub(MedicalDeptSub medicalDeptSub) {
        return medicalDeptSubDao.insertMedicalDeptSub(medicalDeptSub);
    }

    @Override
    public PageUtils selectMedicalDeptSubConditionByPage(HashMap<String, Object> map) {
        ArrayList list = medicalDeptSubDao.selectMedicalDeptSubConditionByPage(map);
        Long count = medicalDeptSubDao.selectMedicalDeptSubConditionByPageCount(map);
        Integer start = (Integer) map.get("start");
        Integer length = (Integer) map.get("length");
        PageUtils pageUtils = new PageUtils(list,count,start,length);
        return pageUtils;

    }

    @Override
    public HashMap<String, Object> selectMedicalDeptSubById(Integer id) {
        return medicalDeptSubDao.selectMedicalDeptSubById(id);
    }

    @Override
    public Integer updateMedicalDeptSub(MedicalDeptSub medicalDeptSub) {
        return medicalDeptSubDao.updateMedicalDeptSub(medicalDeptSub);
    }
}
