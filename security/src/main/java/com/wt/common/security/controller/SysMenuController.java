package com.wt.common.security.controller;

import com.wt.common.core.controller.BaseController;
import com.wt.common.core.result.HttpResultEntity;
import com.wt.common.security.model.SysMenu;
import com.wt.common.security.service.SysMenuService;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @ProjectName: src
 * @Package: com.wt.common.security.controller
 * @Description:
 * @Author: lichking2017@aliyun.com
 * @CreateDate: 2018/4/15 上午9:21
 * @Version: v1.0
 */
@Validated
@RestController
@RequestMapping("/sysMenu")
public class SysMenuController extends BaseController {


    @Value("${menu_root_id}")
    private String menuRootId;

    @Autowired
    private SysMenuService sysMenuService;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public HttpResultEntity<?> create(@Valid @RequestBody SysMenu sysMenu) {
        sysMenuService.save(sysMenu);
        return getSuccessResult();
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public HttpResultEntity<?> delete(@PathVariable("id") String menuId) {
        sysMenuService.deleteLogic(menuId);
        return getSuccessResult();
    }

    @RequestMapping(value = "/deleteBatch", method = RequestMethod.DELETE)
    public HttpResultEntity<?> deleteBatch(@RequestBody List<String> ids) {
        sysMenuService.deleteLogicBatch(ids);
        return getSuccessResult();
    }

    @RequestMapping(value = "/getData", method = RequestMethod.GET)
    public HttpResultEntity<?> getData() {
//        PageData<SysMenu> sysMenuPageData = sysMenuService.queryAndPaging(new QueryHelper().pagingInfo());
        return getSuccessResult(null);
    }

    @RequestMapping(value = "/queryAdminMenu", method = RequestMethod.GET)
    public HttpResultEntity<?> queryAdminMenu() {
        List<SysMenu> sysMenus = sysMenuService.querySubMenuByMenuId(menuRootId);
        return getSuccessResult(sysMenus);
    }

    @RequestMapping(value = "/queryAll", method = RequestMethod.GET)
    public HttpResultEntity<?> queryAll() {
        List<SysMenu> sysMenus = sysMenuService.queryAll();
        return getSuccessResult(sysMenus);
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public HttpResultEntity<?> querySysMenu(@PathVariable("id") @Length(min = 1,max = 5,message = "长度大于1，小于5") String
                                                        id) {
        SysMenu sysMenu = sysMenuService.queryMenuAndParent(id);
        return getSuccessResult(sysMenu);
    }

    @RequestMapping(method=RequestMethod.PUT)
    public HttpResultEntity<?> update(@RequestBody SysMenu sysMenu){
        sysMenuService.updateForSelective(sysMenu);
        return getSuccessResult();
    }

}
