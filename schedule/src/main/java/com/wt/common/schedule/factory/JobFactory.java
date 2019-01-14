package com.wt.common.schedule.factory;

import org.quartz.spi.TriggerFiredBundle;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;

/**
 * @ProjectName: myProject
 * @Package: com.wt.common.schedule.factory
 * @Description:
 * @Author: lichking2017@aliyun.com
 * @CreateDate: 2018/7/27 上午11:23
 * @Version: v1.0
 */
//【知识点】，Aware接口的使用，获得applicationContext
//【知识点】，将job纳入到Spring容器管理，从而使job实例可以引用Spring容器中的其他Bean
public class JobFactory extends SpringBeanJobFactory implements ApplicationContextAware{
    private ApplicationContext applicationContext;


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    protected Object createJobInstance(TriggerFiredBundle bundle) throws Exception {
//        创建Job实例
        Object obj = super.createJobInstance(bundle);
//      将Job实例添加到Spring 容器中，那么JOB就可以注入其他的Bean了
        applicationContext.getAutowireCapableBeanFactory().autowireBean(obj);
        return obj;
    }
}
