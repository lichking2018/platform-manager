package com.wt.common.security.handler;

import com.wt.common.security.model.SysMenu;

import java.util.List;

/**
 * @ProjectName: src
 * @Package: com.wt.common.security.handler
 * @Description:
 * @Author: lichking2017@aliyun.com
 * @CreateDate: 2018/4/15 上午9:47
 * @Version: v1.0
 */
public class SysMenuHandler {

    /**
     * 获取菜单下的子菜单树
     * @param sysMenu
     * @param allMenus
     */
    public static void generateMenuTree(SysMenu sysMenu, List<SysMenu> allMenus){
        for(SysMenu menu:allMenus){
            if(menu.getParentSysMenu()==null){
                continue;
            }
            if(sysMenu.getMenuId().equals(menu.getParentSysMenu().getMenuId())){
                sysMenu.getChildren().add(menu);
                generateMenuTree(menu,allMenus);
            }
        }
    }
}
