package com.hospital.hms.sercive;

import cn.hutool.json.JSONArray;
import com.hospital.hms.pojo.DoctorWorkPlan;

import java.util.ArrayList;
import java.util.Map;

public interface DoctorWorkPlanService {
    String insertDoctorWorkPlan(Map<String, Object> param);
    JSONArray selectWorkPlanByTime(Map param, ArrayList dateList);
}
