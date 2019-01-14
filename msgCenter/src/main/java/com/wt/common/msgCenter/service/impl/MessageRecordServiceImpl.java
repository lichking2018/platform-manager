package com.wt.common.msgCenter.service.impl;

import com.wt.common.core.service.impl.ServiceSupport;
import com.wt.common.msgCenter.dao.MessageRecordMapper;
import com.wt.common.msgCenter.model.MessageRecord;
import com.wt.common.msgCenter.service.MessageRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ProjectName: sydsxx
 * @Package: com.wt.common.msgCenter.service.impl
 * @Description:
 * @Author: lichking2017@aliyun.com
 * @CreateDate: 2018/6/12 下午3:46
 * @Version: v1.0
 */
@Service
public class MessageRecordServiceImpl extends ServiceSupport<MessageRecord,MessageRecordMapper> implements MessageRecordService{

    @Autowired
    private MessageRecordMapper mapper;

    @Override
    protected MessageRecordMapper getMapper() {
        return mapper;
    }

//    @Override
//    public PageData<MessageRecord> queryAndPaging(QueryHelper queryHelper) {
//        List<MessageRecord> messageRecords = mapper.queryAndPaging(queryHelper);
//        Integer total = mapper.queryAndPagingTotal();
//        PageData<MessageRecord> pageData = new PageData<>();
//        return pageData.setData(messageRecords).setRecordsTotal(total);
//    }


}
