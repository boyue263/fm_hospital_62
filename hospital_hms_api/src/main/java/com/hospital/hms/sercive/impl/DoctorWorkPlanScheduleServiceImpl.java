package com.hospital.hms.sercive.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.map.MapUtil;
import com.hospital.common.exception.GlobalException;
import com.hospital.hms.common.CommonResult;
import com.hospital.hms.dao.DoctorWorkPlanDao;
import com.hospital.hms.dao.DoctorWorkPlanScheduleDao;
import com.hospital.hms.pojo.DoctorWorkPlanSchedule;
import com.hospital.hms.sercive.DoctorWorkPlanScheduleService;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import com.hospital.hms.controller.form.DoctorScheduleSlotVO;

import java.util.*;

@Service
@Slf4j
public class DoctorWorkPlanScheduleServiceImpl implements DoctorWorkPlanScheduleService {
    private String WORK_PLAN_SCHEDULE_KEY = "work_plan_schedule_";
    private static final Map<String, String> APPOINTMENT_SLOT = Map.ofEntries(
            new AbstractMap.SimpleEntry<>("1", "08:00"),
            new AbstractMap.SimpleEntry<>("2", "08:30"),
            new AbstractMap.SimpleEntry<>("3", "09:00"),
            new AbstractMap.SimpleEntry<>("4", "09:30"),
            new AbstractMap.SimpleEntry<>("5", "10:00"),
            new AbstractMap.SimpleEntry<>("6", "10:30"),
            new AbstractMap.SimpleEntry<>("7", "11:00"),
            new AbstractMap.SimpleEntry<>("8", "11:30"),
            new AbstractMap.SimpleEntry<>("9", "13:00"),
            new AbstractMap.SimpleEntry<>("10", "13:30"),
            new AbstractMap.SimpleEntry<>("11", "14:00"),
            new AbstractMap.SimpleEntry<>("12", "14:30"),
            new AbstractMap.SimpleEntry<>("13", "15:00"),
            new AbstractMap.SimpleEntry<>("14", "15:30"),
            new AbstractMap.SimpleEntry<>("15", "16:00")
    );
    @Autowired
    private DoctorWorkPlanScheduleDao doctorWorkPlanScheduleDao;
    @Autowired
    private DoctorWorkPlanDao doctorWorkPlanDao;
    @Autowired
    RedisTemplate redisTemplate;
    @Override
    public void insert(ArrayList<DoctorWorkPlanSchedule> list) {
        insertDoctorWorkPlanSchedule(list);
        //避免患者端挂号出现超售的现象，将诊计划保存到redis中
        addScheduleCacheToRedis(list);
    }
    private void insertDoctorWorkPlanSchedule(ArrayList<DoctorWorkPlanSchedule> list) {
        for (DoctorWorkPlanSchedule entity : list) {
            doctorWorkPlanScheduleDao.insert(entity);
        }
    }
    private void addScheduleCacheToRedis(ArrayList<DoctorWorkPlanSchedule> list) {
        // 如果list为空，直接返回
        if (CollectionUtils.isEmpty(list)) {
            return;
        }
        //获取WorkPlanId
        int workPlanId = list.get(0).getWorkPlanId();
        // 查询数据库记录，避免在循环中多次查询数据库
        ArrayList<HashMap<String,Object>> scheduleList = doctorWorkPlanScheduleDao.selectScheduleByWorkPlanId(workPlanId);
        if (CollectionUtils.isEmpty(scheduleList)) {
            return; // 如果查询结果为空，直接返回
        }
        // 遍历查询结果并缓存到 Redis
        for (HashMap schedule : scheduleList) {
            cacheScheduleToRedis(schedule);
        }
    }
    private void cacheScheduleToRedis(HashMap schedule) {
        // 从map中获取必要的数据
        int id = MapUtil.getInt(schedule, "scheduleId");
        int slot = MapUtil.getInt(schedule, "slot");
        String date = MapUtil.getStr(schedule, "date");
        // 定义缓存记录的Key
        String key = WORK_PLAN_SCHEDULE_KEY + id;
        // 将出诊时间段缓存到Redis
        redisTemplate.opsForHash().putAll(key, schedule);
        // 获取时间段对应的起始时间
        String time = APPOINTMENT_SLOT.get(String.valueOf(slot));
        // 设置缓存过期时间，保存的单位是秒
        setCacheExpiration(key, date, time);
    }
    private void setCacheExpiration(String key, String date, String time) {
        // 解析出诊时间，设置缓存过期时间
        Date expirationTime = DateUtil.parse(date + " " + time);
        redisTemplate.expireAt(key, expirationTime);
    }

    @Override
    public ArrayList<HashMap<String, Object>> selectDoctorScheduleByDeptSubIdAndDate(Map<String, Object> param) {
        ArrayList<HashMap<String,Object>> list = doctorWorkPlanScheduleDao.selectDoctorScheduleByDeptSubIdAndDate(param);
        LinkedHashMap<Integer,HashMap<String,Object>> doctorMap = new LinkedHashMap<>();

        for (HashMap<String,Object> map : list) {
            int doctorId = MapUtil.getInt(map, "doctorId");
            int slot = MapUtil.getInt(map, "slot");

            HashMap<String,Object> doctor = doctorMap.computeIfAbsent(doctorId, k -> {
                HashMap<String,Object> newDoctor = new HashMap<>(map);
                newDoctor.put(("slot"),new ArrayList<Boolean>(Collections.nCopies(15,Boolean.FALSE)));
                return newDoctor;
            });

            ArrayList<Boolean> slotList = (ArrayList<Boolean>) doctor.get("slot");
            if (slot>=1&&slot<=15) {
                slotList.set(slot-1,Boolean.TRUE);
            }
        }

        return new ArrayList<>(doctorMap.values());
    }

    @Override
    public HashMap<String,Object> selectDoctorScheduleByWorkPlanId(Integer workPlanId) {
        ArrayList<HashMap<String,Object>> list =doctorWorkPlanScheduleDao.selectDoctorScheduleByWorkPlanId(workPlanId);
//        {"date":"2025-02-18","num":0,"slot":4,"doctorId":88,"maximum":3,"scheduleId":584},
//        {"date":"2025-02-18","num":0,"slot":5,"doctorId":88,"maximum":3,"scheduleId":587},
//        {"date":"2025-02-18","num":0,"slot":6,"doctorId":88,"maximum":3,"scheduleId":588}
        if(list.isEmpty()){
            return new HashMap<>();
        }
        HashMap<String,Object> result = new  HashMap<>();
        ArrayList<HashMap<String,Object>> temp = new ArrayList<>();
        HashMap<String,Object> first = list.get(0);
        result.put("doctorId",MapUtil.getInt(first, "doctorId"));
        result.put(("maximum"),MapUtil.getInt(first, "maximum"));
        for(HashMap<String,Object> map : list){
            temp.add(new HashMap<>(){{
                put("scheduleId",MapUtil.getInt(map, "scheduleId"));
                put("slot",MapUtil.getInt(map, "slot"));
                put("num",MapUtil.getInt(map, "num"));
            }});
        }
        result.put("slots",temp);


        return  result;
    }

    @Override
    public void updateSchedule(Map param) {
        //删除或者添加出诊时段
        ArrayList<DoctorWorkPlanSchedule> addList = insertOrDeleteScheduleHandle(param);
        //把新添加的出诊时段缓存到Redis
        addScheduleCacheToRedis(addList);

    }



    private ArrayList<DoctorWorkPlanSchedule> insertOrDeleteScheduleHandle(Map param) {
        // 更新接诊人数上限
        doctorWorkPlanDao.updateMaximum(param);

        Integer workPlanId = MapUtil.getInt(param, "workPlanId");

        // 待处理的时间段列表（添加或者删除）
        ArrayList<DoctorScheduleSlotVO> slots = (ArrayList<DoctorScheduleSlotVO>) param.get("slots");

        // 用于存放添加时间段的列表
        ArrayList<DoctorWorkPlanSchedule> addList = new ArrayList<>();
        // 用于存放删除时间段的列表
        ArrayList<Integer> removeList = new ArrayList<>();

        // 分析哪些是添加时间段，哪些是删除时间段
        slots.forEach(slot -> {
            if ("insert".equals(slot.getOperate())) {
                DoctorWorkPlanSchedule entity = new DoctorWorkPlanSchedule();
                entity.setWorkPlanId(workPlanId);
                entity.setMaximum(slot.getMaximum());
                entity.setSlot(slot.getSlot());
                addList.add(entity); // 时间段保存到添加列表
            } else {
                removeList.add(slot.getScheduleId()); // 时间段保存到删除列表
            }
        });

        // 判断删除列表是否有元素
        if (!removeList.isEmpty()) {
            // 查询待删除时间段的总挂号人数
            long sum = doctorWorkPlanScheduleDao.selectSumNumByIds(removeList);
            // 如果这些待删除的时间段总挂号人数大于 0，说明存在患者挂号，不能删除
            if (sum > 0) {
                throw new GlobalException("修改失败，本次修改与已经挂号记录冲突");
            }
            // 删除时间段记录
            doctorWorkPlanScheduleDao.deleteByIds(removeList);
            // 删除 Redis 上的时间段缓存
            removeList.forEach(scheduleId -> redisTemplate.delete(WORK_PLAN_SCHEDULE_KEY + scheduleId));
        }

        // 处理添加时间段列表
        insertDoctorWorkPlanSchedule(addList);
        return addList;
    }

    @Override
    public void deleteWorkPlan(int workPlanId) {
        Integer num =doctorWorkPlanDao.selectActualRegistrationCount(workPlanId);
        if (num != null && num > 0) {
            log.error("该出诊计划已经有患者挂号，禁止删除", workPlanId);
            throw new GlobalException("该出诊计划已经有患者挂号，禁止删除");
        }
        //1.删除出诊计划对应的时段
        deleteByWorkPlanId(workPlanId);
        //2.删除出诊计划数据
        doctorWorkPlanDao.deleteById(workPlanId);
    }
    @Override
    public void deleteByWorkPlanId(int workPlanId) {
        ArrayList<HashMap<String,Object>> list = doctorWorkPlanScheduleDao.selectDoctorScheduleByWorkPlanId(workPlanId);
        List<String> keys = new ArrayList<>();

        // 提取所有缓存 key
        list.forEach(one -> {
            int scheduleId = MapUtil.getInt(one, "scheduleId");
            String key = WORK_PLAN_SCHEDULE_KEY + scheduleId;
            keys.add(key);
        });
        // 删除时段记录信息
        doctorWorkPlanScheduleDao.deleteByWorkPlanId(workPlanId);
        // 批量删除 redis 缓存中的数据
        if (!keys.isEmpty()) {
            redisTemplate.delete(keys);
        }
    }
}
