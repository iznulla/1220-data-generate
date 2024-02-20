//package com.uzinfo.datagenerate.web.configuration.datasource;
//
//import jakarta.persistence.EntityManagerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.autoconfigure.domain.EntityScan;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.boot.jdbc.DataSourceBuilder;
//import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
//import org.springframework.boot.orm.jpa.hibernate.SpringImplicitNamingStrategy;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.DependsOn;
//import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.jdbc.datasource.AbstractDataSource;
//import org.springframework.jdbc.datasource.DriverManagerDataSource;
//import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
//import org.springframework.orm.jpa.JpaTransactionManager;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//import org.springframework.transaction.PlatformTransactionManager;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//
//import javax.sql.DataSource;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Properties;
//
//@Configuration
//@EnableTransactionManagement
//@DependsOn("dataSourceRouting")
//@EnableJpaRepositories(
//        entityManagerFactoryRef = "appEntityManagerFactory",
//        transactionManagerRef = "appTransactionManager",
//        basePackages = { "com.uzinfo.datagenerate.web.repository.app" }
//)
//@EntityScan(basePackages = "com.uzinfo.datagenerate.web.repository.app", basePackageClasses = Jsr310JpaConverters.class)
//public class AppDataSourceConfig {
//    @Autowired
//    private DataSourceTwoConfig dataSourceTwoConfig;
//    @Bean(name = "appDataSource")
//    public DataSource dataSource() {
//        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        dataSource.setUrl(dataSourceTwoConfig.getJdbcUrl());
//        dataSource.setUsername(dataSourceTwoConfig.getUsername());
//        dataSource.setPassword(dataSourceTwoConfig.getPassword());
//        dataSource.setDriverClassName(dataSourceTwoConfig.getDriverClassName());
//        return dataSource;
//    }
//
//    @Bean(name = "appEntityManagerFactory")
//    public LocalContainerEntityManagerFactoryBean
//    barEntityManagerFactory(
//            EntityManagerFactoryBuilder builder,
//            @Qualifier("appDataSource") DataSource dataSource
//    ) {
//        return
//                builder
//                        .dataSource(dataSource)
//                        .packages("com.uzinfo.datagenerate.web.repository.app")
//                        .persistenceUnit("app")
////                        .properties(namingStrategy)
//                        .build();
//    }
//
//    @Bean(name = "appTransactionManager")
//    public PlatformTransactionManager appTransactionManager(
//            @Qualifier("appEntityManagerFactory") EntityManagerFactory
//                    appEntityManagerFactory
//    ) {
//        return new JpaTransactionManager(appEntityManagerFactory);
//    }
//}
