package com.wt.common.email.exception;

/**
 * @ProjectName: sydsxx
 * @Package: exception
 * @Description: 邮件类型异常
 * @Author: lichking2017@aliyun.com
 * @CreateDate: 2018/6/9 下午6:11
 * @Version: v1.0
 */
public class EmailTypeException extends BaseEmailException{
    public EmailTypeException() {
        super(ErrorCode.EMAIL_TYPE_EXCEPTION);
    }
}
