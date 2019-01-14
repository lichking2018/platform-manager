package com.wt.common.security.dao;

import com.wt.common.core.dao.MapperSupport;
import com.wt.common.security.model.SysMenu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysMenuMapper extends MapperSupport<SysMenu>{


    List<SysMenu> findAllAndParentMenu();

    SysMenu findMenuAndParent(@Param("menuId") String menuId);

//    List<SysMenu> queryAndPaging(@Param("queryHelper") QueryHelper queryHelper);

    Integer queryAndPagingTotal();

}