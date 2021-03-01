package com.assignment.rpn.service.impl;

import com.assignment.rpn.model.RpnCalculatorResult;
import com.assignment.rpn.model.RpnStack;
import com.assignment.rpn.model.enums.ResponseCodes;
import com.assignment.rpn.service.RpnCalculatorService;
import com.assignment.rpn.testconfig.TestConfig;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.rules.SpringMethodRule;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;

import static com.assignment.rpn.model.enums.ResponseCodes.INSUFFICIENT_PARAMETERS;
import static com.assignment.rpn.model.enums.ResponseCodes.INVALID_INPUT_CHARACTER;
import static com.assignment.rpn.model.enums.ResponseCodes.SUCCESS;

@RunWith(Parameterized.class)
@ContextConfiguration(classes = TestConfig.class, loader = AnnotationConfigContextLoader.class)
public class RpnCalculatorVlidatorTest {
    @Rule
    public final SpringMethodRule smr = new SpringMethodRule();

    @Parameterized.Parameter(value = 0)
    public String expression;
    @Parameterized.Parameter(value = 1)
    public int stackSize;
    @Parameterized.Parameter(value = 2)
    public boolean result;
    @Parameterized.Parameter(value = 3)
    public ResponseCodes responseCodes;

    @Autowired
    private RpnCalculatorService rpnCalculatorService;

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        Collection<Object[]> params = new ArrayList();
        params.add(new Object[]{"2.0 2.0 +", 1, true, SUCCESS});
        params.add(new Object[]{"1 2 5 /", 2, true, SUCCESS});
        params.add(new Object[]{"2.0 6.0 @", 2, false, INVALID_INPUT_CHARACTER});
        params.add(new Object[]{"2.0 +", 1, false, INSUFFICIENT_PARAMETERS});
        params.add(new Object[]{"2.0 2.0 + -", 1, false, INSUFFICIENT_PARAMETERS});
        params.add(new Object[]{"2.0 2.0 + - *", 1, false, INSUFFICIENT_PARAMETERS});
        params.add(new Object[]{"*", 0, false, INSUFFICIENT_PARAMETERS});
        params.add(new Object[]{"* @ $ % ^ & *", 0, false, INSUFFICIENT_PARAMETERS});
        params.add(new Object[]{"abcd", 0, false, INVALID_INPUT_CHARACTER});
        params.add(new Object[]{"&&&&&&&&", 0, false, INVALID_INPUT_CHARACTER});
        params.add(new Object[]{"1 2 5 / * -", 1, false, INSUFFICIENT_PARAMETERS});
        params.add(new Object[]{"1 + 2 + 3", 1, false, INSUFFICIENT_PARAMETERS});
        params.add(new Object[]{"1 - 2", 1, false, INSUFFICIENT_PARAMETERS});
        params.add(new Object[]{"1 sqrt", 1, true, SUCCESS});
        return params;
    }

    @Test
    public void testEvaluate() {
        //given
        RpnStack rpnStack = createRpnStack();
        //when
        RpnCalculatorResult rpnCalculatorResult = rpnCalculatorService.evaluate(expression, rpnStack);
        //then
        Assert.assertEquals(result, rpnCalculatorResult.isSuccess());
        Assert.assertEquals(stackSize, rpnStack.getNumberStack().size());
        Assert.assertTrue(rpnCalculatorResult.getResponseMessage().contains(responseCodes.getResponseMessage()));
    }

    private RpnStack createRpnStack() {
        return new RpnStack(new ArrayDeque<>(), new ArrayDeque<>());
    }
}
