package com.wt.common.email.domain;

/**
 * @ProjectName: sydsxx
 * @Package: domain
 * @Description:
 * @Author: lichking2017@aliyun.com
 * @CreateDate: 2018/6/9 下午3:17
 * @Version: v1.0
 */
public class Email {

    private EmailBody emailBody;

    private EmailConfig emailConfig;

    public EmailBody getEmailBody() {
        return emailBody;
    }

    public Email setEmailBody(EmailBody emailBody) {
        this.emailBody = emailBody;
        return this;
    }

    public EmailConfig getEmailConfig() {
        return emailConfig;
    }

    public Email setEmailConfig(EmailConfig emailConfig) {
        this.emailConfig = emailConfig;
        return this;
    }
}
