package com.wt.common.upload.enums;

/**
 * @ProjectName: syInfo
 * @Package: com.wt.common.upload.enums
 * @Description:异常描述
 * @Author: lichking2017@aliyun.com
 * @CreateDate: 2018/5/6 下午10:22
 * @Version: v1.0
 */
public enum UploadExceptionEnum {
    PATH_NOT_FOUND("100","没有发现路径"),
    FILE_READ_ERROR("101","文件读取失败"),
    FILE_STREAM_ERROR("102","文件流关闭异常"),
    FILE_PATH_CHECK_ERROR("103","文件目录检测错误，同名的文件存在无法创建文件夹");
    private String errorCode;
    private String message;

    UploadExceptionEnum(String errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
