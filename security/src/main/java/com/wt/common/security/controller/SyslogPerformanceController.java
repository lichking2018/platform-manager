package com.wt.common.security.controller;

import com.wt.common.core.controller.BaseController;
import com.wt.common.core.result.HttpResultEntity;
import com.wt.common.security.service.SyslogPerformanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ProjectName: syInfo
 * @Package: com.wt.common.core.controller
 * @Description:
 * @Author: lichking2017@aliyun.com
 * @CreateDate: 2018/5/13 下午8:28
 * @Version: v1.0
 */
@RestController
@RequestMapping("/syslogPerformance")
public class SyslogPerformanceController extends BaseController{

    @Autowired
    private SyslogPerformanceService syslogPerformanceService;

    @RequestMapping(value = "/getData",method = RequestMethod.GET)
    public HttpResultEntity<?> getData(){
//        PageData<SyslogPerformance> syslogPerformancePageData = syslogPerformanceService.queryAndPaging(new QueryHelper().pagingInfo());
        return getSuccessResult(null);
    }
}
