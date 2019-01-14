package com.wt.common.security.service.impl;


import com.wt.common.core.service.impl.ServiceSupport;
import com.wt.common.security.dao.SyslogPerformanceMapper;
import com.wt.common.security.model.SyslogPerformance;
import com.wt.common.security.service.SyslogPerformanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ProjectName: syInfo
 * @Package: com.wt.common.core.service.impl
 * @Description:
 * @Author: lichking2017@aliyun.com
 * @CreateDate: 2018/5/13 下午8:24
 * @Version: v1.0
 */
@Service
public class SyslogPerformanceServiceImpl extends ServiceSupport<SyslogPerformance,SyslogPerformanceMapper> implements SyslogPerformanceService {

    @Autowired
    private SyslogPerformanceMapper syslogPerformanceMapper;

    @Override
    protected SyslogPerformanceMapper getMapper() {
        return syslogPerformanceMapper;
    }


//    @Override
//    public PageData<SyslogPerformance> queryAndPaging(QueryHelper queryHelper) {
//        List<SyslogPerformance> syslogPerformanceList = getMapper().queryAndPaging(queryHelper);
//        Integer total = getMapper().queryAndPagingTotal();
//
//        return new PageData<SyslogPerformance>().setRecordsTotal(total).setData(syslogPerformanceList);
//    }
}
