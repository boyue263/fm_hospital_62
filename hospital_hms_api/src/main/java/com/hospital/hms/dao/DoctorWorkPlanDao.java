package com.hospital.hms.dao;

import com.hospital.hms.pojo.DoctorWorkPlan;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Mapper
public interface DoctorWorkPlanDao {
    Integer checkDoctorWorkPlanForToday(Map<String, Object> doctorWorkPlan);
    void insert(DoctorWorkPlan doctorWorkPlan);
    ArrayList<HashMap> selectWorkPlanByTime(Map param);
    void updateMaximum(Map param);
    Integer selectActualRegistrationCount(int id);
    void deleteById(int id);
}
