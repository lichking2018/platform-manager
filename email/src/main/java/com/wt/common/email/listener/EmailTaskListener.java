package com.wt.common.email.listener;

import com.wt.common.email.task.EmailTaskThread;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * @ProjectName: sydsxx
 * @Package: listener
 * @Description: Email发送线程监听器，在servlet容器启动的时候，开启线程。容器关闭的时候，停止线程
 * @Author: lichking2017@aliyun.com
 * @CreateDate: 2018/6/9 下午3:30
 * @Version: v1.0
 */
@WebListener
public class EmailTaskListener implements ServletContextListener{
    private EmailTaskThread emailTaskThread;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        if(emailTaskThread==null){
            emailTaskThread = new EmailTaskThread();
        }
        emailTaskThread.start();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        if(emailTaskThread!=null&&emailTaskThread.isInterrupted()){
            emailTaskThread.interrupt();
        }
    }
}
