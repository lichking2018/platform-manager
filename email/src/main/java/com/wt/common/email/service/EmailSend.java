package com.wt.common.email.service;

import com.wt.common.email.domain.Email;
import com.wt.common.email.exception.MimeConvertException;

/**
 * @ProjectName: sydsxx
 * @Package: service
 * @Description:
 * @Author: lichking2017@aliyun.com
 * @CreateDate: 2018/6/9 下午4:00
 * @Version: v1.0
 */
public interface EmailSend {
    void send(Email email) throws MimeConvertException;
}
