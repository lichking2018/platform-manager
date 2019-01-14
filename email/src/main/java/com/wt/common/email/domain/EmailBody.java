package com.wt.common.email.domain;

import com.wt.common.email.enums.EmailType;

import java.io.File;
import java.util.Arrays;
import java.util.List;

/**
 * @ProjectName: sydsxx
 * @Package: domain
 * @Description:
 * @Author: lichking2017@aliyun.com
 * @CreateDate: 2018/6/9 下午3:10
 * @Version: v1.0
 */
public class EmailBody {

    //邮件标题
    private String subject;

    //邮件正文
    private String message;

    //发送目标
    private List<String> to;

    //邮件附件
    private List<File> attachment;

    //邮件类型
    private EmailType emailType;

    public String getMessage() {
        return message;
    }

    public EmailBody setMessage(String message) {
        this.message = message;
        return this;
    }


    public String getSubject() {
        return subject;
    }

    public EmailBody setSubject(String subject) {
        this.subject = subject;
        return this;
    }

    public List<String> getTo() {
        return to;
    }

    public EmailBody setTo(List<String> to) {
        this.to = to;
        return this;
    }

    public EmailBody setTo(String... tos){
        this.to = Arrays.asList(tos);
        return this;
    }

    public List<File> getAttachment() {
        return attachment;
    }

    public EmailBody setAttachment(List<File> attachment) {
        this.attachment = attachment;
        return this;
    }




    public EmailType getEmailType() {
        return emailType;
    }

    public EmailBody setEmailType(EmailType emailType) {
        this.emailType = emailType;
        return this;
    }


    @Override
    public String toString() {
        return String.format("收到邮件任务=》【邮件标题】%s,【邮件正文】%s",this.subject,this.message);
    }
}
