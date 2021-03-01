package com.assignment.config;

import com.assignment.rpn.config.RpnCalculatorServiceConfig;
import com.assignment.rpn.model.RpnStack;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Scope;

import java.util.ArrayDeque;

@Configuration
@Import({RpnCalculatorServiceConfig.class})
public class RpnCalculatorConfig {

    @Bean
    @Scope("singleton")
    public RpnStack createRpnStack() {
        //On cli application startup, will create empty stack
        return new RpnStack(new ArrayDeque<>(), new ArrayDeque<>());
    }
}
