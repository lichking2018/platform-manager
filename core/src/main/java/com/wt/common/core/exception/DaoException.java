package com.wt.common.core.exception;

/**
 * @ProjectName: syInfo
 * @Package: com.wt.common.core.exception
 * @Description:
 * @Author: lichking2017@aliyun.com
 * @CreateDate: 2018/4/18 下午11:55
 * @Version: v1.0
 */
public class DaoException extends BaseErrorException{

    public DaoException(String message) {
        super(message);
    }

    public DaoException(String message, Throwable cause) {
        super(message, cause);
    }

    public DaoException(Throwable cause) {
        super(cause);
    }
}
