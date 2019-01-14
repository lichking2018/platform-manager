package com.wt.common.schedule.service;

import com.wt.common.core.service.BaseService;
import com.wt.common.schedule.model.ScheduleJob;
import org.quartz.CronTrigger;
import org.quartz.TriggerKey;

import javax.validation.Valid;

/**
 * @ProjectName: myProject
 * @Package: com.wt.common.schedule.service
 * @Description:
 * @Author: lichking2017@aliyun.com
 * @CreateDate: 2018/7/27 上午10:47
 * @Version: v1.0
 */
public interface ScheduleJobService extends BaseService<ScheduleJob>{

    /**
     * 同步所有调度作业
     */
    void syncScheduleJobs();

    /**
     * 同步单条调度作业
     */
    void syncScheduleJob(ScheduleJob scheduleJob);


    /**
     * 创建新的调度作业
     * @param scheduleJob
     */
    void createNewScheduleJob(@Valid ScheduleJob scheduleJob);

    /**
     * 更新调度作业
     * @param scheduleJob
     * @param trigger
     * @param triggerKey
     */
    void updateScheduleJob(ScheduleJob scheduleJob, CronTrigger trigger, TriggerKey triggerKey);

    /**
     * 删除调度作业
     * @param scheduleJob
     */
//    void deleteScheduleJob(ScheduleJob scheduleJob);


    void forbiddenJob(ScheduleJob scheduleJob);

    void forbiddenJob(String jobName,String groupName);
}
