package com.assignment.rpn.util;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.Collection;

@RunWith(Parameterized.class)
public class RpnUtilIsNumericTest {

    @Parameterized.Parameter(value = 0)
    public String token;
    @Parameterized.Parameter(value = 1)
    public boolean expectedResult;

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        Collection<Object[]> params = new ArrayList();
        params.add(new Object[]{"1", true});
        params.add(new Object[]{"11", true});
        params.add(new Object[]{"2.0f", true});
        params.add(new Object[]{"3.0d", true});
        params.add(new Object[]{"@", false});
        params.add(new Object[]{".", false});
        params.add(new Object[]{"+", false});
        params.add(new Object[]{"-", false});
        params.add(new Object[]{"/", false});
        params.add(new Object[]{"undo", false});
        params.add(new Object[]{"clear", false});
        params.add(new Object[]{"This is not a number", false});
        return params;
    }

    @Test
    public void testIsNumeric() {
        //when
        boolean result = RpnUtil.isNumeric(token);
        //then
        Assert.assertEquals(expectedResult, result);
    }
}
