package com.wt.common.security.schedule;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ProjectName: myProject
 * @Package: com.wt.common.security.schedule
 * @Description:
 * @Author: lichking2017@aliyun.com
 * @CreateDate: 2018/7/28 上午10:43
 * @Version: v1.0
 */
public class MemberOutOfDate implements Job{
    Logger logger = LoggerFactory.getLogger(MemberOutOfDate.class);
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        logger.info("我是会员超期通知作业");
    }
}
