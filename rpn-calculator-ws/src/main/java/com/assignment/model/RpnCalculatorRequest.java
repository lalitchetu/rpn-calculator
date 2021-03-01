package com.assignment.model;

public class RpnCalculatorRequest {
    String expression;

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    @Override
    public String toString() {
        return "RpnCalculatorRequest{" +
                "expression='" + expression + '\'' +
                '}';
    }
}
