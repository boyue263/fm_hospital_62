package com.hospital.hms.dao;

import com.hospital.hms.pojo.DoctorWorkPlanSchedule;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Mapper
public interface DoctorWorkPlanScheduleDao {
    void insert(DoctorWorkPlanSchedule entity);
    ArrayList<HashMap<String,Object>> selectScheduleByWorkPlanId(int workPlanId);
    ArrayList<HashMap<String,Object>> selectDoctorScheduleByDeptSubIdAndDate(Map<String,Object> param);
    ArrayList<HashMap<String,Object>> selectDoctorScheduleByWorkPlanId(Integer workPlanId);
    void deleteByIds(ArrayList<Integer> ids);
    long selectSumNumByIds(ArrayList<Integer> ids);
    void deleteByWorkPlanId(int workPlanId);
}
