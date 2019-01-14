package com.wt.common.security.service;

import com.wt.common.core.service.BaseService;
import com.wt.common.security.exception.BaseSecurityException;
import com.wt.common.security.model.SysUser;

/**
 * @ProjectName: src
 * @Package: com.wt.common.security.service
 * @Description:
 * @Author: lichking2017@aliyun.com
 * @CreateDate: 2018/4/12 上午9:00
 * @Version: v1.0
 */
public interface SysUserService extends BaseService<SysUser>{

    SysUser login(String account,String password) throws BaseSecurityException;
}
