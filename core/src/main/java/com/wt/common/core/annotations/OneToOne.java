package com.wt.common.core.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @ProjectName: syInfo
 * @Package: com.wt.common.core.annotations
 * @Description:
 * @Author: lichking2017@aliyun.com
 * @CreateDate: 2018/5/3 下午8:00
 * @Version: v1.0
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface OneToOne {
    String property();
    String column();
}
