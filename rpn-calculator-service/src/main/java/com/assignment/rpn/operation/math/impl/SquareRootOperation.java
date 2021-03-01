package com.assignment.rpn.operation.math.impl;

import com.assignment.rpn.operation.math.UnaryOperation;
import org.springframework.stereotype.Component;

@Component
public class SquareRootOperation implements UnaryOperation {
    @Override
    public Double calculate(Double x) {
        return Math.sqrt(x);
    }
}
