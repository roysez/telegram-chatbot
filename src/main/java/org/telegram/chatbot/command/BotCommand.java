package org.telegram.chatbot.command;

import org.telegram.chatbot.Bot;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

public abstract class BotCommand {

    private String commandName;

    public BotCommand(String commandName) {
        this.commandName = commandName;
    }

    public String getCommandName() {
        return commandName;
    }

    public abstract void execute(Bot bot, Update update) throws TelegramApiException;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BotCommand that = (BotCommand) o;

        return commandName != null ? commandName.equals(that.commandName) : that.commandName == null;
    }

    @Override
    public int hashCode() {
        return commandName != null ? commandName.hashCode() : 0;
    }
}
