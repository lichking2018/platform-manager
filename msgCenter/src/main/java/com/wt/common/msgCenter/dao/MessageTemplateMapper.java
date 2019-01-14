package com.wt.common.msgCenter.dao;

import com.wt.common.core.dao.MapperSupport;
import com.wt.common.msgCenter.model.MessageTemplate;

public interface MessageTemplateMapper extends MapperSupport<MessageTemplate>{

//    List<MessageTemplate> queryAndPaging(@Param("queryHelper") QueryHelper queryHelper);

    Integer queryAndPagingTotal();
}