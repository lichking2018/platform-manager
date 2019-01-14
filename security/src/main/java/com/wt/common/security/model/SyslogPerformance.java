package com.wt.common.security.model;

import com.wt.common.core.annotations.Id;
import com.wt.common.core.annotations.Table;
import com.wt.common.core.entity.BaseEntity;

@Table(name = "SyslogPerformanceT")
public class SyslogPerformance extends BaseEntity<SyslogPerformance> {

    @Id(name = "logId")
    private String logId;

    private String remoteHost;

    private String requestURI;

    private String requestType;

    private Integer remotePort;

    private String timeConsuming;

    private String operatorId;

    private String operatorName;

    private String parameters;

    public String getLogId() {
        return logId;
    }

    public void setLogId(String logId) {
        this.logId = logId == null ? null : logId.trim();
    }

    public String getRemoteHost() {
        return remoteHost;
    }

    public SyslogPerformance setRemoteHost(String remoteHost) {
        this.remoteHost = remoteHost == null ? null : remoteHost.trim();
        return this;
    }

    public String getRequestURI() {
        return requestURI;
    }

    public SyslogPerformance setRequestURI(String requestURI) {
        this.requestURI = requestURI == null ? null : requestURI.trim();
        return this;
    }

    public String getRequestType() {
        return requestType;
    }

    public SyslogPerformance setRequestType(String requestType) {
        this.requestType = requestType == null ? null : requestType.trim();
        return this;
    }

    public Integer getRemotePort() {
        return remotePort;
    }

    public SyslogPerformance setRemotePort(Integer remotePort) {
        this.remotePort = remotePort;
        return this;
    }

    public String getTimeConsuming() {
        return timeConsuming;
    }

    public SyslogPerformance setTimeConsuming(String timeConsuming) {
        this.timeConsuming = timeConsuming == null ? null : timeConsuming.trim();
        return this;
    }

    public String getOperatorId() {
        return operatorId;
    }

    public SyslogPerformance setOperatorId(String operatorId) {
        this.operatorId = operatorId;
        return this;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public SyslogPerformance setOperatorName(String operatorName) {
        this.operatorName = operatorName;
        return this;
    }

    public String getParameters() {
        return parameters;
    }

    public void setParameters(String parameters) {
        this.parameters = parameters;
    }
}