package com.assignment.rpn.operation.math.impl;

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

import java.util.ArrayList;
import java.util.Collection;

@RunWith(Parameterized.class)
@ContextConfiguration(classes = TestConfig.class, loader = AnnotationConfigContextLoader.class)
public class SquareRootOperationTest {
    private static final double DELTA = 1e-15;

    @Rule
    public final SpringMethodRule smr = new SpringMethodRule();

    @Parameterized.Parameter(value = 0)
    public Double input;
    @Parameterized.Parameter(value = 1)
    public Double expectedResult;

    @Autowired
    private SquareRootOperation squareRootOperation;

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        Collection<Object[]> params = new ArrayList();
        params.add(new Object[]{1d, 1d});
        params.add(new Object[]{2d, 1.4142135623730951d});
        params.add(new Object[]{3d, 1.7320508075688772d});
        params.add(new Object[]{4d, 2d});
        params.add(new Object[]{5d, 2.23606797749979d});
        params.add(new Object[]{6d, 2.449489742783178d});
        params.add(new Object[]{7d, 2.6457513110645907d});
        params.add(new Object[]{8d, 2.8284271247461903d});
        params.add(new Object[]{9d, 3d});
        params.add(new Object[]{10d, 3.1622776601683795d});
        params.add(new Object[]{20d, 4.47213595499958d});
        params.add(new Object[]{100d, 10d});
        return params;
    }

    @Test
    public void testIsNumeric() {
        //when
        Double result = squareRootOperation.calculate(input);
        //then
        Assert.assertEquals(expectedResult, result, DELTA);
    }
}
