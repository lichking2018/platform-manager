package com.wt.common.core.exception;

import java.text.MessageFormat;

/**
 * @ProjectName: syInfo
 * @Package: com.wt.common.core.exception
 * @Description: 业务逻辑异常基类，此类异常需要被捕获。当出现异常后，系统应该进入异常流
 * @Author: lichking2017@aliyun.com
 * @CreateDate: 2018/5/6 下午9:17
 * @Version: v1.0
 */
public class BaseLogicException extends Exception {

    private ExceptionBody exceptionBody;

    public BaseLogicException() {
    }

    public BaseLogicException(ExceptionBody exceptionBody) {
        super(exceptionBody.getMessage());
        setExceptionBody(exceptionBody);
    }

    public BaseLogicException(String message,String... args){
        super(MessageFormat.format(message,args));
    }

    public BaseLogicException(ExceptionBody exceptionBody, Throwable cause) {
        super(exceptionBody.getMessage(), cause);
        setExceptionBody(exceptionBody);
    }


    public ExceptionBody getExceptionBody() {
        return exceptionBody;
    }

    public void setExceptionBody(ExceptionBody exceptionBody) {
        this.exceptionBody = exceptionBody;
    }

}
