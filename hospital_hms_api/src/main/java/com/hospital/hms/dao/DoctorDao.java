package com.hospital.hms.dao;

import com.hospital.hms.pojo.Doctor;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Mapper
public interface DoctorDao {
    ArrayList<Map<String,Object>> selectDoctorByPage(Map<String,Object> map);
    Long selectDoctorCount(Map<String,Object> map);
    Integer insert(Doctor doctor);
    HashMap<String,Object> selectDoctorDetailById(Integer id);
    HashMap<String,Object> selectById(Integer id);
    void update(Map<String,Object> map);
    void deleteByIds(Integer[] ids);
    ArrayList<HashMap<String,Object>> selectDoctorBySubId(int subId);
    void updatePicture(Map<String,Object> map);
}
