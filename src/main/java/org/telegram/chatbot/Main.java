package org.telegram.chatbot;

import org.apache.log4j.Logger;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiRequestException;

public class Main {
    private static Logger logger = Logger.getLogger(Main.class);

    public static void main(String[] args) {
        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();


        try {
            telegramBotsApi.registerBot(new Bot());
            telegramBotsApi.registerBot(new InterestingBot());
            logger.info("Application started..");
        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }
    }
}
