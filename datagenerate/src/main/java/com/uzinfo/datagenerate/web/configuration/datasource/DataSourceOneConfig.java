package com.uzinfo.datagenerate.web.configuration.datasource;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix="dataone.datasource")
@Getter
@Setter
public class DataSourceOneConfig {
    private String jdbcUrl;
    private String username;
    private String password;
    private String platform;
    private String driverClassName;
}
