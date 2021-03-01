package com.assignment.rpn.util;

import static com.assignment.rpn.util.RpnUtil.calculateThePosition;

public class MessageUtil {

    public static String formatInsufficientParametersMessage(String operator, int position) {
        return String.format("Operator %s (position: %s) : Insufficient parameters", operator, position);
    }

    public static String formatInvalidInputCharacterMessage(String character, int position) {
        return String.format("Character '%s' (position: %s) : Invalid input character", character, position);
    }

    public static String createInsufficientParametersMessage(String token, int counter) {
        int position = calculateThePosition(counter);
        return formatInsufficientParametersMessage(token, position);
    }

    public static String createInvalidInputCharacterMessage(String token, int counter) {
        int position = calculateThePosition(counter);
        return formatInvalidInputCharacterMessage(token, position);
    }
}
