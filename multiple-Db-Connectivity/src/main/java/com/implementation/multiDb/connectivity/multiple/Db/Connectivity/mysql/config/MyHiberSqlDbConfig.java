package com.implementation.multiDb.connectivity.multiple.Db.Connectivity.mysql.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "userInfoEntityManagerFactoryBean",
        transactionManagerRef = "userInfoTransactionManager",
        basePackages = {"com.implementation.multiDb.connectivity.multiple.Db.Connectivity.mysql.repositories.userInfo"}
)
public class MyHiberSqlDbConfig {

    @Autowired
    private Environment environment;

    private Logger logger = LoggerFactory.getLogger(MyHiberSqlDbConfig.class);


    @Primary
    @Bean
    @ConfigurationProperties(prefix = "spring.mysqlmyhiber-datasource")
    public DataSource userInfodataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(environment.getProperty("third.mysqlmyhiber-datasource.url"));
        dataSource.setDriverClassName(environment.getProperty("third.mysqlmyhiber-datasource.driverClassName"));
        dataSource.setUsername(environment.getProperty("third.mysqlmyhiber-datasource.username"));
        dataSource.setPassword(environment.getProperty("third.mysqlmyhiber-datasource.password"));
        logger.info("dataSource: " + dataSource.getUrl() + "\r\n");
        return dataSource;
    }


    @Primary
    @Bean("userInfoEntityManagerFactoryBean")
    public LocalContainerEntityManagerFactoryBean userInfoEntityManagerFactoryBean() {
        LocalContainerEntityManagerFactoryBean bean =
                new LocalContainerEntityManagerFactoryBean();

        bean.setDataSource(userInfodataSource());

        JpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        bean.setJpaVendorAdapter(adapter);
        Map<String, String> props = new HashMap<>();
        props.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        props.put("hibernate.show.sql", "true");
        props.put("hibernate.hbm2ddl.auto", "update");
        bean.setJpaPropertyMap(props);
        bean.setPackagesToScan("com.implementation.multiDb.connectivity.multiple.Db.Connectivity.mysql.model.userInfo");
        return bean;
    }


    @Primary
    @Bean("userInfoTransactionManager")
    public PlatformTransactionManager userInfoTransactionManager() {
        JpaTransactionManager manager = new JpaTransactionManager();
        manager.setEntityManagerFactory(userInfoEntityManagerFactoryBean().getObject());
        return manager;
    }
}
