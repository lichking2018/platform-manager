package com.wt.common.upload.exception;

import com.wt.common.core.exception.BaseLogicException;
import com.wt.common.core.exception.ExceptionBody;
import com.wt.common.upload.enums.UploadExceptionEnum;

/**
 * @ProjectName: syInfo
 * @Package: com.wt.common.upload.exception
 * @Description:
 * @Author: lichking2017@aliyun.com
 * @CreateDate: 2018/5/6 下午9:38
 * @Version: v1.0
 */
public class UploadException extends BaseLogicException {

    public UploadException(UploadExceptionEnum exceptionEnum, Throwable e) {
        super(new ExceptionBody(exceptionEnum.getErrorCode(), exceptionEnum.getMessage()), e);
    }

    public UploadException(UploadExceptionEnum exceptionEnum) {
        super(new ExceptionBody(exceptionEnum.getErrorCode(), exceptionEnum.getMessage()));
    }
}
