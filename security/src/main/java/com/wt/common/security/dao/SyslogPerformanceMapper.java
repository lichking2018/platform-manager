package com.wt.common.security.dao;

import com.wt.common.core.dao.MapperSupport;
import com.wt.common.security.model.SyslogPerformance;

public interface SyslogPerformanceMapper extends MapperSupport<SyslogPerformance>{

    Integer queryAndPagingTotal();
}