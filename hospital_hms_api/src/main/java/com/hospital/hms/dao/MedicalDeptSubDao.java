package com.hospital.hms.dao;

import com.hospital.hms.pojo.MedicalDeptSub;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Mapper
public interface MedicalDeptSubDao {
    ArrayList<HashMap<String,Object>> selectMedicalDeptSubConditionByPage(HashMap<String,Object> map);
    long selectMedicalDeptSubConditionByPageCount(HashMap<String,Object> map);

    Integer insertMedicalDeptSub(MedicalDeptSub medicalDeptSub);

    HashMap<String,Object> selectMedicalDeptSubById(Integer id);
    Integer updateMedicalDeptSub(MedicalDeptSub medicalDeptSub);
}
