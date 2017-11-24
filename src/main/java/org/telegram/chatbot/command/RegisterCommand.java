package org.telegram.chatbot.command;

import org.telegram.chatbot.exception.FullRoomException;
import org.telegram.chatbot.exception.PlayerAlreadyRegisteredException;
import org.telegram.chatbot.game.Game;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.util.Optional;

public class RegisterCommand extends BotCommand {

    private Game game = Game.getInstance();

    public RegisterCommand(String commandName) {
        super(commandName);
    }

    public void execute(TelegramLongPollingBot bot, Update update) throws TelegramApiException {

        Optional<SendMessage> message = Optional.empty();
        Optional<SendMessage> groupMessage = Optional.empty();

        try {
            game.regPlayer(update.getMessage().getFrom());

            message =  Optional.ofNullable(new SendMessage()
                    .setChatId(String.valueOf(update.getMessage().getFrom().getId()))
                    .setText("Successfully registered"));

            groupMessage =  Optional.ofNullable(new SendMessage()
                    .setChatId(update.getMessage().getChatId())
                    .setText("Новый игрок: @" + update.getMessage().getFrom().getUserName()));



        } catch (FullRoomException e) {

            groupMessage =  Optional.ofNullable(new SendMessage()
                    .setChatId(update.getMessage().getChatId())
                    .setText("Нету места, иди делай лабки"));

        } catch (PlayerAlreadyRegisteredException e) {

            groupMessage =  Optional.ofNullable(new SendMessage()
                    .setChatId(update.getMessage().getChatId())
                    .setText("@" + update.getMessage().getFrom().getUserName() + " ты уже в игре. "));

        }

        if (message.isPresent()) {
            bot.execute(message.get());
        }
        if (groupMessage.isPresent()) {
            bot.execute(groupMessage.get());
        }

    }
}
