package com.hospital.wx.service.impl;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateRange;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.map.MapUtil;
import com.hospital.wx.dao.DoctorWorkPlanDao;
import com.hospital.wx.service.DoctorWorkPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service
public class DoctorWorkPlanServiceImpl implements DoctorWorkPlanService {
    @Autowired
    private DoctorWorkPlanDao doctorWorkPlanDao;
    @Override
    public ArrayList<String> selectDoctorScheduleBySubIdAndDate(Map param) {
        ArrayList<String> list = doctorWorkPlanDao.selectDoctorScheduleBySubIdAndDate(param);
        DateTime startDate = DateUtil.parse(MapUtil.getStr(param, "startDate"));
        DateTime endDate = DateUtil.parse(MapUtil.getStr(param, "endDate"));
        DateRange range = DateUtil.range(startDate, endDate, DateField.DAY_OF_MONTH);
        ArrayList result = new ArrayList();
        while (range.hasNext()) {
            String date = range.next().toDateStr();
            if (list.contains(date)) {
                result.add(new HashMap() {{
                    put("date", date);
                    put("status", "出诊");
                }});
            } else {
                result.add(new HashMap() {{
                    put("date", date);
                    put("status", "无号");
                }});
            }
        }
        return result;
    }

    @Override
    public ArrayList<HashMap> getDoctorShiftDetailsBySubIdAndDate(Map param) {
        return doctorWorkPlanDao.getDoctorShiftDetailsBySubIdAndDate(param);
    }
}
