package com.hospital.hms.sercive.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.map.MapUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
import com.hospital.common.exception.GlobalException;
import com.hospital.hms.dao.DoctorWorkPlanDao;
import com.hospital.hms.dao.DoctorWorkPlanScheduleDao;
import com.hospital.hms.pojo.DoctorWorkPlan;
import com.hospital.hms.pojo.DoctorWorkPlanSchedule;
import com.hospital.hms.sercive.DoctorWorkPlanScheduleService;
import com.hospital.hms.sercive.DoctorWorkPlanService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Slf4j
@Service
public class DoctorWorkPlanServiceImpl implements DoctorWorkPlanService {
    @Autowired
    private DoctorWorkPlanDao doctorWorkPlanDao;
    @Autowired
    private DoctorWorkPlanScheduleService doctorWorkPlanScheduleService;
    @Transactional
    @Override
    public String insertDoctorWorkPlan(Map<String, Object> param) {

        try {
            Integer id = doctorWorkPlanDao.checkDoctorWorkPlanForToday(param);
            if (id != null) {
                log.info("该医生已存在出诊计划");
                return "该医生已存在出诊计划";
            }
            DoctorWorkPlan doctorWorkPlan = BeanUtil.toBean(param, DoctorWorkPlan.class);
            doctorWorkPlan.setMaximum(Integer.parseInt(param.get("totalMaximum").toString()));
            doctorWorkPlanDao.insert(doctorWorkPlan);
            log.info("医生的出诊计划插入成功，出诊计划ID: {}",doctorWorkPlan.getId());
            Integer[] slots = (Integer[]) param.get("slots");
            int slotMaximum = Integer.parseInt(param.get("slotMaximum").toString());
            ArrayList<DoctorWorkPlanSchedule> scheduleList = new ArrayList<>();
            for(Integer slot : slots) {
                DoctorWorkPlanSchedule schedule = BeanUtil.toBean(param, DoctorWorkPlanSchedule.class);
                schedule.setWorkPlanId(doctorWorkPlan.getId());
                schedule.setSlot(slot);
                schedule.setMaximum(slotMaximum);
                scheduleList.add(schedule);

            }
            doctorWorkPlanScheduleService.insert(scheduleList);
        } catch (NumberFormatException e) {
            log.error("插入医生出诊计划或出诊时间段时发生错误", e);
            throw new GlobalException("插入医生出诊计划或出诊时间段失败", e);
        }
        return "";
    }

    @Override
    public JSONArray selectWorkPlanByTime(Map param, ArrayList dateList) {
        ArrayList<HashMap> list = doctorWorkPlanDao.selectWorkPlanByTime(param);
        Map<Integer, HashMap> tempResult = new HashMap<>();
        for (HashMap one : list) {
            // 从查询结果中获取相关字段
            String deptName = MapUtil.getStr(one, "deptName");
            int deptSubId = MapUtil.getInt(one, "deptSubId");
            String deptSubName = MapUtil.getStr(one, "deptSubName");
            String doctorName = MapUtil.getStr(one, "doctorName");
            String date = MapUtil.getStr(one, "date");
            if (!tempResult.containsKey(deptSubId)) {
                HashMap temp = new HashMap() {{
                    put("deptName", deptName); // 存储科室名称
                    put("deptSubId", deptSubId); // 存储子科室ID
                    put("deptSubName", deptSubName); // 存储子科室名称
                    put("plan", new LinkedHashMap<>() {{ // 存储日期对应的医生排班
                        put(date, new ArrayList<>() {{ // 初始化当前日期的医生列表
                            add(doctorName);
                        }});
                    }});
                }};
                tempResult.put(deptSubId, temp); // 将当前科室的计划存入tempResult
            } else {
                // 若已存在该deptSubId，则获取对应的计划
                HashMap map = tempResult.get(deptSubId);

                //结构如下： "2025-02-13": ["韩倩倩", "赵六", "李雨萌"]
                LinkedHashMap plan = (LinkedHashMap) map.get("plan");

                // 若当前日期不存在，则初始化一个医生列表
                if (!plan.containsKey(date)) {
                    plan.put(date, new ArrayList<>());
                }

                // 获取当前日期的医生列表，并添加新的医生
                ArrayList doctors = (ArrayList) plan.get(date);
                doctors.add(doctorName);
            }
        }
        tempResult.values().forEach(map -> {
            /**
             *     "plan": {
             *       "2025-02-13": ["韩倩倩", "赵六", "李雨萌"],
             *       "2025-02-14": ["赵六", "韩倩倩"],
             *       "2025-02-15": ["韩倩倩"]
             *     }
             *
             * */
            LinkedHashMap plan = (LinkedHashMap) map.get("plan");
            /**
             * "plan": {
             *          *       "2025-02-13": ["韩倩倩", "赵六", "李雨萌"],
             *          *       "2025-02-14": ["赵六", "韩倩倩"],
             *          *       "2025-02-15": ["韩倩倩"],
             *          *       "2025-02-16": [],
             *          *       "2025-02-17": [],
             *          *       "2025-02-18": [],
             *          *       "2025-02-19": [],
             *          *       "2025-02-20": []
             *          *     }
             * */
            dateList.forEach(date -> plan.putIfAbsent(date, new ArrayList<>()));
        });
        tempResult.values().forEach(map -> {
            /**
             * // 使用TreeMap按日期升序排序
             * TreeMap<String, ArrayList> sortedPlan = new TreeMap<>((d1, d2) -> new DateTime(d1).isAfter(new DateTime(d2)) ? 1 : -1);
             * 这里创建了一个 TreeMap，目的是按日期对排班进行升序排序。
             *
             * TreeMap 是一个自排序的 Map，它根据键（这里是日期字符串）进行排序。默认情况下，TreeMap 会按字典顺序排序，但我们传入了一个自定义比较器，来控制日期的排序方式。
             * 传入的比较器逻辑是：
             * new DateTime(d1).isAfter(new DateTime(d2)) ? 1 : -1：如果 d1 的日期晚于 d2，就返回 1（即 d1 在 d2 后面）；否则返回 -1（即 d1 在 d2 前面）。这个比较器确保了日期按升序排列。
             * */
            TreeMap<String, ArrayList> sortedPlan = new TreeMap<>((d1, d2) -> new DateTime(d1).isAfter(new DateTime(d2)) ? 1 : -1);
            /**
             * 排序前的结果：
             * "plan": {
             *          *       "2025-02-17": [],
             *          *       "2025-02-16": [],
             *          *     }
             * */
            LinkedHashMap plan = (LinkedHashMap) map.get("plan");
            /**
             * 排序后的结果：
             * "plan": {
             *          *       "2025-02-16": [],
             *          *       "2025-02-17": [],
             *          *     }
             * */
            sortedPlan.putAll(plan);
            map.replace("plan", new ArrayList<>());
            sortedPlan.forEach((date, doctors) -> {
                ((ArrayList) map.get("plan")).add(new HashMap<String, Object>() {{
                    put("date", date); // 存储日期
                    put("doctors", doctors); // 存储该日期的医生列表
                }});
            });
        });


        return JSONUtil.parseArray(tempResult.values());
    }
}
