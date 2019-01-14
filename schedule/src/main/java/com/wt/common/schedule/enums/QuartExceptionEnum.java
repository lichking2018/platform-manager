package com.wt.common.schedule.enums;

/**
 * @ProjectName: myProject
 * @Package: com.wt.common.schedule.enums
 * @Description:
 * @Author: lichking2017@aliyun.com
 * @CreateDate: 2018/7/27 下午2:47
 * @Version: v1.0
 */
public enum QuartExceptionEnum {
    GETTRIGGER_EXCEPTION("400","获取Trigger异常"),
    JOBCLASS_NOTFOUND_EXCEPTION("401","未找到调度作业对应的Class"),
    CREATEJOB_FAIL_EXCEPTION("402","添加作业到调度器失败"),
    UPDATEJOB_EXCEPTION("403","更新JOB异常"),
    DELETEJOB_EXCEPTION("404","删除JOB异常");

    private String error_code;
    private String error_message;

    QuartExceptionEnum(String error_code, String error_message) {
        this.error_code = error_code;
        this.error_message = error_message;
    }

    public String getError_code() {
        return error_code;
    }

    public String getError_message() {
        return error_message;
    }
}
