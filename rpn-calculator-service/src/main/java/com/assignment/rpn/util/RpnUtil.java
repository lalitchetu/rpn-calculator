package com.assignment.rpn.util;

public class RpnUtil {
    public static boolean isNumeric(String token) {
        try {
            Double.parseDouble(token);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }

    public static int calculateThePosition(int counter) {
        return counter * 2 + 1;
    }
}
