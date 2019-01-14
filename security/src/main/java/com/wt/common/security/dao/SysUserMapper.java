package com.wt.common.security.dao;

import com.wt.common.core.dao.MapperSupport;
import com.wt.common.security.model.SysUser;

public interface SysUserMapper extends MapperSupport<SysUser> {


    Integer queryAndPagingTotal();
}