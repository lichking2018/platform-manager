package com.wt.common.schedule.service.impl;

import com.wt.common.core.handle.QueryHandle;
import com.wt.common.core.service.impl.ServiceSupport;
import com.wt.common.schedule.dao.ScheduleJobMapper;
import com.wt.common.schedule.enums.QuartExceptionEnum;
import com.wt.common.schedule.enums.ScheduleJobStatus;
import com.wt.common.schedule.exception.*;
import com.wt.common.schedule.model.ScheduleJob;
import com.wt.common.schedule.service.ScheduleJobService;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ProjectName: myProject
 * @Package: com.wt.common.schedule.service.impl
 * @Description:
 * @Author: lichking2017@aliyun.com
 * @CreateDate: 2018/7/27 上午10:48
 * @Version: v1.0
 */
@Service("ScheduleJobService")
public class ScheduleJobServiceImpl extends ServiceSupport<ScheduleJob,ScheduleJobMapper> implements
        ScheduleJobService {

    Logger logger = LoggerFactory.getLogger(ScheduleJobServiceImpl.class);

    @Autowired
    private ScheduleJobMapper mapper;

//【知识点】，通过名称注入实例
    @Autowired
    private Scheduler scheduler;

    @Override
    protected ScheduleJobMapper getMapper() {
        return this.mapper;
    }

    @Override
    public void syncScheduleJobs() {
        List<ScheduleJob> scheduleJobs = this.findAll(new ScheduleJob().setDeleteFlag(false),new QueryHandle());
        for(ScheduleJob scheduleJob:scheduleJobs){
            syncScheduleJob(scheduleJob);
        }
    }


    @Override
    public void syncScheduleJob(ScheduleJob scheduleJob) {

        if(scheduleJob.getJobStatus()== ScheduleJobStatus.OFF){
            forbiddenJob(scheduleJob);
            return;
        }
        //【知识点】，trigger会有其对应的triggerkey，同样job也会有其对应的jobkey
        TriggerKey triggerKey = new TriggerKey(scheduleJob.getJobName(),scheduleJob.getJobGroup());
        try {
            //【知识点】，scheduler获取trigger，根据triggerKey
            CronTrigger trigger = (CronTrigger) getScheduler().getTrigger(triggerKey);
            if(trigger==null){
                createNewScheduleJob(scheduleJob);
                return;
            }
            updateScheduleJob(scheduleJob,trigger,triggerKey);
        } catch (SchedulerException e) {
            logger.error("同步调度作业异常，具体内容{}",scheduleJob.toString(),e);
            throw new GetTriggerException(QuartExceptionEnum.GETTRIGGER_EXCEPTION,e);
        }

    }

    @Override
    public void createNewScheduleJob(ScheduleJob scheduleJob) {
        try {
            //【知识点】，加载class到jvm
            Class<? extends Job> jobClass = (Class<? extends Job>)Class.forName(scheduleJob.getJobClass());
            CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(scheduleJob.getExpression());
            //【知识点】，创建trigger、创建jobDetail
            CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(scheduleJob.getJobName(),scheduleJob
                    .getJobGroup()).withSchedule(cronScheduleBuilder).build();
            JobDetail jobDetail = JobBuilder.newJob(jobClass).withIdentity(scheduleJob.getJobName(),scheduleJob
                    .getJobGroup()).build();
            //【知识点】，为job设置参数
            jobDetail.getJobDataMap().put("scheduleJob",scheduleJob);
            //【知识点】，将trigger和job动态添加到schedule容器中
            getScheduler().scheduleJob(jobDetail,trigger);
        } catch (ClassNotFoundException e) {
            logger.error("获取调度作业异常，未找到{}",scheduleJob.getJobClass());
            throw new JobClassNotFoundException(QuartExceptionEnum.JOBCLASS_NOTFOUND_EXCEPTION,e);
        } catch (SchedulerException e) {
            logger.error("添加调度作业失败，调度作业详情:{}",scheduleJob.toString());
            throw new CreateScheduleJobException(QuartExceptionEnum.CREATEJOB_FAIL_EXCEPTION,e);
        }
    }

    @Override
    public void updateScheduleJob(ScheduleJob scheduleJob, CronTrigger trigger, TriggerKey triggerKey) {
        if(trigger.getCronExpression().equals(scheduleJob.getExpression())){
            return;
        }
        try {
            CronTrigger cronTrigger = trigger.getTriggerBuilder().withSchedule(CronScheduleBuilder.cronSchedule
                    (scheduleJob.getExpression())).build();
            //【知识点】，Quart 更新触发Job的trigger
            getScheduler().rescheduleJob(triggerKey,cronTrigger);
        } catch (SchedulerException e) {
            logger.error("调度作业更新异常，作业信息：{}",scheduleJob.toString(),e);
            throw new SecheduleJobUpdateException(QuartExceptionEnum.UPDATEJOB_EXCEPTION,e);
        }
    }

    public void deleteScheduleJob(String jobName,String groupName) {
        JobKey jobKey = new JobKey(jobName,groupName);
        try {
            //【知识点】，删除job
            getScheduler().deleteJob(jobKey);
        } catch (SchedulerException e) {
            logger.error("删除作业异常，jobName:{}，groupName:{}",jobName,groupName,e);
            throw new DeleteScheduleJobException(QuartExceptionEnum.DELETEJOB_EXCEPTION,e);
        }
    }

    @Override
    public void forbiddenJob(ScheduleJob scheduleJob){
        deleteScheduleJob(scheduleJob.getJobName(),scheduleJob.getJobGroup());
    }

    @Override
    public void forbiddenJob(String jobName, String groupName) {
        this.deleteScheduleJob(jobName,groupName);
    }

    private Scheduler getScheduler(){
        return this.scheduler;
    }
}
