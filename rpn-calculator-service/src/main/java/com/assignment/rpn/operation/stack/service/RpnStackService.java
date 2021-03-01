package com.assignment.rpn.operation.stack.service;

import com.assignment.rpn.model.RpnStack;
import com.assignment.rpn.operation.stack.StackOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class RpnStackService {
    private static final Logger LOG = LoggerFactory.getLogger(RpnStackService.class);
    public void perform(RpnStack rpnStack, String token, StackOperation stackOperation) {
        LOG.trace("In perform() with rpnStack: {}, token: {} and stackOperation: {}", rpnStack, token, stackOperation);
        stackOperation.perform(rpnStack, token);
    }
}
