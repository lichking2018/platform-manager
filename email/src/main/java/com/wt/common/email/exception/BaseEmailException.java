package com.wt.common.email.exception;

import com.wt.common.core.exception.BaseLogicException;
import com.wt.common.core.exception.ExceptionBody;

/**
 * @ProjectName: sydsxx
 * @Package: exception
 * @Description:
 * @Author: lichking2017@aliyun.com
 * @CreateDate: 2018/6/9 下午4:41
 * @Version: v1.0
 */
public class BaseEmailException extends BaseLogicException{
    public BaseEmailException(ErrorCode errorCode) {
        super(new ExceptionBody(errorCode.errorCode,errorCode.errorMessage));
    }

    public BaseEmailException(ErrorCode errorCode, Throwable cause) {
        super(new ExceptionBody(errorCode.errorCode,errorCode.errorMessage), cause);
    }

    enum ErrorCode{
        MIME_CONVERT_EXCEPTION("300","创建MimeMessageHelper异常")
        ,EMAIL_TYPE_EXCEPTION("301","邮件类型异常");
        private String errorCode;
        private String errorMessage;

        ErrorCode(String errorCode, String errorMessage) {
            this.errorCode = errorCode;
            this.errorMessage = errorMessage;
        }
    }
}
