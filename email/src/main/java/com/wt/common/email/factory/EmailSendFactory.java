package com.wt.common.email.factory;

import com.wt.common.email.enums.EmailType;
import com.wt.common.email.service.EmailSend;
import com.wt.common.email.service.impl.MimeEmailSendImpl;
import com.wt.common.email.service.impl.SimpleEmailSendImpl;

/**
 * @ProjectName: sydsxx
 * @Package: factory
 * @Description: Email发送工厂
 * @Author: lichking2017@aliyun.com
 * @CreateDate: 2018/6/9 下午5:33
 * @Version: v1.0
 */
public class EmailSendFactory {
    public static EmailSend produce(EmailType emailType){
        switch (emailType){
            case MIME:
                return new MimeEmailSendImpl();
            case SIMPLE:
                return new SimpleEmailSendImpl();
        }
        throw new IllegalArgumentException();
    }
}
