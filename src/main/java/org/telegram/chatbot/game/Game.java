package org.telegram.chatbot.game;

import lombok.Data;
import org.apache.log4j.Logger;
import org.telegram.chatbot.exception.FullRoomException;
import org.telegram.chatbot.exception.PlayerAlreadyRegisteredException;
import org.telegram.chatbot.utils.StringUtils;
import org.telegram.telegrambots.api.objects.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Data
public class Game {

    public static int DIGITS_COUNT = 3;
    private static Logger logger = Logger.getLogger(Game.class);
    private static Game instance = new Game();
    private Player firstPlayer;
    private Player secondPlayer;
    private Boolean gameIsStarted = false;
    private Round currentRound;
    private List<Round> rounds = new ArrayList<>();

    private Game() {

    }

    public static Game getInstance() {
        return instance == null ? new Game() : instance;
    }

    public static int switchMode() {
        if (DIGITS_COUNT == 3)
            DIGITS_COUNT += 1;
        else
            DIGITS_COUNT -= 1;
        return DIGITS_COUNT;
    }

    public void finishGame() {
        firstPlayer = null;
        secondPlayer = null;
        gameIsStarted = false;
        currentRound = null;
        rounds = new ArrayList<>();
    }

    public List<Player> getPlayers() {
        return Stream.of(firstPlayer, secondPlayer).collect(Collectors.toList());
    }

    public void regPlayer(User user) throws FullRoomException, PlayerAlreadyRegisteredException {
        if (isAlreadyRegistered(user.getId()))
            throw new PlayerAlreadyRegisteredException();
        else if (isFreeSpace()) {
            if (firstPlayer == null)
                firstPlayer = new Player(user);
            else
                secondPlayer = new Player(user);
        } else
            throw new FullRoomException("Full room");
    }

    public boolean isPlayer(Integer id) {
        return firstPlayer.getId().equals(id) || secondPlayer.getId().equals(id);
    }

    public boolean startGame() {
        if (!isFreeSpace() && !gameIsStarted) {
            firstPlayer.setValue(generateRandomNumber());
            logger.info("First player value: " + firstPlayer.getValue());
            secondPlayer.setValue(generateRandomNumber());
            logger.info("Second player value: " + secondPlayer.getValue());
            gameIsStarted = true;
            currentRound = new Round();
            return true;
        } else return false;
    }

    public int generateRandomNumber() {
        int value;
        do {
            if (DIGITS_COUNT == 3) {
                value = new Random().nextInt(1000 - 101) + 101;
            } else {
                value = new Random().nextInt(10000 - 1001) + 1001;
            }
        } while (!StringUtils.isValidNumberForGame(String.valueOf(value)));
        return value;
    }

    private boolean isAlreadyRegistered(Integer id) {
        return (firstPlayer != null && firstPlayer.getId().equals(id))
                || (secondPlayer != null && secondPlayer.getId().equals(id));
    }

    private boolean isFreeSpace() {
        return firstPlayer == null || secondPlayer == null;
    }


    public boolean nextRound() {
        if (currentRound.isFinished()) {
            rounds.add(currentRound);
            currentRound = new Round();
            return true;
        } else
            return false;
    }

    public String getRoundsTable() {
        StringBuilder response = new StringBuilder();
        response.append("Все раунды: \n");
        for (Round round : rounds) {
            Score fPlayerScore = round.getFPlayerScore();
            Score sPlayerScore = round.getSPlayerScore();
            response.append(fPlayerScore.getValue())
                    .append(" | ")
                    .append(fPlayerScore.toString())
                    .append(" | ")
                    .append(sPlayerScore.toString())
                    .append(" | ")
                    .append(sPlayerScore.getValue())
                    .append("\n");
        }
        return response.toString();
    }
}
