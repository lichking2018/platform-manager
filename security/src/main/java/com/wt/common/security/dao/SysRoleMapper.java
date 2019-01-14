package com.wt.common.security.dao;

import com.wt.common.security.model.SysRole;

public interface SysRoleMapper {
    int insert(SysRole record);

    int insertSelective(SysRole record);
}