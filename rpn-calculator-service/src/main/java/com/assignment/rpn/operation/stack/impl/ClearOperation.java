package com.assignment.rpn.operation.stack.impl;

import com.assignment.rpn.operation.stack.StackOperation;
import com.assignment.rpn.model.RpnStack;
import org.springframework.stereotype.Component;

@Component
public class ClearOperation implements StackOperation {
    @Override
    public void perform(RpnStack rpnStack, String token) {
        rpnStack.clearAllStack();
    }
}
