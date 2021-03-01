package com.assignment.rpn.validation;

import com.assignment.rpn.model.RpnStack;
import com.assignment.rpn.model.exceptions.RpnCalculatorException;
import com.assignment.rpn.operation.Operation;
import com.assignment.rpn.operation.math.BinaryOperation;
import com.assignment.rpn.operation.math.UnaryOperation;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import static com.assignment.rpn.model.enums.ResponseCodes.INSUFFICIENT_PARAMETERS;
import static com.assignment.rpn.model.enums.ResponseCodes.INVALID_OPERATOR;
import static com.assignment.rpn.util.MessageUtil.createInsufficientParametersMessage;

@Aspect
@Component
public class ValidateStackAspect {
    private static final Logger LOG = LoggerFactory.getLogger(ValidateStackAspect.class);

    @Pointcut("@annotation(com.assignment.rpn.model.annotation.ValidateStack)")
    public void rpnMathServiceMethod() {
    }

    @Around("rpnMathServiceMethod()")
    public Object intercept(ProceedingJoinPoint pjp) throws Throwable {
        LOG.trace("In intercept");

        RpnStack rpnStack = (RpnStack) pjp.getArgs()[0];
        String operator = (String) pjp.getArgs()[1];
        int counter = (int) pjp.getArgs()[2];
        Operation operation = (Operation) pjp.getArgs()[3];

        validateSufficientParameterInStack(rpnStack, operator, counter, operation);

        return pjp.proceed();
    }


    public void validateSufficientParameterInStack(RpnStack rpnStack, String operator, int counter, Operation operation) {
        LOG.trace("In validateSufficientParameterInStack with RpnStack: {}, operator: {}, counter: {} and operation: {}", rpnStack, operator, counter, operation);
        if (rpnStack.getNumberStack().size() < minNumberOfParameter(operation)) {
            throw new RpnCalculatorException(INSUFFICIENT_PARAMETERS, createInsufficientParametersMessage(operator, counter));
        }
    }

    public int minNumberOfParameter(Operation operation) {
        if (operation instanceof UnaryOperation) {
            return 1;
        } else if (operation instanceof BinaryOperation) {
            return 2;
        }
        throw new RpnCalculatorException(INVALID_OPERATOR);
    }
}
