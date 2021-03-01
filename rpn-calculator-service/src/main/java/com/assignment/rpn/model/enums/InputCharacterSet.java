package com.assignment.rpn.model.enums;

import com.assignment.rpn.util.RpnUtil;

import java.util.stream.Stream;

import static com.assignment.rpn.model.enums.OperatorType.BINARY_OPERATOR;
import static com.assignment.rpn.model.enums.OperatorType.INSERT_NUMBER_TO_STACK;
import static com.assignment.rpn.model.enums.OperatorType.NOT_AN_MATH_OPERATOR;
import static com.assignment.rpn.model.enums.OperatorType.UNARY_OPERATOR;

public enum InputCharacterSet {

    PLUS("+", BINARY_OPERATOR, true),
    MINUS("-", BINARY_OPERATOR, true),
    MULTIPLY("*", BINARY_OPERATOR, true),
    DIVIDE("/", BINARY_OPERATOR, true),

    SQRT("sqrt", UNARY_OPERATOR, true),

    UNDO("undo", NOT_AN_MATH_OPERATOR, false),
    CLEAR("clear", NOT_AN_MATH_OPERATOR, false),
    EMPTY_STRING("", NOT_AN_MATH_OPERATOR, false),

    NUMBER("This is not a operator", INSERT_NUMBER_TO_STACK, false),
    INVALID_CHARACTER("This is not a operator", NOT_AN_MATH_OPERATOR, false);

    private final String operator;
    private final OperatorType type;
    private final boolean isOperator;

    InputCharacterSet(String operator, OperatorType type, boolean isOperator) {
        this.operator = operator;
        this.type = type;
        this.isOperator = isOperator;
    }

    public static InputCharacterSet getValue(String value) {
        if (RpnUtil.isNumeric(value)) {
            return NUMBER;
        } else {
            return Stream.of(values()).filter((x) -> {
                return x.getOperator().equalsIgnoreCase(value);
            }).findFirst().orElse(
                    INVALID_CHARACTER
            );
        }
    }

    public String getOperator() {
        return operator;
    }

    public OperatorType getType() {
        return type;
    }

    public boolean isOperator() {
        return isOperator;
    }
}
