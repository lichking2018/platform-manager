package com.wt.common.email.handler;

import com.wt.common.email.domain.Email;
import com.wt.common.email.domain.EmailBody;
import com.wt.common.email.exception.MimeConvertException;
import com.wt.common.email.factory.EmailSendFactory;
import com.wt.common.email.service.EmailSend;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

/**
 * @ProjectName: sydsxx
 * @Package: handler
 * @Description: email任务池管理类
 * @Author: lichking2017@aliyun.com
 * @CreateDate: 2018/6/9 下午7:53
 * @Version: v1.0
 */
public class EmailTaskHandler {

    public static void send(Email email) throws MimeConvertException {
        EmailBody emailBody = email.getEmailBody();
        System.out.println(emailBody.getEmailType());
        Assert.isTrue(emailBody.getEmailType()!=null,"邮件类型未设置");
        Assert.isTrue(!CollectionUtils.isEmpty(emailBody.getTo()),"未设置发送目标");

        EmailSend emailSend = EmailSendFactory.produce(email.getEmailBody().getEmailType());
        emailSend.send(email);
    }

}
