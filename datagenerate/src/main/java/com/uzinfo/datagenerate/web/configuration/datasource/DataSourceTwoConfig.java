package com.uzinfo.datagenerate.web.configuration.datasource;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@ConfigurationProperties(prefix = "datatwo.datasource")
@Getter
@Setter
public class DataSourceTwoConfig {
    private String jdbcUrl;
    private String username;
    private String password;
    private String platform;
    private String driverClassName;
    private String maximumPoolSize;


}
