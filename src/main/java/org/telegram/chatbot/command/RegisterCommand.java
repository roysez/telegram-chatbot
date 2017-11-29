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
                    .setText("Ты в игре.. жди свое число"));

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

        if(game.startGame()){
            game.getPlayers().forEach(player -> {
                try {
                    bot.execute(new SendMessage()
                            .setChatId(String.valueOf(player.getId()))
                            .setText("Твое рандомное число: " + player.getValue()));


                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            });
            bot.execute(new SendMessage()
                    .setChatId(update.getMessage().getChatId())
                    .setText("Игра началась, все игроки получили свои числа :\n " +
                    "@" + game.getFirstPlayer().getUsername() + " VS " +
                     "@" +game.getSecondPlayer().getUsername()));
        }


    }
}
