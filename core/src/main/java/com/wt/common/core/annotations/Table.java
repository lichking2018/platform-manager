package com.wt.common.core.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @ProjectName: syInfo
 * @Package: com.wt.common.core.annotations
 * @Description: 用于entity,标注对应的数据库表名
 * @Author: lichking2017@aliyun.com
 * @CreateDate: 2018/4/18 下午10:19
 * @Version: v1.0
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Table {
    String name() default "";
}
