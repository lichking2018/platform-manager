package com.wt.common.core.result;

/**
 * @ProjectName: src
 * @Package: com.wt.common.core.result
 * @Description:
 * @Author: lichking2017@aliyun.com
 * @CreateDate: 2018/4/12 下午7:51
 * @Version: v1.0
 */
public class HttpResultHandle {
    public static String NOLOG_CODE ="-1";
    //【知识点】，阿拉伯数字1在前台Js转换的时候会转化为true，0转化为false，因此这里取同意
    public static String SUCCESS_CODE="1";
    public static String ERROR_CODE="0";

    public enum HttpResultEnum{
        NOTLOG(NOLOG_CODE,"请重新登录"),SUCCESS(SUCCESS_CODE),ERROR(ERROR_CODE,"网络异常");
        private String errorCode;
        private String message;
        HttpResultEnum(String errorCode) {
            this.errorCode = errorCode;
        }

        HttpResultEnum(String errorCode, String message) {
            this.errorCode = errorCode;
            this.message = message;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getErrorCode() {
            return errorCode;
        }

        public void setErrorCode(String errorCode) {
            this.errorCode = errorCode;
        }
    }



    public static HttpResultEntity<?> getSuccessResult(){
        return getSuccessResult(null);
    }


    public static HttpResultEntity<?> getSuccessResult(Object result){
        return new HttpResultEntity<>(HttpResultEnum.SUCCESS.errorCode,HttpResultEnum.SUCCESS.name(),result);
    }

    public static HttpResultEntity<?> getErrorResult(){
        return new HttpResultEntity<>(HttpResultEnum.ERROR.errorCode,HttpResultEnum.ERROR.message);
    }

    public static HttpResultEntity<?> getErrorResult(String message){
        return new HttpResultEntity<>(HttpResultEnum.ERROR.errorCode,message);
    }

    public static HttpResultEntity<?> getErrorResult(Object result){
        return new HttpResultEntity<>(HttpResultEnum.ERROR.errorCode,HttpResultEnum.ERROR.name(),result);
    }

    public static HttpResultEntity<?> getErrorResult(String errorCode,String message, Object result){
        return new HttpResultEntity<>(errorCode,message,result);

    }

    public static HttpResultEntity<?> getErrorResult(String errorCode,String message){
        return new HttpResultEntity<>(errorCode,message,null);
    }
}
