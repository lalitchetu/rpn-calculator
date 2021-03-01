package com.assignment.model;

import java.util.List;

public class RpnCalculatorResponse {

    boolean success;
    String responseMessage;
    List<Double> stackElements;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public List<Double> getStackElements() {
        return stackElements;
    }

    public void setStackElements(List<Double> stackElements) {
        this.stackElements = stackElements;
    }

    @Override
    public String toString() {
        return "RpnCalculatorResponse{" +
                "success=" + success +
                ", responseMessage='" + responseMessage + '\'' +
                ", stackElements=" + stackElements +
                '}';
    }
}
