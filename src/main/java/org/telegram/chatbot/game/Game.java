package org.telegram.chatbot.game;

import lombok.Data;
import org.apache.log4j.Logger;
import org.telegram.chatbot.exception.FullRoomException;
import org.telegram.chatbot.exception.PlayerAlreadyRegisteredException;
import org.telegram.chatbot.utils.StringUtils;
import org.telegram.telegrambots.api.objects.User;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Data
public class Game {

    private static Logger logger = Logger.getLogger(Game.class);

    private static Game instance = new Game();
    private Player firstPlayer;
    private Player secondPlayer;
    private Boolean gameIsStarted = false;

    private Game() {

    }

    public static Game getInstance() {
        return instance == null ? new Game() : instance;
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
            return true;
        } else return false;
    }


    public int generateRandomNumber() {
        int value;
        do {
            value = new Random().nextInt(1000 - 101) + 101;
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
}
