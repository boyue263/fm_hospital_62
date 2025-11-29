package com.hospital.wx.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public interface DoctorWorkPlanService {
    ArrayList<String> selectDoctorScheduleBySubIdAndDate(Map param);
    ArrayList<HashMap> getDoctorShiftDetailsBySubIdAndDate(Map param);
}
