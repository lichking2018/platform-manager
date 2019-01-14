package com.wt.common.msgCenter.controller;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.wt.common.core.controller.BaseController;
import com.wt.common.core.result.HttpResultEntity;
import com.wt.common.msgCenter.enums.MessageType;
import com.wt.common.msgCenter.model.MessageTemplate;
import com.wt.common.msgCenter.service.MessageTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ProjectName: sydsxx
 * @Package: com.wt.common.msgCenter.controller
 * @Description: 消息模板控制器
 * @Author: lichking2017@aliyun.com
 * @CreateDate: 2018/6/13 下午12:50
 * @Version: v1.0
 */
@RestController
@RequestMapping("/messageTemplate")
public class MessageTemplateController extends BaseController{
    @Autowired
    private MessageTemplateService service;

    @RequestMapping(method = RequestMethod.POST)
    public HttpResultEntity<?> create(@RequestBody MessageTemplate messageTemplate){
        service.save(messageTemplate);
        return getSuccessResult();
    }

    @RequestMapping(value = "/{templateId}",method = RequestMethod.DELETE)
    public HttpResultEntity<?> delete(@PathVariable("templateId") String templateId){
        service.delete(templateId);
        return getSuccessResult();
    }

    @RequestMapping(method = RequestMethod.PUT)
    public HttpResultEntity<?> update(@RequestBody MessageTemplate messageTemplate){
        service.updateForSelective(messageTemplate);
        return getSuccessResult();
    }

    @RequestMapping(value = "/selectOne/{templateId}",method = RequestMethod.GET)
    public HttpResultEntity<?> selectOne(@PathVariable("templateId") String templateId){
        return getSuccessResult(service.findById(templateId,MessageTemplate.class));
    }

    @RequestMapping(value = "/getData",method = RequestMethod.GET)
    public HttpResultEntity<?> getData(){
        MessageTemplate messageTemplate = new MessageTemplate();
        messageTemplate.setTemplateName("用户注册通知");


        List<MessageTemplate> messageTemplates = service.selectByCondition(
                messageTemplate
        );

        return null;
    }

    @RequestMapping(value = "/queryTemplateType",method = RequestMethod.GET)
    public HttpResultEntity<?> queryTemplateType(){

        JsonArray messageTypes = new JsonArray();
        for(MessageType type:MessageType.values()){
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("typeId",type.name());
            jsonObject.addProperty("typeName",type.name());
            messageTypes.add(jsonObject);
        }
        return getSuccessResult(messageTypes);
    }

    @RequestMapping(value = "/batchRemoveTemplate", method = RequestMethod.DELETE)
    public HttpResultEntity<?> batchRemoveTemplate(@RequestBody List<String> templateIds) {
        service.deleteLogicBatch(templateIds);
        return getSuccessResult();
    }
}
