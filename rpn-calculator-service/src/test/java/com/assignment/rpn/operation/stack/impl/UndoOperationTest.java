package com.assignment.rpn.operation.stack.impl;

import com.assignment.rpn.model.OperationHistory;
import com.assignment.rpn.model.RpnStack;
import com.assignment.rpn.model.enums.InputCharacterSet;
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
public class UndoOperationTest {
    private static final double DELTA = 1e-15;

    @Autowired
    private UndoOperation undoOperation;

    @Test
    public void testUndoOperation() {
        //given
        RpnStack rpnStack = createRpnStack();
        //when
        undoOperation.perform(rpnStack, "undo");
        //then
        Assert.assertEquals(3, rpnStack.getNumberStack().size());
        Assert.assertEquals(2d, rpnStack.getNumberStack().pop(), DELTA);
        Assert.assertEquals(0, rpnStack.getUndoStack().size());
    }

    private RpnStack createRpnStack() {
        return new RpnStack(new ArrayDeque<>(Arrays.asList(10d, 3d)), new ArrayDeque<>(Arrays.asList(new OperationHistory(InputCharacterSet.PLUS, Arrays.asList(1d, 2d), 3d))));
    }
}
