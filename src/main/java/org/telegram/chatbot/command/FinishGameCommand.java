package org.telegram.chatbot.command;

import org.telegram.chatbot.Bot;
import org.telegram.chatbot.game.Game;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

public class FinishGameCommand extends BotCommand {


    public FinishGameCommand(String commandName) {
        super(commandName);
    }

    @Override
    public void execute(Bot bot, Update update) throws TelegramApiException {
        if (update.getMessage().getFrom().getUserName().equals("roysez")) {
            Game game = bot.getGame(update.getMessage().getChatId());

            if (game.getGameIsStarted()) {
                game.finishGame();
                bot.execute(new SendMessage()
                        .setChatId(update.getMessage().getChatId())
                        .setText("Играв завершена"));
            }
        } else {
            bot.execute(new SendMessage()
                    .setChatId(update.getMessage().getChatId())
                    .setText("Только бог может это сделать.. "));
        }
    }
}
