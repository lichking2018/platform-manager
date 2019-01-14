package com.wt.common.core.result;

/**
 * @ProjectName: src
 * @Package: com.wt.common.core.entity
 * @Description:
 * @Author: lichking2017@aliyun.com
 * @CreateDate: 2018/4/12 下午3:08
 * @Version: v1.0
 */
public class HttpResultEntity<R> {

    private String errorCode;

    private String message;

    private R result;


    public HttpResultEntity(String errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }

    public HttpResultEntity(HttpResultHandle.HttpResultEnum resultEnum) {
        this.errorCode = resultEnum.getErrorCode();
        this.message = resultEnum.getMessage();
    }

    public HttpResultEntity(String errorCode, String message, R result) {
        this.errorCode = errorCode;
        this.message = message;
        this.result = result;
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

    public R getResult() {
        return result;
    }

    public void setResult(R result) {
        this.result = result;
    }
}
