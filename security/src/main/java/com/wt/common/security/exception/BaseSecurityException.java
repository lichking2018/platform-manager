package com.wt.common.security.exception;

import com.wt.common.core.exception.BaseLogicException;
import com.wt.common.core.exception.ExceptionBody;

/**
 * @ProjectName: syInfo
 * @Package: com.wt.common.security.exception
 * @Description:
 * @Author: lichking2017@aliyun.com
 * @CreateDate: 2018/5/15 下午3:11
 * @Version: v1.0
 */
public class BaseSecurityException extends BaseLogicException{
    public BaseSecurityException(ErrorCode errorCode) {
        super(new ExceptionBody(errorCode.code,errorCode.message));
    }

    protected enum ErrorCode{
        ACCOUNT_PASSWORD_ERROR("200","用户名或密码错误"),
        DULPLICATE_ACCOUNT_EXCEPTION("201","存在重复的账号，请联系管理员");
        private String code;
        private String message;

        ErrorCode(String code, String message) {
            this.code = code;
            this.message = message;
        }
    }
}
