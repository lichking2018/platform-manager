package com.wt.common.security.service.impl;

import com.wt.common.core.service.impl.ServiceSupport;
import com.wt.common.security.dao.SysMenuMapper;
import com.wt.common.security.dao.SysUserMapper;
import com.wt.common.security.handler.SysMenuHandler;
import com.wt.common.security.model.SysMenu;
import com.wt.common.security.service.SysMenuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ProjectName: src
 * @Package: com.wt.common.security.service.impl
 * @Description:
 * @Author: lichking2017@aliyun.com
 * @CreateDate: 2018/4/15 上午9:25
 * @Version: v1.0
 */
@Service
public class SysMenuServiceImpl extends ServiceSupport<SysMenu, SysMenuMapper> implements SysMenuService {
    Logger logger = LoggerFactory.getLogger(SysMenuServiceImpl.class);

    @Autowired
    private SysMenuMapper sysMenuMapper;

    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    protected SysMenuMapper getMapper() {
        return this.sysMenuMapper;
    }


    @Override
    public List<SysMenu> queryAll() {
        return sysMenuMapper.findAllAndParentMenu();
    }

    @Override
    public List<SysMenu> querySubMenuByMenuId(String menuId) {
        SysMenu sysMenu = sysMenuMapper.selectById(menuId, SysMenu.class);
        SysMenuHandler.generateMenuTree(sysMenu, this.queryAll());
        return sysMenu.getChildren();
    }

//    @Override
//    public PageData<SysMenu> queryAndPaging(QueryHelper queryHelper) {
//        PageData<SysMenu> result = new PageData<>();
//        result.setData(this.getMapper().queryAndPaging(queryHelper));
//        result.setRecordsTotal(this.getMapper().queryAndPagingTotal());
//        return result;
//    }

    @Override
    public void deleteLogic(String id) {
        getMapper().deleteLogicByEntity(new SysMenu().setMenuId(id));
    }

    @Override
    public void deleteLogicBatch(List<String> ids) {
            getMapper().deleteLogicBatch(ids,SysMenu.class);

    }

    @Override
    public SysMenu queryMenuAndParent(String menuId) {
        return getMapper().findMenuAndParent(menuId);
    }

    @Override
    public void delete(String id) {
        getMapper().delete(id,SysMenu.class);
    }
}
