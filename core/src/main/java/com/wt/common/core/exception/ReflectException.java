package com.wt.common.core.exception;

/**
 * @ProjectName: syInfo
 * @Package: com.wt.common.core.exception
 * @Description:
 * @Author: lichking2017@aliyun.com
 * @CreateDate: 2018/4/19 下午10:29
 * @Version: v1.0
 */
public class ReflectException extends BaseErrorException{
    public ReflectException(String message) {
        super(message);
    }

    public ReflectException(String message, Throwable cause) {
        super(message, cause);
    }

    public ReflectException(Throwable cause) {
        super(cause);
    }
}
