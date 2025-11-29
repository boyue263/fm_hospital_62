package com.hospital.hms.sercive.impl;

import com.hospital.common.exception.GlobalException;
import com.hospital.common.utils.PageUtils;
import com.hospital.hms.dao.MedicalDeptDao;
import com.hospital.hms.pojo.MedicalDept;
import com.hospital.hms.sercive.MedicalDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MedicalDeptServiceImpl implements MedicalDeptService {
    @Autowired
    private MedicalDeptDao medicalDeptDao;
    @Override
    public PageUtils selectConditionByPage(Map<String, Object> map) {
        List<HashMap<String, Object>> list = medicalDeptDao.selectConditionByPage(map);
        Long count = medicalDeptDao.selectConditionByPageCount(map);
        Integer start = (Integer) map.get("start");
        Integer length = (Integer) map.get("length");
        PageUtils pageUtils = new PageUtils(list,count,start,length);
        return pageUtils;
    }

    @Override
    public Integer insertMedicalDept(MedicalDept medicalDept) {
        return medicalDeptDao.insertMedicalDept(medicalDept);
    }

    @Override
    public HashMap<String, Object> selectMedicalDeptById(Integer id) {
        return medicalDeptDao.selectMedicalDeptById(id);
    }

    @Override
    public Integer updateMedicalDept(MedicalDept medicalDept) {
        return medicalDeptDao.updateMedicalDept(medicalDept);
    }

    @Override
    public ArrayList<HashMap<String, Object>> selectAllMedicalDeptNameAndId() {
        return medicalDeptDao.selectAllMedicalDeptNameAndId();
    }

    @Override
    public Integer deleteMedicalDeptById(Integer[] ids) {
        if(!medicalDeptDao.selectMedicalDeptCanDelete(ids)){
            throw new GlobalException("有关联科室");
        }
        return medicalDeptDao.deleteMedicalDeptById(ids);
    }

    @Override
    public Map<String, List<HashMap<String, Object>>> selectDeptAndSup() {
        ArrayList<HashMap<String,Object>> list = medicalDeptDao.selectDeptAndSup();

        Map<String, List<HashMap<String, Object>>> map = new LinkedHashMap<>();
        for (HashMap<String,Object> map1 : list) {
            String deptName = (String) map1.get("deptName");
            Integer subId = (Integer) map1.get("subId");
            String subName = (String) map1.get("subName");
            HashMap<String,Object> subMap = new HashMap<>();
            subMap.put("subId",subId);
            subMap.put("subName",subName);
            map.computeIfAbsent(deptName, k -> new ArrayList<>()).add(subMap);
        }
        return map;
    }
}
