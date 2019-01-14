package com.wt.common.security.model;

import com.wt.common.core.annotations.Id;
import com.wt.common.core.annotations.Table;
import com.wt.common.core.entity.BaseEntity;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Table(name = "SysMenuT")
public class SysMenu extends BaseEntity<SysMenu>{
    @Id(name = "menuId")
    private String menuId;

    private String menuName;

    @NotEmpty(message = "菜单名称不能为空")
    private String menuUrl;

    private String icon;

    @com.wt.common.core.annotations.OneToOne(property = "menuId",column = "pId")
    private SysMenu parentSysMenu;

    private List<SysMenu> children;

    public String getMenuId() {
        return menuId;
    }

    public SysMenu setMenuId(String menuId) {
        this.menuId = menuId;
        return this;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName == null ? null : menuName.trim();
    }

    public String getMenuUrl() {
        return menuUrl;
    }

    public void setMenuUrl(String menuUrl) {
        this.menuUrl = menuUrl == null ? null : menuUrl.trim();
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public SysMenu getParentSysMenu() {
        return parentSysMenu;
    }

    public void setParentSysMenu(SysMenu parentSysMenu) {
        this.parentSysMenu = parentSysMenu;
    }

    public List<SysMenu> getChildren() {
        if(CollectionUtils.isEmpty(children)){
            children = new ArrayList<>();
        }

        return children;
    }

    public void setChildren(List<SysMenu> children) {
        this.children = children;
    }
}