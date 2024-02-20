package com.uzinfo.datagenerate.web.configuration.datasource;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import lombok.RequiredArgsConstructor;

import java.util.Objects;

@Configuration
@EnableJpaRepositories(
        basePackages = "com.uzinfo.datagenerate.web.repository.app",
        transactionManagerRef = "appTransactionManager")
@EnableTransactionManagement
@RequiredArgsConstructor
@DependsOn("dataSourceRouting")
public class DataSourceConfig {

    private final DataSourceRouting dataSourceRouting;
    @Bean(name = "appDataSource")
    public DataSource dataSource() {
        return dataSourceRouting.dataSourceTwoDataSource();
    }

    @Bean(name = "appManagerFactory")
    public LocalContainerEntityManagerFactoryBean baseEntityManagerFactory() {
        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setDataSource(dataSource());
        factoryBean.setPackagesToScan("com.uzinfo.datagenerate.web.repository.app");
        factoryBean.getPersistenceUnitName();
        HibernateJpaVendorAdapter vendorAdapter
                = new HibernateJpaVendorAdapter();
        factoryBean.setJpaVendorAdapter(vendorAdapter);
        return factoryBean;
    }



    @Bean(name = "appTransactionManager")
    public JpaTransactionManager transactionManager(
            @Autowired @Qualifier("appManagerFactory") LocalContainerEntityManagerFactoryBean entityManagerFactoryBean) {
        return new JpaTransactionManager(Objects.requireNonNull(entityManagerFactoryBean.getObject()));
    }

}