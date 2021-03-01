package com.assignment.application;

import com.assignment.rpn.model.RpnCalculatorResult;
import com.assignment.rpn.model.RpnStack;
import com.assignment.rpn.model.enums.ResponseCodes;
import com.assignment.rpn.service.RpnCalculatorService;
import com.assignment.testconfig.TestConfig;
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
import static com.assignment.rpn.model.enums.ResponseCodes.SUCCESS;

@RunWith(Parameterized.class)
@ContextConfiguration(classes = TestConfig.class, loader = AnnotationConfigContextLoader.class)
public class RpnCalculatorServiceTest {
    private static final double DELTA = 1e-15;

    @Rule
    public final SpringMethodRule smr = new SpringMethodRule();
    @Parameterized.Parameter(value = 0)
    public String expression;
    @Parameterized.Parameter(value = 1)
    public int stackSize;
    @Parameterized.Parameter(value = 2)
    public double numberOnTopOfTheStack;
    @Parameterized.Parameter(value = 3)
    public boolean result;
    @Parameterized.Parameter(value = 4)
    public ResponseCodes responseCodes;

    @Autowired
    private RpnCalculatorService rpnCalculatorService;

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        Collection<Object[]> params = new ArrayList();
        params.add(new Object[]{"5 2", 2, 2.00d, true, SUCCESS});
        params.add(new Object[]{"2 sqrt clear 9 sqrt", 1, 3.00d, true, SUCCESS});
        params.add(new Object[]{"5 2 - 3 - ", 1, 0.00d, true, SUCCESS});
        params.add(new Object[]{"5 2 - 3 - clear", 0, 0.00d, true, SUCCESS});
        params.add(new Object[]{"5 4 3 2 undo undo * 5 * undo", 2, 5.00d, true, SUCCESS});
        params.add(new Object[]{"7 12 2 / * 4 /", 1, 10.50d, true, SUCCESS});
        params.add(new Object[]{"1 2 3 4 5 * clear 3 4 -", 1, -1.00d, true, SUCCESS});
        params.add(new Object[]{"1 2 3 4 5 * * * *", 1, 120.00d, true, SUCCESS});
        params.add(new Object[]{"1 2 3 * 5 + * * 6 5", 1, 11.00d, false, INSUFFICIENT_PARAMETERS});
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
        if (rpnStack.getNumberStack().size() > 0) {
            Assert.assertEquals(numberOnTopOfTheStack, rpnStack.getNumberStack().pop(), DELTA);
        }
        Assert.assertTrue(rpnCalculatorResult.getResponseMessage().contains(responseCodes.getResponseMessage()));
    }

    private RpnStack createRpnStack() {
        return new RpnStack(new ArrayDeque<>(), new ArrayDeque<>());
    }
}
