package com.assignment.rpn.operation.stack.impl;

import com.assignment.rpn.model.OperationHistory;
import com.assignment.rpn.model.RpnStack;
import com.assignment.rpn.operation.stack.StackOperation;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.assignment.rpn.model.enums.InputCharacterSet.NUMBER;

@Component
public class UndoOperation implements StackOperation {
    @Override
    public void perform(RpnStack rpnStack, String token) {
        if (rpnStack.getUndoStack().size() > 0) {
            rpnStack.getNumberStack().pop();

            OperationHistory undoOperationHistory = rpnStack.getUndoStack().pop();
            if (undoOperationHistory.getInputCharacterSet() != NUMBER) {
                List<Double> listOfOperands = undoOperationHistory.getListOfOperands();
                for (Double operand : listOfOperands) {
                    rpnStack.getNumberStack().push(operand);
                }
            }
        }
    }
}
