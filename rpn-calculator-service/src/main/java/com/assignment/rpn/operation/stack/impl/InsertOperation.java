package com.assignment.rpn.operation.stack.impl;

import com.assignment.rpn.model.OperationHistory;
import com.assignment.rpn.model.RpnStack;
import com.assignment.rpn.operation.stack.StackOperation;
import org.springframework.stereotype.Component;

import java.util.Arrays;

import static com.assignment.rpn.model.enums.InputCharacterSet.NUMBER;

@Component
public class InsertOperation implements StackOperation {
    @Override
    public void perform(RpnStack rpnStack, String token) {
        rpnStack.getNumberStack().push(Double.parseDouble(token));
        rpnStack.getUndoStack().push(new OperationHistory(NUMBER, Arrays.asList(Double.parseDouble(token))));
    }
}
