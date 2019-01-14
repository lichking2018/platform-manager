package com.wt.common.security.schedule;

import com.wt.common.schedule.model.ScheduleJob;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ProjectName: myProject
 * @Package: com.wt.common.security.schedule
 * @Description:
 * @Author: lichking2017@aliyun.com
 * @CreateDate: 2018/7/28 下午3:49
 * @Version: v1.0
 */
public class FindHumen implements Job{
    Logger logger = LoggerFactory.getLogger(FindHumen.class);

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        JobDataMap jobDataMap = jobExecutionContext.getJobDetail().getJobDataMap();
        ScheduleJob scheduleJob = (ScheduleJob)jobDataMap.get("scheduleJob");
        logger.info("==================================");
        logger.info("我是调度作业：{}",scheduleJob.toString());
        logger.info("==================================");
    }
}
