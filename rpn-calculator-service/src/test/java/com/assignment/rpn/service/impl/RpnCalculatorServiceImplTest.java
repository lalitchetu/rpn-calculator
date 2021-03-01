package com.assignment.rpn.service.impl;

import com.assignment.rpn.model.RpnCalculatorResult;
import com.assignment.rpn.model.RpnStack;
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

@RunWith(Parameterized.class)
@ContextConfiguration(classes = TestConfig.class, loader = AnnotationConfigContextLoader.class)
public class RpnCalculatorServiceImplTest {

    private static final double DELTA = 1e-15;
    @Rule
    public final SpringMethodRule smr = new SpringMethodRule();
    @Parameterized.Parameter(value = 0)
    public String expression;
    @Parameterized.Parameter(value = 1)
    public int stackSize;
    @Parameterized.Parameter(value = 2)
    public double result;
    @Autowired
    private RpnCalculatorService rpnCalculatorService;

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        Collection<Object[]> params = new ArrayList();
        params.add(new Object[]{"2.0 2.0 +", 1, 4.0000000000000000d});
        params.add(new Object[]{"2.0 6.0 +", 1, 8.0000000000000000d});
        params.add(new Object[]{"2 6 +    ", 1, 8.0000000000000000d});
        params.add(new Object[]{"2.0 6.0 -", 1, -4.0000000000000000d});
        params.add(new Object[]{"2.0 6.0 *", 1, 12.0000000000000000d});
        params.add(new Object[]{"2.0 6.0 /", 1, 0.333333333333333d});
        params.add(new Object[]{"2.0 6.0", 2, 6.0000000000000000d});
        return params;
    }

    @Test
    public void testEvaluate() {
        //given
        RpnStack rpnStack = createRpnStack();
        //when
        RpnCalculatorResult rpnCalculatorResult = rpnCalculatorService.evaluate(expression, rpnStack);
        //then
        Assert.assertTrue(rpnCalculatorResult.isSuccess());
        Assert.assertEquals(stackSize, rpnStack.getNumberStack().size());
        Assert.assertEquals(result, rpnStack.getNumberStack().pop(), DELTA);
    }

    private RpnStack createRpnStack() {
        return new RpnStack(new ArrayDeque<>(), new ArrayDeque<>());
    }
}
