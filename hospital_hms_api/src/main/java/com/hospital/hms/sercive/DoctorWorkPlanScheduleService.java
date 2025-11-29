package com.hospital.hms.sercive;

import com.hospital.hms.pojo.DoctorWorkPlanSchedule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public interface DoctorWorkPlanScheduleService {
    void insert(ArrayList<DoctorWorkPlanSchedule> list);
    ArrayList<HashMap<String,Object>> selectDoctorScheduleByDeptSubIdAndDate(Map<String,Object> param);
    HashMap<String,Object> selectDoctorScheduleByWorkPlanId(Integer workPlanId);
    void updateSchedule(Map param);
    void deleteWorkPlan(int workPlanId);
    void deleteByWorkPlanId(int workPlanId);
}
