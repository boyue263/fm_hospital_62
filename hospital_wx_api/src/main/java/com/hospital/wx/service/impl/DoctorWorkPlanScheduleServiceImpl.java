package com.hospital.wx.service.impl;

import com.hospital.wx.dao.DoctorWorkPlanScheduleDao;
import com.hospital.wx.service.DoctorWorkPlanScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service
public class DoctorWorkPlanScheduleServiceImpl implements DoctorWorkPlanScheduleService {
    @Autowired
    DoctorWorkPlanScheduleDao doctorWorkPlanScheduleDao;
    @Override
    public ArrayList<HashMap> selectDoctorWorkPlanSchedule(Map param) {
        return doctorWorkPlanScheduleDao.selectDoctorWorkPlanSchedule(param);
    }
}
