package com.wt.common.security.service;

import com.wt.common.core.service.BaseService;
import com.wt.common.security.model.SysMenu;

import java.util.List;

/**
 * @ProjectName: src
 * @Package: com.wt.common.security.service
 * @Description:
 * @Author: lichking2017@aliyun.com
 * @CreateDate: 2018/4/15 上午9:24
 * @Version: v1.0
 */
public interface SysMenuService extends BaseService<SysMenu>{
    /**
     * 查询所有的菜单
     * @return
     */
    List<SysMenu> queryAll();

    /**
     * 根据菜单ID，查询下属子菜单
     * @param menuId
     * @return
     */
    List<SysMenu> querySubMenuByMenuId(String menuId);

    /**
     * 根据Id获取 菜单及父菜单
     * @param menuId
     * @return
     */
    SysMenu queryMenuAndParent(String menuId);

}
