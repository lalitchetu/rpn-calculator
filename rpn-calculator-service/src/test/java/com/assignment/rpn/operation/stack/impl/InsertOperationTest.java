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
public class InsertOperationTest {

    @Autowired
    private InsertOperation insertOperation;

    @Test
    public void testInsertOperation() {
        //given
        RpnStack rpnStack = createRpnStack();
        //when
        insertOperation.perform(rpnStack, "2");
        //then
        Assert.assertEquals(3, rpnStack.getNumberStack().size());
        Assert.assertEquals(2, rpnStack.getUndoStack().size());
    }

    private RpnStack createRpnStack() {
        return new RpnStack(new ArrayDeque<>(Arrays.asList(10d, 20d)), new ArrayDeque<>(Arrays.asList(new OperationHistory())));
    }
}
