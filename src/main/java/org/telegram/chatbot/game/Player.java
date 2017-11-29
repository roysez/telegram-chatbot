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

    public Score guessNumber(String value,Player player) {
        int digitsMatches = StringUtils.digitsMatches(value,
                String.valueOf(player.getValue()));
        int placesMatches = StringUtils.placesMatches(value,
                String.valueOf(player.getValue()));
        return new Score(Integer.valueOf(value),digitsMatches, placesMatches);
    }
}
