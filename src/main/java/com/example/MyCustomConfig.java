package com.example;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyCustomConfig {

    @Bean
    @ConditionalOnProperty(name = "my.custom.bean.enabled", havingValue = "true")
    public String ThisIsMyFirstConditionalBean() {
        return "This is a conditional bean";
    }
}
