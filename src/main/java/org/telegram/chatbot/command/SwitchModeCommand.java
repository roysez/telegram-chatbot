package org.telegram.chatbot.command;

import org.telegram.chatbot.game.Game;
import org.telegram.chatbot.utils.StringUtils;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

public class SwitchModeCommand extends BotCommand {

    public SwitchModeCommand(String commandName) {
        super(commandName);
    }

    @Override
    public void execute(TelegramLongPollingBot bot, Update update) throws TelegramApiException {
        Game instance = Game.getInstance();
        SendMessage message;
        if (!instance.getGameIsStarted()) {
            int mode = StringUtils.switchMode();
            message = new SendMessage()
                    .setChatId(update.getMessage().getChatId())
                    .setText("Теперь количество цифр: " + mode);
        } else {
            message = new SendMessage()
                    .setChatId(update.getMessage().getChatId())
                    .setText("Сменить режим можно только после игры.. ");
        }
        bot.execute(message);
    }
}
