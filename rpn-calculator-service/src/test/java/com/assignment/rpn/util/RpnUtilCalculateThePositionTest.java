package com.assignment.rpn.util;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.Collection;

@RunWith(Parameterized.class)
public class RpnUtilCalculateThePositionTest {

    @Parameterized.Parameter(value = 0)
    public int counter;
    @Parameterized.Parameter(value = 1)
    public int expectedPosition;

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        Collection<Object[]> params = new ArrayList();
        params.add(new Object[]{1, 3});
        params.add(new Object[]{2, 5});
        params.add(new Object[]{3, 7});
        params.add(new Object[]{4, 9});
        params.add(new Object[]{5, 11});
        params.add(new Object[]{6, 13});
        params.add(new Object[]{7, 15});
        return params;
    }

    @Test
    public void testCalculateThePosition() {
        //when
        int position = RpnUtil.calculateThePosition(counter);
        //then
        Assert.assertEquals(expectedPosition, position);
    }
}
