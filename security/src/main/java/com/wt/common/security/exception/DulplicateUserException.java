package com.wt.common.security.exception;

/**
 * @ProjectName: syInfo
 * @Package: com.wt.common.security.exception
 * @Description:
 * @Author: lichking2017@aliyun.com
 * @CreateDate: 2018/5/15 下午5:51
 * @Version: v1.0
 */
public class DulplicateUserException extends BaseSecurityException{

    public DulplicateUserException() {
        super(ErrorCode.DULPLICATE_ACCOUNT_EXCEPTION);
    }
}
