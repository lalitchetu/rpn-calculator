package com.assignment.rpn.util;

import org.junit.Assert;
import org.junit.Test;

public class MessageUtilTest {

    @Test
    public void testFormatInsufficientParametersMessage() {
        //given
        String expectedMessage = "Operator + (position: 2) : Insufficient parameters";
        //when
        String message = MessageUtil.formatInsufficientParametersMessage("+", 2);
        //then
        Assert.assertTrue(message.contains(expectedMessage));
    }

    @Test
    public void testFormatInvalidInputCharacterMessage() {
        //given
        String expectedMessage = "Character '+' (position: 2) : Invalid input character";
        //when
        String message = MessageUtil.formatInvalidInputCharacterMessage("+", 2);
        //then
        Assert.assertTrue(message.contains(expectedMessage));
    }

    @Test
    public void testCreateInsufficientParametersMessage() {
        //given
        String expectedMessage = "Operator + (position: 5) : Insufficient parameters";
        //when
        String message = MessageUtil.createInsufficientParametersMessage("+", 2);
        //then
        Assert.assertTrue(message.contains(expectedMessage));
    }

    @Test
    public void testCreateInvalidInputCharacterMessage() {
        //given
        String expectedMessage = "Character '+' (position: 5) : Invalid input character";
        //when
        String message = MessageUtil.createInvalidInputCharacterMessage("+", 2);
        //then
        Assert.assertTrue(message.contains(expectedMessage));
    }
}
