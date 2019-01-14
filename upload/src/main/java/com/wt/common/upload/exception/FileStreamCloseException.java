package com.wt.common.upload.exception;

import com.wt.common.upload.enums.UploadExceptionEnum;

/**
 * @ProjectName: syInfo
 * @Package: com.wt.common.upload.exception
 * @Description: 文件关闭流异常
 * @Author: lichking2017@aliyun.com
 * @CreateDate: 2018/5/8 下午12:49
 * @Version: v1.0
 */
public class FileStreamCloseException extends UploadException {
    public FileStreamCloseException(Throwable e) {
        super(UploadExceptionEnum.FILE_STREAM_ERROR, e);
    }
}
