package org.telegram.chatbot.command;

import org.telegram.chatbot.Bot;
import org.telegram.chatbot.utils.Dictionary;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.exceptions.TelegramApiException;

public class RigatCommand extends BotCommand {
    public RigatCommand(String commandName) {
        super(commandName);
    }

    @Override
    public void execute(Bot bot, Update update) throws TelegramApiException {

        DeleteMessage deleteMessage = new DeleteMessage(update.getMessage().getChatId(), update.getMessage().getMessageId());
        deleteMessage.validate();


        bot.execute(new SendMessage()
                .setChatId(update.getMessage().getChatId())
                .setText(Dictionary.randomPhrase()));
        bot.execute(deleteMessage);
    }
}
