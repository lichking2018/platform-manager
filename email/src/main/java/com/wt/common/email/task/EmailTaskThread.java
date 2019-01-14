package com.wt.common.email.task;

import com.wt.common.email.domain.Email;
import com.wt.common.email.exception.MimeConvertException;
import com.wt.common.email.handler.EmailTaskHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @ProjectName: sydsxx
 * @Package: task
 * @Description:
 * @Author: lichking2017@aliyun.com
 * @CreateDate: 2018/6/9 下午3:08
 * @Version: v1.0
 */
public class EmailTaskThread extends Thread{
    Logger logger = LoggerFactory.getLogger(EmailTaskThread.class);
    public static Queue taskQueue = new LinkedList();

    @Override
    public void run() {
        logger.info("开启邮件发送线程...");
        while(!EmailTaskThread.interrupted()){
            if(!taskQueue.isEmpty()){
                logger.info("taskQuery Size:"+taskQueue.size());
                Email email = (Email) taskQueue.poll();
                try {
                    EmailTaskHandler.send(email);
                } catch (MimeConvertException e) {
                    logger.error("邮件发送失败，邮件内容{}",email.getEmailBody().getMessage());
                }
            }

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        logger.info("线程关闭了...");
    }
}
