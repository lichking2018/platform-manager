package com.wt.common.schedule.provider;

import com.alibaba.druid.pool.DruidDataSource;
import com.wt.common.core.util.SpringPropertyUtils;
import org.quartz.utils.ConnectionProvider;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @ProjectName: myProject
 * @Package: com.wt.common.schedule.provider
 * @Description:
 * @Author: lichking2017@aliyun.com
 * @CreateDate: 2018/7/24 下午4:49
 * @Version: v1.0
 */
public class DataSourceProvider implements ConnectionProvider {

    private DruidDataSource dataSource;

    @Override
    public Connection getConnection() throws SQLException {
        Connection connection = dataSource.getConnection();
        return connection;
    }

    @Override
    public void shutdown() throws SQLException {
        dataSource.close();
    }

    @Override
    public void initialize() throws SQLException {
        dataSource = new DruidDataSource();
        dataSource.setUsername(SpringPropertyUtils.get("jdbc_user"));
        dataSource.setPassword(SpringPropertyUtils.get("jdbc_password"));
        dataSource.setUrl(this.analyzeUrl(SpringPropertyUtils.get("jdbc_url")));
        dataSource.setMaxActive(20);
        dataSource.setInitialSize(1);
        dataSource.setMinIdle(1);
        dataSource.setMaxWait(Integer.parseInt(SpringPropertyUtils.get("max_wait")));
    }


    private String analyzeUrl(String url){
        return url.replace("${jdbc_ip}",SpringPropertyUtils.get("jdbc_ip")).replace("${jdbc_database}",
                SpringPropertyUtils.get("jdbc_database"));
    }
}
