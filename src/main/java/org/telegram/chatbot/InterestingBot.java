package org.telegram.chatbot;

import org.telegram.chatbot.command.CommandSet;
import org.telegram.chatbot.command.RigatCommand;
import org.telegram.chatbot.utils.StringUtils;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.exceptions.TelegramApiException;

public class InterestingBot extends Bot {

    private CommandSet commandSet = new CommandSet();

    public InterestingBot() {
        commandSet.addCommand(new RigatCommand("/rugatu"));
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            if (StringUtils.isCommand(update.getMessage().getText()))
                processCommand(update);
        }
    }

    private void processCommand(Update update) {
        try {

            String text = update.getMessage().getText();


            text = text.split("@")[0];

            if (commandSet.contains(text)) {
                commandSet.getCommand(text).execute(this, update);
            }


        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotUsername() {
        return "generatorRugatniBot";
    }

    @Override
    public String getBotToken() {
        return "509767652:AAFLS6SzQ6vB-eTVi18eSTJZKCbT1TqDfRQ";
    }
}
