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
 * @CreateDate: 2018/4/20 下午2:58
 * @Version: v1.0
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Id {
    String name();
}
