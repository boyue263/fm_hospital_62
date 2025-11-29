package com.hospital.wx.quartz;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.IdUtil;
//import com.hospital.wx.dao.UserDao;
import com.hospital.wx.socket.WebSocketService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Component
@Slf4j
public class VideoDiagnosisJob extends QuartzJobBean {
    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {

    }
//
//    @Resource
//    private RedisTemplate redisTemplate;
//
//    @Resource
//    private UserDao userDao;
//
//    @Override
//    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
//        // 获取所有医生上线缓存
//        Set<String> keys = redisTemplate.keys("online_doctor_*");
//
//        // 遍历每个上线缓存
//        keys.forEach(key -> {
//            int doctorId = Integer.parseInt(key.substring(key.lastIndexOf("_") + 1));
//            int userId = userDao.selectUserIdByRefId(new HashMap<String, Object>() {{
//                put("refId", doctorId);
//                put("job", "医生");
//            }});
//
//            Map<String, Object> entries = redisTemplate.opsForHash().entries(key);
//            String currentOrder = MapUtil.getStr(entries, "currentOrder");
//            String nextOrder = MapUtil.getStr(entries, "nextOrder");
//            boolean currentHandle = MapUtil.getBool(entries, "currentHandle");
//            boolean currentPayment = MapUtil.getBool(entries, "currentPayment");
//            boolean nextPayment = MapUtil.getBool(entries, "nextPayment");
//            String nextPatient = MapUtil.getStr(entries, "nextPatient");
//            String nextStart = MapUtil.getStr(entries, "nextStart");
//            String nextEnd = MapUtil.getStr(entries, "nextEnd");
//            boolean currentNotify = MapUtil.getBool(entries, "currentNotify");
//            boolean nextNotify = MapUtil.getBool(entries, "nextNotify");
//
//            // 如果没有当前问诊，就把排队已付款的问诊提到当前问诊
//            if ("none".equals(currentOrder) && !"none".equals(nextOrder) && nextPayment) {
//                // 把等候的问诊换成当前问诊
//                entries.replace("currentPatient", nextPatient);
//                entries.replace("currentOrder", nextOrder);
//                entries.replace("currentHandle", false);
//                entries.replace("currentStart", nextStart);
//                entries.replace("currentEnd", nextEnd);
//                entries.replace("currentPayment", nextPayment);
//                entries.replace("currentNotify", false);
//                entries.replace("currentStatus", 1);
//
//                // 清理等候问诊的缓存
//                entries.replace("nextPatient", "none");
//                entries.replace("nextOrder", "none");
//                entries.replace("nextStart", null);
//                entries.replace("nextEnd", null);
//                entries.replace("nextPayment", false);
//                entries.replace("nextNotify", false);
//
//                redisTemplate.opsForHash().putAll(key, entries);
//            }
//
//            // 患者已付款并且问诊缓存未处理（未创建缓存定时器）
//            if (!currentHandle && currentPayment) {
//                String currentStart = MapUtil.getStr(entries, "currentStart");
//                String currentEnd = MapUtil.getStr(entries, "currentEnd");
//                DateTime startTime = new DateTime(currentStart);
//                DateTime endTime = new DateTime(currentEnd);
//
//                // 创建视频问诊开始时间的缓存
//                String startKey = "video_diagnose_start_" + currentOrder + "#" + doctorId;
//                redisTemplate.opsForValue().set(startKey, currentStart);
//                redisTemplate.expireAt(startKey, startTime);
//
//                // 生成UUID的RoomId，然后保存到Redis缓存里面
//                String roomId = IdUtil.simpleUUID().toUpperCase();
//                redisTemplate.opsForHash().put(key, "roomId", roomId);
//
//                // 创建视频问诊结束时间的缓存
//                String endKey = "video_diagnose_end_" + currentOrder + "#" + doctorId;
//                redisTemplate.opsForValue().set(endKey, currentEnd);
//                redisTemplate.expireAt(endKey, endTime);
//
//                // 标记已处理缓存
//                redisTemplate.opsForHash().put(key, "currentHandle", true);
//            }
//
//            // 如果当前问诊付款成功，并且还未推送给医生端，就发出推送消息
//            if (currentPayment && !currentNotify) {
//                WebSocketService.sendInfo("RefreshDiagnose", String.valueOf(userId));
//                redisTemplate.opsForHash().put(key, "currentNotify", true); // 标记已推送
//            }
//
//            // 如果排队问诊付款成功，并且还未推送给医生端，就发出推送消息
//            if (nextPayment && !nextNotify) {
//                WebSocketService.sendInfo("RefreshDiagnose", String.valueOf(userId));
//                redisTemplate.opsForHash().put(key, "nextNotify", true); // 标记已推送
//            }
//        });
//    }
}
