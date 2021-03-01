package com.assignment.rpn.service;

import com.assignment.rpn.model.RpnCalculatorResult;
import com.assignment.rpn.model.RpnStack;

public interface RpnCalculatorService {
    RpnCalculatorResult evaluate(String expression, RpnStack rpnStack);
}
