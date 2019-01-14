package com.wt.common.schedule.exception;

import com.wt.common.schedule.enums.QuartExceptionEnum;

/**
 * @ProjectName: myProject
 * @Package: com.wt.common.schedule.exception
 * @Description:
 * @Author: lichking2017@aliyun.com
 * @CreateDate: 2018/7/28 下午2:58
 * @Version: v1.0
 */
public class SecheduleJobUpdateException extends BaseQuartException{
    public SecheduleJobUpdateException(QuartExceptionEnum exceptionEnum, Throwable cause) {
        super(exceptionEnum, cause);
    }
}
