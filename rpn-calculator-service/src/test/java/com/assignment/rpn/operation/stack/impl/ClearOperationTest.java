package com.assignment.rpn.operation.stack.impl;

import com.assignment.rpn.model.OperationHistory;
import com.assignment.rpn.model.RpnStack;
import com.assignment.rpn.testconfig.TestConfig;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.util.ArrayDeque;
import java.util.Arrays;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TestConfig.class, loader = AnnotationConfigContextLoader.class)
public class ClearOperationTest {

    @Autowired
    private ClearOperation clearOperation;

    @Test
    public void testClearOperation() {
        //given
        RpnStack rpnStack = createRpnStack();
        //when
        clearOperation.perform(rpnStack, "clear");
        //then
        Assert.assertEquals(0, rpnStack.getNumberStack().size());
        Assert.assertEquals(0, rpnStack.getUndoStack().size());
    }

    private RpnStack createRpnStack() {
        return new RpnStack(new ArrayDeque<>(Arrays.asList(10d, 20d)), new ArrayDeque<>(Arrays.asList(new OperationHistory())));
    }
}
