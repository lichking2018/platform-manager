package com.wt.common.email.service.impl;

import com.wt.common.email.domain.Email;
import com.wt.common.email.domain.EmailBody;
import com.wt.common.email.domain.EmailConfig;
import com.wt.common.email.exception.MimeConvertException;
import com.wt.common.email.service.EmailSend;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import com.wt.common.email.service.EmailSendSupport;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

/**
 * @ProjectName: sydsxx
 * @Package: service.impl
 * @Description:
 * @Author: lichking2017@aliyun.com
 * @CreateDate: 2018/6/9 下午4:01
 * @Version: v1.0
 */
public class MimeEmailSendImpl extends EmailSendSupport implements EmailSend {
    @Override
    public void send(Email email) throws MimeConvertException {
        JavaMailSender javaMailSender = getSender();
        EmailBody emailBody = email.getEmailBody();
        EmailConfig emailConfig = email.getEmailConfig();

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = null;
        try {
            mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "utf-8");
            mimeMessageHelper.setFrom(emailConfig.getFrom());
            String[] toArray = new String[emailBody.getTo().size()];


            for(int i=0;i<emailBody.getTo().size();i++){
                toArray[i] = emailBody.getTo().get(i);
            }

            mimeMessageHelper.setTo(toArray);

            mimeMessageHelper.setSubject(emailBody.getSubject());
            mimeMessageHelper.setText(emailBody.getMessage(), true);

            //附件发送
            for(File file:emailBody.getAttachment()){
                mimeMessageHelper.addAttachment(file.getName(), file);
            }
        } catch (MessagingException e) {
            throw new MimeConvertException(e);
        }

        javaMailSender.send(mimeMessage);
    }
}
