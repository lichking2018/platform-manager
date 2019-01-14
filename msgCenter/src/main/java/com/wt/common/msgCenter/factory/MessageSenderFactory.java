package com.wt.common.msgCenter.factory;

import com.wt.common.msgCenter.adapter.MsgSenderI;
import com.wt.common.msgCenter.adapter.impl.EmailSender;
import com.wt.common.msgCenter.enums.MessageType;

/**
 * @ProjectName: myProject
 * @Package: com.wt.common.msgCenter.factory
 * @Description:
 * @Author: lichking2017@aliyun.com
 * @CreateDate: 2018/6/22 下午5:09
 * @Version: v1.0
 */
public class MessageSenderFactory {
    public static MsgSenderI produce(MessageType type) {
        switch (type) {
            case EMAIL:
                return new EmailSender();
            default:
                throw new IllegalArgumentException(String.format("未获得类型为%s的适配器", type));
        }

    }
}
