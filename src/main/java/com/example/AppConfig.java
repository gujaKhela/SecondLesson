package com.example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;



    @Configuration
    @EnableJpaRepositories(basePackages = "com.example")
    @ComponentScan(basePackages = "com.example") // Tells Spring to scan the 'org.example' package for beans
    public class AppConfig {
        private static final String DRIVER_CLASS_NAME= "org.postgresql.Driver";
        private static final String DATABASE_URL = "jdbc:postgresql://localhost:5432/my_ticket_service_db";
        private static final String DATABASE_USERNAME= "postgres";
        private static final String DATABASE_PASSWORD= "1996";

        private static final String PACKAGE_TO_SCAN = "com.example";

        @Bean
        public DataSource dataSource() {
            DriverManagerDataSource dataSource = new DriverManagerDataSource();
            dataSource.setDriverClassName(DRIVER_CLASS_NAME);
            dataSource.setUrl(DATABASE_URL);
            dataSource.setUsername(DATABASE_USERNAME);
            dataSource.setPassword(DATABASE_PASSWORD);
            return dataSource;
        }

        @Bean
        public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
            LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
            em.setDataSource(dataSource());
            em.setPackagesToScan(PACKAGE_TO_SCAN);
            em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
            return em;
        }

        @Bean
        public PlatformTransactionManager transactionManager() {
            JpaTransactionManager transactionManager = new JpaTransactionManager();
            transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
            return transactionManager;
        }
    }

