package com.wt.common.msgCenter.adapter;

import com.google.gson.JsonObject;

import java.util.List;

/**
 * @ProjectName: myProject
 * @Package: com.wt.common.msgCenter.adapter
 * @Description:
 * @Author: lichking2017@aliyun.com
 * @CreateDate: 2018/6/19 下午5:32
 * @Version: v1.0
 */
public interface MsgSenderI {
    void send(List<String> target, String msgContent, JsonObject param);
}
