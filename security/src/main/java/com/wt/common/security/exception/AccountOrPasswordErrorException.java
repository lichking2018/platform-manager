package com.wt.common.security.exception;

/**
 * @ProjectName: syInfo
 * @Package: com.wt.common.security.exception
 * @Description:
 * @Author: lichking2017@aliyun.com
 * @CreateDate: 2018/5/15 下午4:32
 * @Version: v1.0
 */
public class AccountOrPasswordErrorException extends BaseSecurityException {
    public AccountOrPasswordErrorException() {
        super(ErrorCode.ACCOUNT_PASSWORD_ERROR);
    }
}
