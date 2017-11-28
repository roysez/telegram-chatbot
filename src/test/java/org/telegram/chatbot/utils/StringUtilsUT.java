package org.telegram.chatbot.utils;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StringUtilsUT {

    @Test
    public void validatorChecker(){
        assertEquals(true, StringUtils.isValidNumberForGame("123"));
        assertEquals(false,StringUtils.isValidNumberForGame("112"));
        assertEquals(false,StringUtils.isValidNumberForGame("0112"));

    }

    @Test
    public void equalDigits(){
        assertEquals(0,StringUtils.digitsMatches("123","405"));
        assertEquals(2,StringUtils.digitsMatches("423","321"));
        assertEquals(3,StringUtils.digitsMatches("123","321"));
    }

    @Test
    public void placesMatches(){
        assertEquals(3,StringUtils.placesMatches("123","123"));
        assertEquals(2,StringUtils.placesMatches("234","134"));
        assertEquals(3,StringUtils.placesMatches("234","234"));
        assertEquals(1,StringUtils.placesMatches("234","431"));
    }
}
