package com.assignment.application;

import com.assignment.config.RpnCalculatorWebAppConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@Import({RpnCalculatorWebAppConfig.class})
@SpringBootApplication
public class RpnCalculatorWebApplication {
    private static final Logger LOG = LoggerFactory.getLogger(RpnCalculatorWebApplication.class);

    public static void main(String[] args) {
        LOG.info("STARTING THE RPN WEB APPLICATION");
        SpringApplication.run(RpnCalculatorWebApplication.class, args);
    }
}
