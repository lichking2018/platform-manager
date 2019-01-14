package com.wt.common.email.domain;

/**
 * @ProjectName: sydsxx
 * @Package: domain
 * @Description: 邮件配置信息
 * @Author: lichking2017@aliyun.com
 * @CreateDate: 2018/6/9 下午3:17
 * @Version: v1.0
 */
public class EmailConfig {
    private String host;
    private String userName;
    private String port;
    private String password;
    private String from;


    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }
    
}
