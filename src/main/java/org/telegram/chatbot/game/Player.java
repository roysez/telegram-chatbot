package org.telegram.chatbot.game;

import org.telegram.telegrambots.api.objects.User;

public class Player {
    private Integer id;

    public Player() {
    }

    public Player(User user){
        id = user.getId();
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
}
