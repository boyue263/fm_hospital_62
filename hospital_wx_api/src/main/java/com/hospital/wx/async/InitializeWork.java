package com.hospital.wx.async;
import com.hospital.wx.quartz.VideoDiagnosisJob;
import com.hospital.wx.utils.QuartzUtil;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class InitializeWork {
    @Resource
    private QuartzUtil quartzUtil;

    @Async("AsyncTaskExecutor")
    public void init() {
        //每隔3秒钟执行一遍定时器
        String cron = "*/3 * * * * ?";
        JobDetail jobDetail = JobBuilder.newJob(VideoDiagnosisJob.class).build();
        quartzUtil.addJob(jobDetail, "视频问诊定时器", "任务组", cron);
        
        //TODO 关闭状态不正确的视频问诊挂号单
    }

}
