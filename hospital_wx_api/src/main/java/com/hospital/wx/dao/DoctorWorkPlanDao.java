package com.hospital.wx.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Mapper
public interface DoctorWorkPlanDao {
    ArrayList<String> selectDoctorScheduleBySubIdAndDate(Map param);
    ArrayList<HashMap> getDoctorShiftDetailsBySubIdAndDate(Map param);
    int updateNumById(Map param);
    int releaseRegistrationLock(String outTradeNo);
}
