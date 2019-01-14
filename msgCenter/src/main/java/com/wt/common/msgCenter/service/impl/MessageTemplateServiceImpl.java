package com.wt.common.msgCenter.service.impl;

import com.wt.common.core.service.impl.ServiceSupport;
import com.wt.common.msgCenter.dao.MessageTemplateMapper;
import com.wt.common.msgCenter.model.MessageTemplate;
import com.wt.common.msgCenter.service.MessageTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ProjectName: sydsxx
 * @Package: com.wt.common.msgCenter.service.impl
 * @Description:
 * @Author: lichking2017@aliyun.com
 * @CreateDate: 2018/6/12 下午3:38
 * @Version: v1.0
 */
@Service
public class MessageTemplateServiceImpl extends ServiceSupport<MessageTemplate,MessageTemplateMapper> implements MessageTemplateService{

    @Autowired
    private MessageTemplateMapper mapper;

    @Override
    protected MessageTemplateMapper getMapper() {
        return mapper;
    }

    @Override
    public void delete(String id) {
        mapper.delete(id,MessageTemplate.class);
    }
}
