package com.biao.xb.dao;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Properties;

public class BaseDao {

    public JdbcTemplate jdbcTemplate;

    public BaseDao() {
        try {
            DruidDataSource dataSource = new DruidDataSource();
            Properties properties = new Properties();
            properties.load(this.getClass().getClassLoader().getResourceAsStream("jdbc.properties"));

            dataSource.setUsername(properties.getProperty("jdbc.username"));
            dataSource.setPassword(properties.getProperty("jdbc.password"));
            dataSource.setUrl(properties.getProperty("jdbc.url"));
            dataSource.setDriverClassName(properties.getProperty("jdbc.driverClassName"));

            jdbcTemplate = new JdbcTemplate(dataSource);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

}
