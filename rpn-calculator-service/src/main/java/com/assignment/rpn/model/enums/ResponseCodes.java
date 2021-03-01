package com.assignment.rpn.model.enums;

public enum ResponseCodes {

    SUCCESS("00", true, "Success"),
    INVALID_EXPRESSION("01", false, "Invalid expression"),
    INSUFFICIENT_PARAMETERS("02", false, "Insufficient parameters"),
    INVALID_OPERATOR("03", false, "Invalid operator"),
    INVALID_INPUT_CHARACTER("04", false, "Invalid input character"),
    UNKNOWN_ERROR("99", false, "Unknown error");

    boolean isSuccess;
    String responseCodes;
    String responseMessage;

    ResponseCodes(String responseCodes, boolean isSuccess, String responseMessage) {
        this.responseCodes = responseCodes;
        this.isSuccess = isSuccess;
        this.responseMessage = responseMessage;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public String getResponseCodes() {
        return responseCodes;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public String setResponseMessage(String responseMessage) {
        return this.responseMessage = responseMessage;
    }
}
