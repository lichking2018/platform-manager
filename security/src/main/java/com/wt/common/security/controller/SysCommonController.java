package com.wt.common.security.controller;

import com.wt.common.core.annotations.IgnoreAuth;
import com.wt.common.core.controller.BaseController;
import com.wt.common.core.result.HttpResultEntity;
import com.wt.common.core.util.CommonUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @ProjectName: myProject
 * @Package: com.wt.common.security.controller
 * @Description:
 * @Author: lichking2017@aliyun.com
 * @CreateDate: 2018/9/11 下午2:31
 * @Version: v1.0
 */
@RestController
@RequestMapping("/sys/common")
public class SysCommonController extends BaseController{
    @RequestMapping(value = "/sysDateTime",method = RequestMethod.GET)
    @IgnoreAuth
    public HttpResultEntity getSysDateTime(){
        String dateTime = CommonUtils.dateFormat("yyyy-MM-dd HH:mm:ss",new Date());
        return getSuccessResult(dateTime);
    }
}
