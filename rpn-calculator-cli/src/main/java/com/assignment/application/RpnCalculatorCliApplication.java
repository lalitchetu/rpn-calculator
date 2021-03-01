package com.assignment.application;

import com.assignment.config.RpnCalculatorConfig;
import com.assignment.rpn.model.RpnCalculatorResult;
import com.assignment.rpn.model.RpnStack;
import com.assignment.rpn.service.RpnCalculatorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.util.Deque;
import java.util.Iterator;
import java.util.Scanner;

@Configuration
@Import({RpnCalculatorConfig.class})
@SpringBootApplication
public class RpnCalculatorCliApplication implements CommandLineRunner {

    private static final Logger LOG = LoggerFactory.getLogger(RpnCalculatorCliApplication.class);

    @Autowired
    private RpnStack rpnStack;

    @Autowired
    private RpnCalculatorService rpnCalculatorService;

    public static void main(String[] args) {
        LOG.info("STARTING THE RPN APPLICATION");
        SpringApplication.run(RpnCalculatorCliApplication.class, args);
        LOG.info("APPLICATION FINISHED");
    }

    @Override
    public void run(String... args) {
        System.out.println("THE RPN APPLICATION IS READY PLEASE ENTER AN EXPRESSION");
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String expression = scanner.nextLine();

            LOG.debug("expression: {}", expression);
            RpnCalculatorResult result = rpnCalculatorService.evaluate(expression, rpnStack);
            if (result.isSuccess()) {
                System.out.println("stack: " + getPrintString(rpnStack.getNumberStack()));
            } else {
                System.out.println("result: " + result.getResponseMessage());
                System.out.println("stack: " + getPrintString(rpnStack.getNumberStack()));
            }
        }
    }

    private String getPrintString(Deque<Double> stack) {
        StringBuilder builder = new StringBuilder();

        for (Iterator<Double> itr = stack.descendingIterator(); itr.hasNext(); ) {
            builder.append(itr.next());
            builder.append(" ");
        }
        return builder.toString();
    }
}
