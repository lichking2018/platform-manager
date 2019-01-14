package com.wt.common.security.controller;

import com.wt.common.core.annotations.IgnoreAuth;
import com.wt.common.core.controller.BaseController;
import com.wt.common.core.result.HttpResultEntity;
import com.wt.common.security.exception.BaseSecurityException;
import com.wt.common.security.handler.HttpSessionHandler;
import com.wt.common.security.model.SysUser;
import com.wt.common.security.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @ProjectName: src
 * @Package: com.wt.common.security.controller
 * @Description:
 * @Author: lichking2017@aliyun.com
 * @CreateDate: 2018/4/12 上午8:54
 * @Version: v1.0
 */
@RestController
@RequestMapping("/sysUser")
public class SysUserController extends BaseController {


    @Autowired
    private SysUserService sysUserService;

    @RequestMapping(value = "/userByUserId/{userId}", method = RequestMethod.GET)
    public HttpResultEntity<?> findSysUserByUserId(@PathVariable("userId") String userId) {
        SysUser sysUser = sysUserService.findById(userId, SysUser.class);
        return getSuccessResult(sysUser);
    }

    @RequestMapping(value = "/userAll", method = RequestMethod.GET)
    public HttpResultEntity<?> queryAllUser() {
        List<SysUser> sysUsers = sysUserService.selectAll();
        return getSuccessResult(sysUsers);
    }

    @IgnoreAuth
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public HttpResultEntity<?> login(String account, String password) throws BaseSecurityException {
        SysUser sysUser = sysUserService.login(account, password);
        request.getSession().setAttribute(HttpSessionHandler.Items.LOGINUSER.name(), sysUser);
        return getSuccessResult();
    }

    @RequestMapping(value = "/getData", method = RequestMethod.GET)
    public HttpResultEntity<?> getData() {
//        PageData pageData = sysUserService.queryAndPaging(new QueryHelper().pagingInfo());
        return getSuccessResult(null);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public HttpResultEntity<?> create(@Valid @RequestBody SysUser sysUser) {
        sysUserService.save(sysUser);
        return getSuccessResult();
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.DELETE)
    public HttpResultEntity<?> removeUser(@PathVariable("userId") String userId) {
        sysUserService.delete(userId);
        return getSuccessResult();
    }

    @RequestMapping(value = "/deleteBatch", method = RequestMethod.DELETE)
    public HttpResultEntity<?> removeUsers(@RequestBody List<String> userIds) {
        sysUserService.deleteLogicBatch(userIds);
        return getSuccessResult();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public HttpResultEntity<?> querySysMenu(@PathVariable("id") String id) {
        SysUser sysUser = sysUserService.findById(id, SysUser.class);
        return getSuccessResult(sysUser);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public HttpResultEntity<?> update(@RequestBody SysUser sysUser) {
        sysUserService.updateForSelective(sysUser);
        return getSuccessResult();
    }
}
