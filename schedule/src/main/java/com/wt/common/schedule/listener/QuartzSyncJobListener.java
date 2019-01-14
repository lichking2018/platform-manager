package com.wt.common.schedule.listener;

import com.wt.common.core.util.SpringContextUtils;
import com.wt.common.schedule.service.ScheduleJobService;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * @ProjectName: myProject
 * @Package: com.wt.common.schedule.listener
 * @Description:
 * @Author: lichking2017@aliyun.com
 * @CreateDate: 2018/7/27 下午2:11
 * @Version: v1.0
 */
//【知识点】，定义监听器，监听Servlet容器的启停。通过Servlet3.0中定义的@WebListener注解方式定义监听器
@WebListener
public class QuartzSyncJobListener implements ServletContextListener{

    Logger logger = LoggerFactory.getLogger(QuartzSyncJobListener.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ScheduleJobService service = SpringContextUtils.getBean("ScheduleJobService",ScheduleJobService.class);
        Scheduler scheduler = SpringContextUtils.getBean("scheduler",
                Scheduler.class);
        logger.info("=====================================");
        //【知识点】，slf4j中，通过参数配置日志信息
        logger.info("{}开始同步调度作业",QuartzSyncJobListener.class.getSimpleName());
        logger.info("=====================================");
        service.syncScheduleJobs();
        logger.info("=====================================");
        logger.info("同步完成");
        logger.info("=====================================");

        logger.info("=====================================");
        logger.info("开始启动调度器");
        logger.info("=====================================");
        try {
            //【知识点】，启动scheduler容器
            scheduler.start();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }

        logger.info("=====================================");
        logger.info("启动完毕");
        logger.info("=====================================");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
