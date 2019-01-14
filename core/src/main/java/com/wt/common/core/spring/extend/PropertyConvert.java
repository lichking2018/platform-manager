package com.wt.common.core.spring.extend;

import org.apache.ibatis.ognl.IntHashMap;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import java.util.Map;
import java.util.Properties;

/**
 * @ProjectName: syInfo
 * @Package: com.wt.common.core.spring.extend
 * @Description:
 * @Author: lichking2017@aliyun.com
 * @CreateDate: 2018/4/23 上午9:24
 * @Version: v1.0
 */
public class PropertyConvert extends PropertyPlaceholderConfigurer {

    private static Map<String,String> properties = new IntHashMap();

    @Override
    protected void processProperties(ConfigurableListableBeanFactory beanFactoryToProcess, Properties props) throws BeansException {
        super.processProperties(beanFactoryToProcess, props);
        for(Object key:props.keySet()){
            properties.put((String)key,(String)(props.get(key)));
        }
    }

    /**
     * 获取资源
     * @param key
     * @return
     */
    public static String getProperty(String key){
        return properties.get(key);
    }
}
