package com.wt.common.core.exception;

/**
 * @ProjectName: syInfo
 * @Package: com.wt.common.core.exception
 * @Description:异常体
 * @Author: lichking2017@aliyun.com
 * @CreateDate: 2018/5/6 下午9:22
 * @Version: v1.0
 */
public class ExceptionBody {
    private String errorCode;
    private String message;

    public ExceptionBody(String errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
