package com.assignment.rpn.model.exceptions;

import com.assignment.rpn.model.enums.ResponseCodes;

public class RpnCalculatorException extends IllegalArgumentException {
    private ResponseCodes responseCodes;

    public RpnCalculatorException() {
        super();
    }

    public RpnCalculatorException(ResponseCodes responseCodes) {
        super();
        this.responseCodes = responseCodes;
    }

    public RpnCalculatorException(ResponseCodes responseCodes, String responseMessage) {
        super();
        this.responseCodes = responseCodes;
        this.responseCodes.setResponseMessage(responseMessage);
    }

    public ResponseCodes getResponseCodes() {
        return responseCodes;
    }
}
