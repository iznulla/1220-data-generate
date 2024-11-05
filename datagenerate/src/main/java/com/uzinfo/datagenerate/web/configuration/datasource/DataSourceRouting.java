package com.uzinfo.datagenerate.web.configuration.datasource;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import jakarta.annotation.PostConstruct;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.stereotype.Component;

@Component
public class DataSourceRouting extends AbstractRoutingDataSource {
    private final DataSourceTwoConfig dataSourceTwoConfig;
    private final DataSourceContextHolder dataSourceContextHolder;
    private Map<Object, Object> dataSourceMap = new HashMap<>();

    public DataSourceRouting(DataSourceContextHolder dataSourceContextHolder,
                             DataSourceTwoConfig dataSourceTwoConfig) {
        this.dataSourceTwoConfig = dataSourceTwoConfig;
        this.dataSourceContextHolder = dataSourceContextHolder;

    }

    @PostConstruct
    private void postConstruct() {
        dataSourceMap.put(DataSourceEnum.DATASOURCE_DEST, dataSourceTwoDataSource());
        this.setTargetDataSources(dataSourceMap);
        this.setDefaultTargetDataSource(dataSourceTwoDataSource());
    }


    @Override
    protected Object determineCurrentLookupKey() {
        return dataSourceContextHolder.getBranchContext();
    }

    public DataSource dataSourceTwoDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(dataSourceTwoConfig.getJdbcUrl());
        dataSource.setUsername(dataSourceTwoConfig.getUsername());
        dataSource.setPassword(dataSourceTwoConfig.getPassword());
        dataSource.setDriverClassName(dataSourceTwoConfig.getDriverClassName());
        return dataSource;
    }

//    private Properties hibernateProperties() {
//        Properties properties = new Properties();
////        properties.put("spring.datasource.max-active", 1);
//        properties.put("datasource.hikari.minimum-idle", 1);
////        properties.put("spring.datasource.min-idle",1);
//        properties.put("datasource.hikari.maximum-pool-size", 1);
//        return properties;
//
//    }

    public void setDataSourceMap(Map<Object, Object> dataSourceMap) {
        this.dataSourceMap = dataSourceMap;
        this.setTargetDataSources(dataSourceMap);
        this.afterPropertiesSet();

    }
}