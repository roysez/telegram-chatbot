package org.telegram.chatbot.command;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

public class RigatCommand extends BotCommand {
    public RigatCommand(String commandName) {
        super(commandName);
    }

    @Override
    public void execute(TelegramLongPollingBot bot, Update update) throws TelegramApiException {
        bot.execute(new SendMessage()
                .setChatId(update.getMessage().getChatId())
                .setText("Ригаю, потім придумаю як буду ригати"));
    }
}
