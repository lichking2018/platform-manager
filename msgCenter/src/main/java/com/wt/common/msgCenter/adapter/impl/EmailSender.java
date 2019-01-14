package com.wt.common.msgCenter.adapter.impl;

import com.google.gson.JsonObject;
import com.wt.common.core.exception.BaseErrorException;
import com.wt.common.email.domain.EmailBody;
import com.wt.common.email.enums.EmailType;
import com.wt.common.email.util.EmailSendUtils;
import com.wt.common.msgCenter.adapter.MsgSenderI;

import java.util.List;

/**
 * @ProjectName: myProject
 * @Package: com.wt.common.msgCenter.adapter.impl
 * @Description:
 * @Author: lichking2017@aliyun.com
 * @CreateDate: 2018/6/19 下午5:33
 * @Version: v1.0
 */
public class EmailSender implements MsgSenderI{

    @Override
    public void send(List<String> target, String msgContent, JsonObject param) {
        checkEmailParams(param);
        EmailBody emailBody = this.setEmailInfo(target,msgContent,param);
        EmailSendUtils.send(emailBody);
    }

    private void checkEmailParams(JsonObject param){
        if(param.get(EmailSendUtils.EMAIL_TYPE)==null){
            throw new BaseErrorException("邮件参数错误，未设置{0}",EmailSendUtils.EMAIL_TYPE);
        }

        if(param.get(EmailSendUtils.SUBJECT)==null){
            throw new BaseErrorException("邮件参数错误，未设置{0}",EmailSendUtils.SUBJECT);
        }
    }

    private EmailBody setEmailInfo(List<String> target, String msgContent, JsonObject param){
        EmailBody emailBody = new EmailBody();
        emailBody.setTo(target);
        emailBody.setEmailType(EmailType.SIMPLE);
        emailBody.setMessage(msgContent);
        emailBody.setSubject(param.get(EmailSendUtils.SUBJECT).toString());
        return emailBody;
    }


}
