package com.wt.common.msgCenter.util;

import com.google.gson.JsonObject;
import com.wt.common.core.util.SpringContextUtils;
import com.wt.common.msgCenter.adapter.MsgSenderI;
import com.wt.common.msgCenter.enums.MessageType;
import com.wt.common.msgCenter.factory.MessageSenderFactory;
import com.wt.common.msgCenter.model.MessageRecord;
import com.wt.common.msgCenter.model.MessageTemplate;
import com.wt.common.msgCenter.service.MessageRecordService;
import com.wt.common.msgCenter.service.MessageTemplateService;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ProjectName: myProject
 * @Package: com.wt.common.msgCenter.util
 * @Description: 消息发送工具类
 * @Author: lichking2017@aliyun.com
 * @CreateDate: 2018/6/19 下午5:37
 * @Version: v1.0
 */
public class MessageSendUtils {

    /**
     * 发送消息接口
     *
     * @param templateCode 模板代码
     * @param param        参数
     */
    public static void send(Object target, String templateCode, JsonObject param) {
        List targets = new ArrayList();
        if (!(target instanceof ArrayList)) {
            targets.add(target);
        } else {
            targets.addAll((ArrayList) target);
        }

        Assert.notNull(templateCode, "模板代码不能为null");
        MessageTemplate messageTemplate = getTemplateContent(templateCode);
        String templateContent = messageTemplate.getTemplateContent();
        String realContent = MessageRegularUtils.replaceDoubleBraces(templateContent, param);

        MsgSenderI msgSenderI = MessageSenderFactory.produce(messageTemplate.getMessageType());
        msgSenderI.send(targets, realContent, param);
        //记录发送日志
        messageRecording(targets,realContent,messageTemplate.getMessageType());
    }


    private static MessageTemplate getTemplateContent(String templateCode) {
        MessageTemplateService templateService = (MessageTemplateService) SpringContextUtils.getBean("messageTemplateServiceImpl");

        List<MessageTemplate> messageTemplates = templateService.selectByCondition(new MessageTemplate()
                .setTemplateCode
                        (templateCode));

        if (CollectionUtils.isEmpty(messageTemplates)) {
            throw new IllegalArgumentException(String.format("未找到 %s 对应的模板", templateCode));
        }

        return messageTemplates.get(0);
    }

    private static void messageRecording(List<String> targets, String realContent, MessageType messageType){
        MessageRecord messageRecord = new MessageRecord();
        MessageRecordService service = (MessageRecordService)SpringContextUtils.getBean("messageRecordServiceImpl");
        messageRecord.setMessageContent(realContent);
        messageRecord.setMessageType(messageType.name());
        messageRecord.setSuccessFlag(true);
        messageRecord.setTarget(targets.stream().collect(Collectors.joining(",")));
        messageRecord.setSendDateTime(new Date());
        service.save(messageRecord);
    }


}
