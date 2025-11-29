package com.hospital.hms.dao;

import com.hospital.hms.pojo.MedicalDept;
import org.apache.ibatis.annotations.Mapper;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Mapper
public interface MedicalDeptDao {
    List<HashMap<String,Object>> selectConditionByPage(Map<String,Object> map);

    Long selectConditionByPageCount(Map<String,Object> map);

    Integer insertMedicalDept(MedicalDept medicalDept);
    HashMap<String,Object> selectMedicalDeptById(Integer id);
    Integer updateMedicalDept(MedicalDept medicalDept);
    ArrayList<HashMap<String,Object>> selectAllMedicalDeptNameAndId();
    Integer deleteMedicalDeptById(Integer[] ids);
    boolean selectMedicalDeptCanDelete(Integer[] ids);
    ArrayList<HashMap<String,Object>> selectDeptAndSup();

}
