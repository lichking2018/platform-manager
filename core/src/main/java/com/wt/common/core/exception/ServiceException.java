package com.wt.common.core.exception;

/**
 * @ProjectName: syInfo
 * @Package: com.wt.common.core.exception
 * @Description:
 * @Author: lichking2017@aliyun.com
 * @CreateDate: 2018/4/20 下午3:28
 * @Version: v1.0
 */
public class ServiceException extends BaseErrorException{
    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceException(Throwable cause) {
        super(cause);
    }
}
