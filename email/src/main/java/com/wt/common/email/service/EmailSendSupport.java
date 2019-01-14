package com.wt.common.email.service;

import com.wt.common.core.util.SpringContextUtils;
import org.springframework.mail.javamail.JavaMailSender;

/**
 * @ProjectName: sydsxx
 * @Package: service
 * @Description:
 * @Author: lichking2017@aliyun.com
 * @CreateDate: 2018/6/9 下午4:08
 * @Version: v1.0
 */
public class EmailSendSupport {
    protected JavaMailSender getSender(){
        return (JavaMailSender) SpringContextUtils.getBean("javaMailSender");
    }
}
