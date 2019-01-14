package com.wt.common.schedule.exception;

import com.wt.common.core.exception.BaseErrorException;
import com.wt.common.core.exception.ExceptionBody;
import com.wt.common.schedule.enums.QuartExceptionEnum;

/**
 * @ProjectName: myProject
 * @Package: com.wt.common.schedule.exception
 * @Description:
 * @Author: lichking2017@aliyun.com
 * @CreateDate: 2018/7/27 下午3:26
 * @Version: v1.0
 */
public class BaseQuartException extends BaseErrorException{

    public BaseQuartException(QuartExceptionEnum exceptionEnum){
        super(new ExceptionBody(exceptionEnum.getError_code(),exceptionEnum.getError_message()));
    }

    public BaseQuartException(QuartExceptionEnum exceptionEnum,Throwable cause){
        super(new ExceptionBody(exceptionEnum.getError_code(),exceptionEnum.getError_message()),cause);
    }
}
