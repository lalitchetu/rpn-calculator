package com.assignment.rpn.testconfig;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan(basePackages = {
        "com.assignment.rpn.operation.math",
        "com.assignment.rpn.operation.stack",
        "com.assignment.rpn.service",
        "com.assignment.rpn.validation",
        "com.assignment.rpn.exceptionhandling"})
public class TestConfig {
}
