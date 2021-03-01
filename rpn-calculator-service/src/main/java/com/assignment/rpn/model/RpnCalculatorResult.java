package com.assignment.rpn.model;

import com.assignment.rpn.model.enums.ResponseCodes;

public class RpnCalculatorResult {

    boolean success;
    String responseMessage;

    public RpnCalculatorResult() {
    }

    public RpnCalculatorResult(ResponseCodes responseCodes) {
        this.success = responseCodes.isSuccess();
        this.responseMessage = responseCodes.getResponseMessage();
    }

    public boolean isSuccess() {
        return success;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

}
