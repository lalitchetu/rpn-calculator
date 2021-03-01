package com.assignment.rpn.operation.math.service;

import com.assignment.rpn.model.RpnStack;
import com.assignment.rpn.model.exceptions.RpnCalculatorException;
import com.assignment.rpn.operation.math.UnaryOperation;
import com.assignment.rpn.testconfig.TestConfig;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.rules.SpringMethodRule;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@RunWith(Parameterized.class)
@ContextConfiguration(classes = TestConfig.class, loader = AnnotationConfigContextLoader.class)
public class RpnMathServiceUnaryOperationTest extends AbstractRpnMathServiceTest {
    private static final double DELTA = 1e-15;
    @Rule
    public final SpringMethodRule smr = new SpringMethodRule();
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Parameterized.Parameter(value = 0)
    public List<Double> elementsInStack;
    @Parameterized.Parameter(value = 1)
    public String operator;
    @Parameterized.Parameter(value = 2)
    public int counter;
    @Parameterized.Parameter(value = 3)
    public UnaryOperation unaryOperation;

    @Parameterized.Parameter(value = 4)
    public int stackSize;
    @Parameterized.Parameter(value = 5)
    public double result;
    @Parameterized.Parameter(value = 6)
    public Class<? extends Exception> expectedException;

    @Autowired
    private RpnMathService rpnMathService;

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        Collection<Object[]> params = new ArrayList();
        // positive test
        params.add(new Object[]{Arrays.asList(4.0d, 9.0d), "sqrt", 1, squareRootOperation(), 2, 2.0d, null});

        // negative test
        params.add(new Object[]{Arrays.asList(), "sqrt", 1, squareRootOperation(), 1, 2.0d, RpnCalculatorException.class});

        return params;
    }

    @Test
    public void testUnaryOperation() {
        //given
        RpnStack rpnStack = createRpnStack(elementsInStack);
        //setup expected exception
        if (expectedException != null) {
            thrown.expect(expectedException);
        }
        //when
        rpnMathService.calculate(rpnStack, operator, counter, unaryOperation);
        //then
        Assert.assertEquals(stackSize, rpnStack.getNumberStack().size());
        Assert.assertEquals(result, rpnStack.getNumberStack().pop(), DELTA);
    }

}
