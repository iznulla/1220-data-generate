package com.uzinfo.datagenerate.web.configuration.datasource;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = {"com.uzinfo.datagenerate.web.repository.base"}
)
@EntityScan(basePackages = "com.uzinfo.datagenerate.web.repository.base", basePackageClasses = Jsr310JpaConverters.class)
public class BaseDataSourceConfig {
    @Autowired
    private DataSourceOneConfig dataSourceOneConfig;

    @Primary
    @Bean(name = "dataSource")
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(dataSourceOneConfig.getJdbcUrl());
        dataSource.setUsername(dataSourceOneConfig.getUsername());
        dataSource.setPassword(dataSourceOneConfig.getPassword());
        dataSource.setDriverClassName(dataSourceOneConfig.getDriverClassName());
        return dataSource;
    }

    @Primary
    @Bean(name = "entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean baseEntityManagerFactory() {
        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setDataSource(dataSource());
        factoryBean.setPackagesToScan("com.uzinfo.datagenerate.web.entity");
        HibernateJpaVendorAdapter vendorAdapter
                = new HibernateJpaVendorAdapter();
        factoryBean.setJpaVendorAdapter(vendorAdapter);
        factoryBean.setJpaProperties(hibernateProperties());
        return factoryBean;
    }

    @Primary
    @Bean(name = "transactionManager")
    public PlatformTransactionManager transactionManager(
            @Qualifier("entityManagerFactory") EntityManagerFactory
                    entityManagerFactory
    ) {
        return new JpaTransactionManager(entityManagerFactory);
    }

    private Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.show_sql", true);
        properties.put("hibernate.format_sql", true);
        properties.put("hibernate.hbm2ddl.auto", "create-drop");
        properties.put("spring.jpa.generate-ddl", true);
        return properties;
    }
}
