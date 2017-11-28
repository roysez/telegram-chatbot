package org.telegram.chatbot.game;

import org.junit.Test;
import org.telegram.chatbot.utils.StringUtils;

import static org.junit.Assert.assertEquals;

public class GameUT {

    @Test
    public void testRandomGenerator(){
        Game game = Game.getInstance();
        for (int j = 0; j < 1000; j++) {

            int i = game.generateRandomNumber();
            System.out.println(i);

            assertEquals(true, i > 100 && i < 1000 && StringUtils.isValidNumberForGame(String.valueOf(i)));
        }
    }
}
