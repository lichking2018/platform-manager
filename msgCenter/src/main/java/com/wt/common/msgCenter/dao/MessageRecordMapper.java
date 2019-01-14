package com.wt.common.msgCenter.dao;

import com.wt.common.core.dao.MapperSupport;
import com.wt.common.msgCenter.model.MessageRecord;

public interface MessageRecordMapper extends MapperSupport<MessageRecord>{

    Integer queryAndPagingTotal();
}