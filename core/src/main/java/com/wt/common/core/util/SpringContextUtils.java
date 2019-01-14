package com.wt.common.core.util;

import com.wt.common.core.spring.extend.PropertyConvert;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.AbstractRefreshableWebApplicationContext;

import javax.sql.DataSource;
import java.sql.Connection;
import java.util.Map;

/**
 * @ProjectName: syInfo
 * @Package: com.wt.common.core.util
 * @Description: Spring容器工具类
 * @Author: lichking2017@aliyun.com
 * @CreateDate: 2018/4/23 上午9:20
 * @Version: v1.0
 */
@Component
public class SpringContextUtils implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    private static final String DATASOURCE = "dataSource";


    /**
     * 获取资源值
     *
     * @param key
     * @return
     */
    public static String getPropertyValue(String key) {
        return PropertyConvert.getProperty(key);
    }

    @Override
    public void setApplicationContext(ApplicationContext _applicationContext) throws BeansException {
        applicationContext = _applicationContext;
    }


    /**
     * 刷新SpringContext容器
     */
    public static void refresh() {
        ((AbstractRefreshableWebApplicationContext) applicationContext).refresh();
    }


    /**
     * 根据类型获取Bean
     *
     * @param type 类型
     * @param <T>  类型参数
     * @return
     */
    public static <T> Map<String, T> getBeans(Class<T> type) {
        return applicationContext.getBeansOfType(type);
    }

    /**
     * 获取单个Bean
     *
     * @param type 类型
     * @param <T>  类型参数
     * @return
     */
    public static <T> T getBean(Class<T> type) {
        return applicationContext.getBean(type);
    }

    /**
     * 根据Bean名称获取Bean
     *
     * @param beanName
     * @return
     */
    public static Object getBean(String beanName) {
        return applicationContext.getBean(beanName);
    }

    public static <T> T getBean(String beanName, Class<T> type) {
        return applicationContext.getBean(beanName, type);
    }

    /**
     * 获取连接
     *
     * @return
     */
    public static Connection getConnection() {
        return DataSourceUtils.getConnection((DataSource) getBean(DATASOURCE));
    }

    /**
     * 获取连接
     *
     * @param dataSource 数据库连接池 bean名称
     * @return
     */
    public static Connection getConnection(String dataSource) {
        return DataSourceUtils.getConnection((DataSource) getBean(dataSource));
    }


    /**
     * 关闭数据库连接
     *
     * @param connection
     */
    public void closeConnection(Connection connection) {
        DataSourceUtils.releaseConnection(connection, (DataSource) getBean(DATASOURCE));
    }


    /**
     * 判断Bean容器，是否包含指定的bean
     *
     * @param beanName 实例名称
     * @return
     */
    public Boolean containsBean(String beanName) {
        return applicationContext.containsBean(beanName);
    }

}
