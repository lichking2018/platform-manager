package com.wt.common.upload.exception;

import com.wt.common.upload.enums.UploadExceptionEnum;

/**
 * @ProjectName: syInfo
 * @Package: com.wt.common.upload.exception
 * @Description:
 * @Author: lichking2017@aliyun.com
 * @CreateDate: 2018/5/8 下午1:49
 * @Version: v1.0
 */
public class FilePathCheckException extends UploadException{

    public FilePathCheckException(){
        super(UploadExceptionEnum.FILE_PATH_CHECK_ERROR);
    }
}
