package org.telegram.chatbot.game;

import org.telegram.chatbot.exception.FullRoomException;
import org.telegram.chatbot.exception.PlayerAlreadyRegisteredException;
import org.telegram.telegrambots.api.objects.User;

public class Game {

    private static Game instance = new Game();
    private Player firstPlayer;
    private Player secondPlayer;

    private Game() {

    }

    public static Game getInstance() {
        return instance == null ? new Game() : instance;
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

    private boolean isAlreadyRegistered(Integer id) {
        return (firstPlayer != null && firstPlayer.getId().equals(id))
                || (secondPlayer != null && secondPlayer.getId().equals(id));
    }

    private boolean isFreeSpace() {
        return firstPlayer == null || secondPlayer == null;
    }
}
