package com.wt.common.upload.exception;

import com.wt.common.upload.enums.UploadExceptionEnum;

/**
 * @ProjectName: syInfo
 * @Package: com.wt.common.upload.exception
 * @Description:
 * @Author: lichking2017@aliyun.com
 * @CreateDate: 2018/5/7 上午7:32
 * @Version: v1.0
 */
public class PathNotFoundException extends UploadException{

    private String fileName;
    public PathNotFoundException(String fileName){
        super(UploadExceptionEnum.PATH_NOT_FOUND);
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
