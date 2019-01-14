package com.wt.common.schedule.exception;

import com.wt.common.schedule.enums.QuartExceptionEnum;

/**
 * @ProjectName: myProject
 * @Package: com.wt.common.schedule.exception
 * @Description:
 * @Author: lichking2017@aliyun.com
 * @CreateDate: 2018/7/27 下午3:31
 * @Version: v1.0
 */
public class GetTriggerException extends BaseQuartException{

    public GetTriggerException(QuartExceptionEnum exceptionEnum, Throwable cause) {
        super(exceptionEnum, cause);
    }
}
