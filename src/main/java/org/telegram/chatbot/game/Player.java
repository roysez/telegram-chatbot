package org.telegram.chatbot.game;

import lombok.Data;
import org.telegram.chatbot.utils.StringUtils;
import org.telegram.telegrambots.api.objects.User;

@Data
public class Player {

    private Integer id;
    private Integer value;
    private String username;
    private String firstName;
    private String lastName;

    public Player(User user) {
        id = user.getId();
        firstName = user.getFirstName();
        lastName = user.getLastName();
        username = user.getUserName();
    }

    public Score guessNumber(Player player) {
        int digitsMatches = StringUtils.digitsMatches(String.valueOf(value),
                String.valueOf(player.getValue()));
        int placesMatches = StringUtils.placesMatches(String.valueOf(value),
                String.valueOf(player.getValue()));
        return new Score(digitsMatches, placesMatches);
    }
}
