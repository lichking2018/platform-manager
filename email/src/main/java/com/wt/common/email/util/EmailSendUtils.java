package com.wt.common.email.util;

import com.wt.common.core.util.SpringPropertyUtils;
import com.wt.common.email.domain.Email;
import com.wt.common.email.domain.EmailBody;
import com.wt.common.email.domain.EmailConfig;
import com.wt.common.email.exception.MimeConvertException;
import com.wt.common.email.task.EmailTaskThread;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ProjectName: sydsxx
 * @Package: util
 * @Description: 邮件发送工具类
 * @Author: lichking2017@aliyun.com
 * @CreateDate: 2018/6/9 下午3:13
 * @Version: v1.0
 */
public class EmailSendUtils {
    static Logger logger = LoggerFactory.getLogger(EmailSendUtils.class);

    public static final String EMAIL_TYPE="emailType";
    public static final String SUBJECT="subject";


    /**
     * 发送邮件API
     * @param emailBody
     * @throws MimeConvertException
     */
    public static void send(EmailBody emailBody){
        Email email = new Email();
        email.setEmailConfig(getEmailConfig()).setEmailBody(emailBody);
        logger.info(emailBody.toString());
        EmailTaskThread.taskQueue.offer(email);
    }

    /**
     * 获得配置
     * @return
     */
    private static EmailConfig getEmailConfig(){
        EmailConfig emailConfig = new EmailConfig();
        emailConfig.setHost(SpringPropertyUtils.get("mail.host"));
        emailConfig.setUserName(SpringPropertyUtils.get("mail.userName"));
        emailConfig.setPassword(SpringPropertyUtils.get("mail.password"));
        emailConfig.setPort(SpringPropertyUtils.get("mail.port"));
        emailConfig.setFrom(SpringPropertyUtils.get("mail.from"));
        return emailConfig;
    }

}
