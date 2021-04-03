package ru.geekbrains.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories("ru.geekbrains.repositories")
@PropertySource(value = {"classpath:application.properties"})
public class HibernateConfig {
    private Environment environment;

    public HibernateConfig(Environment environment) {
        this.environment = environment;
    }

    @Bean(name = "entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean getEntityManagerFactoryBean(){
        LocalContainerEntityManagerFactoryBean containerEntityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        containerEntityManagerFactoryBean.setJpaVendorAdapter(getJpaVendorAdapter());
        containerEntityManagerFactoryBean.setDataSource(dataSource());
        containerEntityManagerFactoryBean.setPersistenceUnitName("myJpaPersistenceUnit");
        containerEntityManagerFactoryBean.setPackagesToScan("ru.geekbrains");
        containerEntityManagerFactoryBean.setJpaProperties(hibernateProperties());
        return containerEntityManagerFactoryBean;
    }

    @Bean
    public JpaVendorAdapter getJpaVendorAdapter(){
        JpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        return adapter;
    }

    @Bean(name = "transactionManager")
    public PlatformTransactionManager txManager(){
        JpaTransactionManager jpaTransactionManager = new JpaTransactionManager(
                getEntityManagerFactoryBean().getObject()
        );
        return jpaTransactionManager;
    }

    @Bean
    public DataSource dataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(environment.getRequiredProperty("jdbc.driver"));
        dataSource.setUrl(environment.getRequiredProperty("jdbc.url"));
        dataSource.setUsername(environment.getRequiredProperty("jdbc.username"));
        dataSource.setPassword(environment.getRequiredProperty("jdbc.password"));
        return dataSource;
    }

    private Properties hibernateProperties(){
        Properties properties = new Properties();
        properties.put("hibernate.dialect", environment.getRequiredProperty("hibernate.dialect"));
        properties.put("hibernate.show_sql", environment.getRequiredProperty("hibernate.show_sql"));
        properties.put("hibernate.format_sql", environment.getRequiredProperty("hibernate.format_sql"));
        properties.put("hibernate.hbm2ddl.auto", environment.getRequiredProperty("hibernate.hbm2ddl.auto"));
        return properties;
    }
}
