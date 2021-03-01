package com.assignment.rpn.operation.math.service;

import com.assignment.rpn.model.OperationHistory;
import com.assignment.rpn.model.RpnStack;
import com.assignment.rpn.model.annotation.ValidateStack;
import com.assignment.rpn.model.enums.InputCharacterSet;
import com.assignment.rpn.operation.math.BinaryOperation;
import com.assignment.rpn.operation.math.UnaryOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class RpnMathService {
    private static final Logger LOG = LoggerFactory.getLogger(RpnMathService.class);

    @ValidateStack
    public void calculate(RpnStack rpnStack, String operator, int counter, UnaryOperation unaryOperation) {
        LOG.trace("In calculate() with rpnStack: {}, operator: {}, counter: {} and unaryOperation: {}", rpnStack, operator, counter, unaryOperation);

        Double var1 = rpnStack.getNumberStack().pop();
        Double result = unaryOperation.calculate(var1);

        rpnStack.getNumberStack().push(result);
        rpnStack.getUndoStack().push(new OperationHistory(InputCharacterSet.getValue(operator), Arrays.asList(var1), result));
    }

    @ValidateStack
    public void calculate(RpnStack rpnStack, String operator, int counter, BinaryOperation binaryOperation) {
        LOG.trace("In calculate() with rpnStack: {}, operator: {}, counter: {} and unaryOperation: {}", rpnStack, operator, counter, binaryOperation);

        Double var1 = rpnStack.getNumberStack().pop();
        Double var2 = rpnStack.getNumberStack().pop();

        Double result = binaryOperation.calculate(var1, var2);

        rpnStack.getNumberStack().push(result);
        rpnStack.getUndoStack().push(new OperationHistory(InputCharacterSet.getValue(operator), Arrays.asList(var2, var1), result)); //reverse-order
    }
}
