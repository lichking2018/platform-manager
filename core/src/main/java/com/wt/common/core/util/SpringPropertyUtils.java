package com.wt.common.core.util;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @ProjectName: syInfo
 * @Package: com.wt.common.core.util
 * @Description: 资源文件获取工具类
 * @Author: lichking2017@aliyun.com
 * @CreateDate: 2018/5/8 上午11:06
 * @Version: v1.0
 */
public class SpringPropertyUtils extends PropertyPlaceholderConfigurer{
    private static Map<String,String> properties = new HashMap<>();

    @Override
    protected void processProperties(ConfigurableListableBeanFactory beanFactoryToProcess, Properties props) throws BeansException {
        super.processProperties(beanFactoryToProcess, props);
        for(Object key:props.keySet()){
            properties.put(key.toString(),props.get(key).toString());
        }
    }

    public static String get(String key){
        return properties.get(key);
    }

}
