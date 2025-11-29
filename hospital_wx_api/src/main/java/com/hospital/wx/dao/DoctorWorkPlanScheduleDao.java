package com.hospital.wx.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Mapper
public interface DoctorWorkPlanScheduleDao {
    ArrayList<HashMap> selectDoctorWorkPlanSchedule(Map param);
    int updateNumById(Map param);
    int releaseRegistrationLock(String outTradeNo);
}
