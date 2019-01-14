package com.wt.common.core.exception;

import java.text.MessageFormat;

/**
 * @ProjectName: syInfo
 * @Package: com.wt.common.core.exception
 * @Description:
 * @Author: lichking2017@aliyun.com
 * @CreateDate: 2018/4/18 下午10:58
 * @Version: v1.0
 */
public class BaseErrorException extends RuntimeException{

    private ExceptionBody exceptionBody;

    public BaseErrorException(){}

    public BaseErrorException(String message){
        super(message);
    }

    public BaseErrorException(String message,Object... args){
        super(MessageFormat.format(message,args));
    }

    public BaseErrorException(String message,Throwable cause){
        super(message,cause);
    }

    public BaseErrorException(String message,Throwable cause,Object... args){
        super(MessageFormat.format(message,args),cause);
    }

    public BaseErrorException(ExceptionBody exceptionBody){
        super(exceptionBody.getMessage());
        this.exceptionBody = exceptionBody;
    }

    public BaseErrorException(ExceptionBody exceptionBody,Throwable cause){
        super(exceptionBody.getMessage(),cause);
        this.exceptionBody = exceptionBody;
    }


    public BaseErrorException(Throwable cause){
        super(cause);
    }


}
