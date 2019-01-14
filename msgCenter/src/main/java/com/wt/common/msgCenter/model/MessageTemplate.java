package com.wt.common.msgCenter.model;

import com.wt.common.core.annotations.Id;
import com.wt.common.core.annotations.Table;
import com.wt.common.core.entity.BaseEntity;
import com.wt.common.msgCenter.enums.MessageType;

@Table(name = "MessageTemplateT")
public class MessageTemplate extends BaseEntity<MessageTemplate>{

    @Id(name = "templateId")
    private String templateId;

    private String templateName;

    private String templateCode;

    private String templateContent;

    private MessageType messageType;

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId == null ? null : templateId.trim();
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName == null ? null : templateName.trim();
    }

    public String getTemplateContent() {
        return templateContent;
    }

    public void setTemplateContent(String templateContent) {
        this.templateContent = templateContent == null ? null : templateContent.trim();
    }

    public MessageType getMessageType() {
        return messageType;
    }

    public void setMessageType(MessageType messageType) {
        this.messageType = messageType;
    }

    public String getTemplateCode() {
        return templateCode;
    }

    public MessageTemplate setTemplateCode(String templateCode) {
        this.templateCode = templateCode;
        return this;
    }
}