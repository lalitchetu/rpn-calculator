package com.assignment.rpn.operation.math.service;

import com.assignment.rpn.model.RpnStack;
import com.assignment.rpn.operation.math.BinaryOperation;
import com.assignment.rpn.operation.math.UnaryOperation;
import com.assignment.rpn.operation.math.impl.SquareRootOperation;

import java.util.ArrayDeque;
import java.util.List;

public class AbstractRpnMathServiceTest {

    public static BinaryOperation additionOperation() {
        return (x, y) -> x + y;
    }

    public static BinaryOperation subtractionOperation() {
        return (x, y) -> y - x;
    }

    public static BinaryOperation multiplicationOperation() {
        return (x, y) -> x * y;
    }

    public static BinaryOperation divisionOperation() {
        return (x, y) -> y / x;
    }


    public static UnaryOperation squareRootOperation() {
        return new SquareRootOperation();
    }

    public RpnStack createRpnStack(List<Double> elementsInStack) {
        return new RpnStack(new ArrayDeque<>(elementsInStack), new ArrayDeque<>());
    }
}
