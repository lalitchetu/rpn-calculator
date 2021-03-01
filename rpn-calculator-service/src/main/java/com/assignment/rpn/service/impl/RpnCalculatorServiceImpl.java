package com.assignment.rpn.service.impl;

import com.assignment.rpn.model.RpnCalculatorResult;
import com.assignment.rpn.model.RpnStack;
import com.assignment.rpn.model.annotation.HandleException;
import com.assignment.rpn.model.enums.InputCharacterSet;
import com.assignment.rpn.model.exceptions.RpnCalculatorException;
import com.assignment.rpn.operation.math.impl.SquareRootOperation;
import com.assignment.rpn.operation.math.service.RpnMathService;
import com.assignment.rpn.operation.stack.impl.ClearOperation;
import com.assignment.rpn.operation.stack.impl.InsertOperation;
import com.assignment.rpn.operation.stack.impl.UndoOperation;
import com.assignment.rpn.operation.stack.service.RpnStackService;
import com.assignment.rpn.service.RpnCalculatorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.assignment.rpn.model.enums.ResponseCodes.INVALID_INPUT_CHARACTER;
import static com.assignment.rpn.model.enums.ResponseCodes.SUCCESS;
import static com.assignment.rpn.util.MessageUtil.createInvalidInputCharacterMessage;
import static org.apache.commons.lang3.StringUtils.SPACE;

@Service
public class RpnCalculatorServiceImpl implements RpnCalculatorService {
    private static final Logger LOG = LoggerFactory.getLogger(RpnCalculatorServiceImpl.class);

    @Autowired
    private RpnMathService rpnMathService;
    @Autowired
    private RpnStackService rpnStackService;
    @Autowired
    private SquareRootOperation squareRootOperation;
    @Autowired
    private UndoOperation undoOperation;
    @Autowired
    private ClearOperation clearOperation;
    @Autowired
    private InsertOperation insertOperation;

    @Override
    @HandleException
    public RpnCalculatorResult evaluate(String expression, RpnStack rpnStack) {
        LOG.trace("In evaluate with evaluate: {} and rpnStack: {}", expression, rpnStack);
        String[] arrayOfInputTokens = expression.split(SPACE);
        for (int counter = 0; counter < arrayOfInputTokens.length; counter++) {
            String token = arrayOfInputTokens[counter];
            switch (InputCharacterSet.getValue(token)) {
                case PLUS:
                    rpnMathService.calculate(rpnStack, token, counter, (x, y) -> x + y);
                    break;
                case MINUS:
                    rpnMathService.calculate(rpnStack, token, counter, (x, y) -> y - x);
                    break;
                case MULTIPLY:
                    rpnMathService.calculate(rpnStack, token, counter, (x, y) -> x * y);
                    break;
                case DIVIDE:
                    rpnMathService.calculate(rpnStack, token, counter, (x, y) -> y / x);
                    break;
                case SQRT:
                    rpnMathService.calculate(rpnStack, token, counter, squareRootOperation);
                    break;
                case UNDO:
                    rpnStackService.perform(rpnStack, token, undoOperation);
                    break;
                case CLEAR:
                    rpnStackService.perform(rpnStack, token, clearOperation);
                    break;
                case NUMBER:
                    rpnStackService.perform(rpnStack, token, insertOperation);
                    break;
                case EMPTY_STRING:
                    break;
                case INVALID_CHARACTER:
                default:
                    LOG.error("Invalid Character in the expression");
                    throw new RpnCalculatorException(INVALID_INPUT_CHARACTER, createInvalidInputCharacterMessage(token, counter));
            }
        }
        LOG.trace("Out evaluate with rpnStack: {}", rpnStack);
        return new RpnCalculatorResult(SUCCESS);
    }
}
