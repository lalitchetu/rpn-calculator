package com.assignment.config;

import com.assignment.rpn.config.RpnCalculatorServiceConfig;
import com.assignment.rpn.model.RpnStack;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.context.annotation.SessionScope;

import java.util.ArrayDeque;

@Configuration
@Import({RpnCalculatorServiceConfig.class})
@ComponentScan(basePackages = {
        "com.assignment.ws",
        "com.assignment.service",
        "com.assignment.map"})
public class RpnCalculatorWebAppConfig {

    @Bean
    @SessionScope
    public RpnStack createRpnStack() {
        //On web application startup, will create empty stack
        return new RpnStack(new ArrayDeque<>(), new ArrayDeque<>());
    }

}
