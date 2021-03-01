package com.assignment.testconfig;

import com.assignment.config.RpnCalculatorConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({RpnCalculatorConfig.class})
public class TestConfig {
}
