package org.telegram.chatbot.command;

import org.telegram.chatbot.Bot;
import org.telegram.chatbot.game.Game;
import org.telegram.chatbot.game.Round;
import org.telegram.chatbot.game.Score;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.util.Optional;

public class GuessNumberCommand extends BotCommand {

    public GuessNumberCommand(String commandName) {
        super(commandName);
    }

    @Override
    public void execute(Bot bot, Update update) throws TelegramApiException {
        Integer id = update.getMessage().getFrom().getId();
        Game game = bot.getGame(update.getMessage().getChatId());


        if (game.getFirstPlayer().getId().equals(id))
            firstPlayerStep(bot, update);
        else
            secondPlayerStep(bot, update);


        if (game.nextRound() && game.getGameIsStarted()) {
            bot.execute(new SendMessage()
                    .setChatId(update.getMessage().getChatId())
                    .setText(game.getRoundsTable()));
        }
    }

    private void secondPlayerStep(Bot bot, Update update) throws TelegramApiException {
        Game game = bot.getGame(update.getMessage().getChatId());

        Round currentRound = game.getCurrentRound();
        Optional<SendMessage> message;
        Optional<SendMessage> finalMessage = Optional.empty();

        Long chatId = update.getMessage().getChatId();
        if (currentRound.secondPlayerHasChance()) {
            Score score = game.getSecondPlayer()
                    .guessNumber(update.getMessage().getText(), game.getFirstPlayer());
            currentRound.setSPlayerScore(score);

            message = Optional.ofNullable(new SendMessage()
                    .setChatId(chatId)
                    .setText("@" + game.getSecondPlayer().getUsername()
                            + " scored: " + score));
            if (score.isGuessed()) {
                finalMessage = Optional.ofNullable(new SendMessage()
                        .setChatId(chatId)
                        .setText("@" + game.getSecondPlayer().getUsername()
                                + " угадал число! \n Игра завершена.. "));
                game.finishGame();

            }
        } else {
            message = Optional.ofNullable(new SendMessage()
                    .setChatId(chatId)
                    .setText("Ждём хода соперника"));
        }


        if (message.isPresent())
            bot.execute(message.get());
        if (finalMessage.isPresent())
            bot.execute(finalMessage.get());
    }


    private void firstPlayerStep(Bot bot, Update update) throws TelegramApiException {

        Game game = bot.getGame(update.getMessage().getChatId());

        Round currentRound = game.getCurrentRound();
        Optional<SendMessage> message;
        Optional<SendMessage> finalMessage = Optional.empty();

        Long chatId = update.getMessage().getChatId();
        if (currentRound.firstPlayerHasChance()) {
            Score score = game.getFirstPlayer()
                    .guessNumber(update.getMessage().getText(), game.getSecondPlayer());
            currentRound.setFPlayerScore(score);

            message = Optional.ofNullable(new SendMessage()
                    .setChatId(chatId)
                    .setText("@" + game.getFirstPlayer().getUsername()
                            + " scored: " + score));
            if (score.isGuessed()) {
                finalMessage = Optional.ofNullable(new SendMessage()
                        .setChatId(chatId)
                        .setText("@" + game.getFirstPlayer().getUsername()
                                + " угадал число! \n Игра завершена.. "));
                game.finishGame();

            }
        } else {
            message = Optional.ofNullable(new SendMessage()
                    .setChatId(chatId)
                    .setText("Ждём хода соперника"));
        }
        if (message.isPresent())
            bot.execute(message.get());
        if (finalMessage.isPresent())
            bot.execute(finalMessage.get());
    }
}
