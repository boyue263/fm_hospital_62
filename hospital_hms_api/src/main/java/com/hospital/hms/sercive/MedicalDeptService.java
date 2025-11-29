package com.hospital.hms.sercive;

import com.hospital.common.utils.PageUtils;
import com.hospital.hms.pojo.MedicalDept;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public interface MedicalDeptService {
    PageUtils selectConditionByPage(Map<String,Object> map);
    Integer insertMedicalDept(MedicalDept medicalDept);
    HashMap<String,Object> selectMedicalDeptById(Integer id);

    Integer updateMedicalDept(MedicalDept medicalDept);

    ArrayList<HashMap<String,Object>> selectAllMedicalDeptNameAndId();
    Integer deleteMedicalDeptById(Integer[] ids);
    Map<String,List<HashMap<String,Object>>> selectDeptAndSup();

}
