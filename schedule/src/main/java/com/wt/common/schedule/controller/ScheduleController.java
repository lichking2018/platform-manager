package com.wt.common.schedule.controller;

import com.wt.common.core.controller.BaseController;
import com.wt.common.core.result.HttpResultEntity;
import com.wt.common.schedule.model.ScheduleJob;
import com.wt.common.schedule.service.ScheduleJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ProjectName: myProject
 * @Package: com.wt.common.schedule.controller
 * @Description:
 * @Author: lichking2017@aliyun.com
 * @CreateDate: 2018/7/28 下午3:37
 * @Version: v1.0
 */
@RestController
@RequestMapping("/schedule")
public class ScheduleController extends BaseController{

    private static final String EXPRESS="0/5 * * * * ?";
    private static final String JOB_TARGET_CLASS="com.wt.common.security.schedule.FindHumen";

    @Autowired
    private ScheduleJobService scheduleJobService;

    @RequestMapping(value = "/create/{jobName}/{groupName}",method = RequestMethod.GET)
    public HttpResultEntity<?> createScheduleJob(@PathVariable("jobName") String jobName,@PathVariable("groupName")
                                                 String groupName){
        ScheduleJob scheduleJob = new ScheduleJob();
        scheduleJob.setJobGroup(groupName);
        scheduleJob.setJobName(jobName);
        scheduleJob.setExpression(EXPRESS);
        scheduleJob.setJobClass(JOB_TARGET_CLASS);
        scheduleJobService.createNewScheduleJob(scheduleJob);
        return getSuccessResult();
    }

    @RequestMapping(value = "/drop/{jobName}/{groupName}",method = RequestMethod.GET)
    public HttpResultEntity<?> dropScheduleJob(@PathVariable("jobName") String jobName,@PathVariable("groupName")
                                               String groupName){
        scheduleJobService.forbiddenJob(jobName,groupName);
        return getSuccessResult();
    }

}
