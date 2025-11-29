//package com.hospital.wx.config;
//
//import cn.hutool.core.date.DateTime;
//import cn.hutool.core.map.MapUtil;
////import com.hospital.wx.dao.UserDao;
//import com.hospital.wx.dao.VideoDiagnosisDao;
//import com.hospital.wx.socket.WebSocketService;
//import com.tencentcloudapi.common.Credential;
//import com.tencentcloudapi.common.exception.TencentCloudSDKException;
//import com.tencentcloudapi.trtc.v20190722.TrtcClient;
//import com.tencentcloudapi.trtc.v20190722.models.DismissRoomByStrRoomIdRequest;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.data.redis.connection.Message;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.listener.KeyExpirationEventMessageListener;
//import org.springframework.data.redis.listener.RedisMessageListenerContainer;
//import org.springframework.stereotype.Component;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@Slf4j
//@Component
//public class VideoDiagnosisExpiredListener extends KeyExpirationEventMessageListener {
//
//    @Value("${tencent.cloud.secretId}")
//    private String cloudSecretId;
//
//    @Value("${tencent.trtc.appId}")
//    private Long trtcAppId;
//
//    @Value("${tencent.cloud.secretKey}")
//    private String cloudSecretKey;
//
//
//    @Autowired
//    private VideoDiagnosisDao videoDiagnosisDao;
//
//    @Autowired
//    private UserDao userDao;
//
//    @Autowired
//    private RedisTemplate redisTemplate;
//
//    public VideoDiagnosisExpiredListener(RedisMessageListenerContainer listenerContainer) {
//        super(listenerContainer);
//    }
//    @Override
//    @Transactional
//    public void onMessage(Message message, byte[] pattern) {
//        String key = message.toString();
//
//        // 如果是与视频问诊相关的缓存被销毁
//        if (key.startsWith("video_diagnose_")) {
//            // 新添加的代码
//            String temp = key.substring(key.lastIndexOf("_") + 1);
//            // 视频问诊订单号
//            int currentOrder = Integer.parseInt(temp.split("#")[0]);
//            // 医生ID
//            int doctorId = Integer.parseInt(temp.split("#")[1]);
//
//            // 根据doctorId查找userId，用于推送消息
//            int userId = userDao.selectUserIdByRefId(new HashMap<>() {{
//                put("refId", doctorId);
//                put("job", "医生");
//            }});
//
//            // 医生上线缓存Key
//            String onlineKey = "online_doctor_" + doctorId;
//
//            // 问诊开始缓存过期
//            if (key.startsWith("video_diagnose_start_")) {
//                // 更新上线缓存中的当前问诊状态
//                redisTemplate.opsForHash().put(onlineKey, "currentStatus", 2);
//                String roomId = redisTemplate.opsForHash().get(onlineKey, "roomId").toString();
//                // 更新数据库记录
//                HashMap<String, Object> param = new HashMap<>() {{
//                    put("id", currentOrder);
//                    put("status", 2);
//                    put("realStart", new DateTime().toString("yyyy-MM-dd HH:mm:ss"));
//                }};
//                videoDiagnosisDao.updateStatus(param);
//
//                //向医生端发送WebSocket消息，告知问诊开始
//                WebSocketService.sendInfo("StartDiagnose" + "#" + roomId + "&" + currentOrder,userId+"");
//            }
//            //问诊结束缓存过期
//            else if (key.startsWith("video_diagnose_end_")) {
//                //更新上线缓存中的当前问诊状态
//                redisTemplate.opsForHash().put(onlineKey, "currentStatus", 3);
//                String roomId = redisTemplate.opsForHash().get(onlineKey, "roomId").toString();
//
//                //更新数据库记录
//                HashMap param = new HashMap() {{
//                    put("id", currentOrder);
//                    put("status", 3);
//                    put("realEnd", new DateTime().toString("yyyy-MM-dd HH:mm:ss"));
//                }};
//                videoDiagnosisDao.updateStatus(param);
//
//
//                Map<String, Object> entries = redisTemplate.opsForHash().entries(onlineKey);
//                String nextPatient = MapUtil.getStr(entries, "nextPatient");
//                String nextOrder = MapUtil.getStr(entries, "nextOrder");
//                String nextStart = MapUtil.getStr(entries, "nextStart");
//                String nextEnd = MapUtil.getStr(entries, "nextEnd");
//                boolean nextPayment = MapUtil.getBool(entries, "nextPayment");
//
//                // 判断等候的问诊订单是否存在
//                if (!"none".equals(nextOrder)) {
//                    //把等候的问诊换成当前问诊
//                    entries.put("currentPatient", nextPatient);
//                    entries.put("currentOrder", nextOrder);
//                    entries.put("currentHandle", false);
//                    entries.put("currentStart", nextStart);
//                    entries.put("currentEnd", nextEnd);
//                    entries.put("currentPayment", nextPayment);
//                    entries.put("currentNotify", false);
//                    entries.put("currentStatus", 1);
//
//                    // 清理等候问诊的缓存
//                    entries.put("nextPatient", "none");
//                    entries.put("nextOrder", "none");
//                    entries.put("nextStart", null);
//                    entries.put("nextEnd", null);
//                    entries.put("nextPayment", false);
//                    entries.put("nextNotify", false);
//                } else {
//                    entries.put("currentPatient", "none");
//                    entries.put("currentOrder", "none");
//                    entries.put("currentHandle", false);
//                    entries.put("currentStart", null);
//                    entries.put("currentEnd", null);
//                    entries.put("currentPayment", false);
//                    entries.put("currentNotify", false);
//                    entries.put("currentStatus", null);
//                }
//
//                // 更新缓存数据
//                redisTemplate.opsForHash().putAll(onlineKey, entries);
//
//                // 向医生端发送消息，告知问诊结束
//                WebSocketService.sendInfo("EndDiagnose" + "#" + roomId, String.valueOf(userId));
//                try {
//                    // 医生端JS收到消息后会退出TRTC房间，这里等待JS退出后销毁房间
//                    Thread.sleep(2000);
//                } catch (InterruptedException e) {
//                    log.error("线程休眠异常", e);
//                }
//
//                // 销毁视频问诊Room
//                Credential cred = new Credential(cloudSecretId, cloudSecretKey);
//                TrtcClient client = new TrtcClient(cred, "ap-beijing");
//                DismissRoomByStrRoomIdRequest request = new DismissRoomByStrRoomIdRequest();
//                request.setSdkAppId(trtcAppId);
//                request.setRoomId(roomId);
//                try {
//                    client.DismissRoomByStrRoomId(request);
//                } catch (TencentCloudSDKException e) {
//                    log.error("销毁TRTC房间失败", e);
//                }
//                redisTemplate.opsForHash().put(key, "roomId", null);
//            }
//        }
//        // 视频问诊挂号缓存被销毁
//        else if (key.startsWith("patient_video_diagnose_payment")) {
//            // 获取挂号单ID
//            int id = Integer.parseInt(key.split("#")[1]);
//            // 查询付款状态
//            HashMap<String, Object> map = videoDiagnosisDao.selectPaymentStatus(id);
//            int paymentStatus = MapUtil.getInt(map, "paymentStatus");
//            int doctorId = MapUtil.getInt(map, "doctorId");
//            key = "online_doctor_" + doctorId;
//
//            // 只要不是付款成功，就销毁该患者候诊缓存
//            if (paymentStatus != 2) {
//                Map<String, Object> entries = redisTemplate.opsForHash().entries(key);
//                // 判断候诊缓存是否是当前挂号单
//                if (id == MapUtil.getInt(entries, "nextOrder")) {
//                    redisTemplate.opsForHash().putAll(key, new HashMap<>() {{
//                        put("nextPatient", "none"); // 候诊患者Id
//                        put("nextOrder", "none"); // 等候问诊的订单号
//                        put("nextStart", "none"); // 等候问诊的开始时间
//                        put("nextEnd", "none"); // 等候问诊的结束时间
//                        put("nextPayment", false); // 等候问诊是否付款
//                        put("nextNotify", false); // 是否通知了医生端
//                    }});
//                }
//                    //这里代码要被删除
//
////                // 使用WebSocket通知视频问诊页面刷新
////                int userId = userDao.selectUserIdByRefId(new HashMap<>() {{
////                    put("refId", doctorId);
////                    put("job", "医生");
////                }});
////                WebSocketService.sendInfo("RefreshDiagnose", String.valueOf(userId));
////
////                // 关闭视频问诊订单（3状态）
////                videoDiagnosisDao.closePayment(new HashMap<>() {{
////                    put("id", id);
////                }});
//            }else {
//                int userId = userDao.selectUserIdByRefId(new HashMap<>() {{
//                    put("refId", doctorId);
//                    put("job", "医生");
//                }});
//                WebSocketService.sendInfo("RefreshDiagnose", String.valueOf(userId));
//            }
//        }
//    }
//}