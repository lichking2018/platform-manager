package com.wt.common.msgCenter.model;

import com.google.gson.annotations.JsonAdapter;
import com.wt.common.core.adapter.DateAdapter;
import com.wt.common.core.annotations.Id;
import com.wt.common.core.annotations.Table;
import com.wt.common.core.entity.BaseEntity;

import java.util.Date;

@Table(name = "MessageRecordT")
public class MessageRecord extends BaseEntity<MessageRecord>{
    @Id(name = "messageId")
    private String messageId;

    private String messageContent;

    private String messageType;

    private Boolean successFlag;

    @JsonAdapter(DateAdapter.class)
    private Date sendDateTime;

    private String target;

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId == null ? null : messageId.trim();
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent == null ? null : messageContent.trim();
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType == null ? null : messageType.trim();
    }

    public Boolean getSuccessFlag() {
        return successFlag;
    }

    public void setSuccessFlag(Boolean successFlag) {
        this.successFlag = successFlag;
    }

    public Date getSendDateTime() {
        return sendDateTime;
    }

    public void setSendDateTime(Date sendDateTime) {
        this.sendDateTime = sendDateTime;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target == null ? null : target.trim();
    }
}