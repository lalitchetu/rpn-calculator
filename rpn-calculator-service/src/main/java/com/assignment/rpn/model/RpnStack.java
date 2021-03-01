package com.assignment.rpn.model;

import java.util.Deque;

public class RpnStack {

    private Deque<Double> numberStack;
    private Deque<OperationHistory> undoStack;

    public RpnStack() {
    }

    public RpnStack(Deque<Double> numberStack, Deque<OperationHistory> undoStack) {
        this.numberStack = numberStack;
        this.undoStack = undoStack;
    }

    public Deque<Double> getNumberStack() {
        return numberStack;
    }

    public Deque<OperationHistory> getUndoStack() {
        return undoStack;
    }

    public void clearAllStack() {
        this.numberStack.clear();
        this.undoStack.clear();
    }

    @Override
    public String toString() {
        return "RpnStack{" +
                "numberStack=" + numberStack +
                ", undoStack=" + undoStack +
                '}';
    }
}
