package com.wt.common.email.service.impl;

import com.wt.common.email.domain.Email;
import com.wt.common.email.domain.EmailBody;
import com.wt.common.email.domain.EmailConfig;
import org.springframework.mail.SimpleMailMessage;
import com.wt.common.email.service.EmailSend;
import com.wt.common.email.service.EmailSendSupport;

/**
 * @ProjectName: sydsxx
 * @Package: service.impl
 * @Description:
 * @Author: lichking2017@aliyun.com
 * @CreateDate: 2018/6/9 下午4:01
 * @Version: v1.0
 */
public class SimpleEmailSendImpl extends EmailSendSupport implements EmailSend{
    @Override
    public void send(Email email) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        EmailConfig emailConfig = email.getEmailConfig();
        EmailBody emailBody = email.getEmailBody();
        simpleMailMessage.setFrom(emailConfig.getFrom());

        String[] toArray = new String[emailBody.getTo().size()];


        for(int i=0;i<emailBody.getTo().size();i++){
            toArray[i] = emailBody.getTo().get(i);
        }
        simpleMailMessage.setTo(toArray);
        simpleMailMessage.setSubject(emailBody.getSubject());
        simpleMailMessage.setText(emailBody.getMessage());
        getSender().send(simpleMailMessage);
    }
}
