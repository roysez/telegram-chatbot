package org.telegram.chatbot;

import org.apache.log4j.Logger;
import org.telegram.chatbot.command.CommandSet;
import org.telegram.chatbot.game.Game;
import org.telegram.chatbot.utils.StringUtils;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

public class Bot extends TelegramLongPollingBot {

    private static Logger logger = Logger.getLogger(Bot.class);

    private CommandSet commandSet = new CommandSet();
    private Game game = Game.getInstance();

    public void onUpdateReceived(Update update) {

        if (update.hasMessage() && update.getMessage().hasText()) {


            String userName = update.getMessage().getFrom().getUserName();
            String firstName = update.getMessage().getFrom().getFirstName();
            String lastName = update.getMessage().getFrom().getLastName();
            logger.info("MESSAGE: UN: - " + userName + ", firsName: = " + firstName + ", lastName: = " + lastName);


            if (StringUtils.isCommand(update.getMessage().getText()))
                processCommand(update);
            else
                processMessage(update);

        }
    }

    private void processMessage(Update update) {
        String text = update.getMessage().getText();
        logger.info("MESSAGE: Text: " + text);
//        SendMessage message = new SendMessage() // Create a SendMessage object with mandatory fields
//                .setChatId(update.getMessage().getChatId())
//                .setText("slishkom mnogo pizdush, vvodi /reg");
//        try {
//            execute(message);
//        } catch (TelegramApiException e) {
//            e.printStackTrace();
//        }
    }

    private void processCommand(Update update) {
        try {

            String text = update.getMessage().getText();
            logger.info("MESSAGE: Command: " + text);


            text = text.split("@")[0];

            if (commandSet.contains(text)) {
                commandSet.getCommand(text).execute(this, update);
            }


        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public String getBotUsername() {
        return "amazingPzBot";
    }

    public String getBotToken() {
        return "491199041:AAFEU14S_sAYSePAOIPYdPsdVKWRnpY-fRQ";
    }
}
