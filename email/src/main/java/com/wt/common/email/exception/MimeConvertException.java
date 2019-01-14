package com.wt.common.email.exception;

/**
 * @ProjectName: sydsxx
 * @Package: exception
 * @Description:
 * @Author: lichking2017@aliyun.com
 * @CreateDate: 2018/6/9 下午4:53
 * @Version: v1.0
 */
public class MimeConvertException extends BaseEmailException{
    public MimeConvertException(Throwable cause) {
        super(BaseEmailException.ErrorCode.MIME_CONVERT_EXCEPTION,cause);
    }
}
