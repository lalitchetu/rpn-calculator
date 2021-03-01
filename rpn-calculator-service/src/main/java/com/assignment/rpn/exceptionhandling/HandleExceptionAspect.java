package com.assignment.rpn.exceptionhandling;

import com.assignment.rpn.model.RpnCalculatorResult;
import com.assignment.rpn.model.exceptions.RpnCalculatorException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import static com.assignment.rpn.model.enums.ResponseCodes.INVALID_EXPRESSION;
import static com.assignment.rpn.model.enums.ResponseCodes.UNKNOWN_ERROR;

@Aspect
@Component
public class HandleExceptionAspect {
    private static final Logger LOG = LoggerFactory.getLogger(HandleExceptionAspect.class);

    @Pointcut("@annotation(com.assignment.rpn.model.annotation.HandleException)")
    public void handleExceptionForEvaluateMethod() {
    }

    @Around("handleExceptionForEvaluateMethod()")
    public Object intercept(ProceedingJoinPoint pjp) throws Throwable {
        LOG.trace("In intercept");
        try {
            return pjp.proceed();
        } catch (RpnCalculatorException ex) {
            LOG.error("Error evaluating expression: ", ex);
            if (ex.getResponseCodes() != null) {
                return new RpnCalculatorResult(ex.getResponseCodes());
            }
            return new RpnCalculatorResult(INVALID_EXPRESSION);
        } catch (IllegalArgumentException ex) {
            LOG.error("Error evaluating expression: ", ex);
            return new RpnCalculatorResult(INVALID_EXPRESSION);
        } catch (Exception ex) {
            LOG.error("Error evaluating expression: ", ex);
            return new RpnCalculatorResult(UNKNOWN_ERROR);
        }
    }
}
