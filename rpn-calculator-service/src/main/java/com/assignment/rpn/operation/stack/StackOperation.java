package com.assignment.rpn.operation.stack;

import com.assignment.rpn.operation.Operation;
import com.assignment.rpn.model.RpnStack;

public interface StackOperation extends Operation {
    void perform(RpnStack rpnStack, String token);
}
