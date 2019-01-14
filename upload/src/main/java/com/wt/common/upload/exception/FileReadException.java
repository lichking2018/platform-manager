package com.wt.common.upload.exception;

import com.wt.common.upload.enums.UploadExceptionEnum;

/**
 * @ProjectName: syInfo
 * @Package: com.wt.common.upload.exception
 * @Description:
 * @Author: lichking2017@aliyun.com
 * @CreateDate: 2018/5/8 下午12:42
 * @Version: v1.0
 */
public class FileReadException extends UploadException{
    public FileReadException(){
        super(UploadExceptionEnum.FILE_READ_ERROR);
    }

    public FileReadException(Throwable e){
        super(UploadExceptionEnum.FILE_READ_ERROR,e);
    }
}
