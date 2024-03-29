package org.telegram.chatbot.game;

import lombok.Data;

@Data
public class Round {
    private Score fPlayerScore;
    private Score sPlayerScore;

    public boolean isFinished() {
        return fPlayerScore != null && sPlayerScore != null;
    }

    public boolean firstPlayerHasChance() {
        return fPlayerScore == null;
    }

    public boolean secondPlayerHasChance() {
        return sPlayerScore == null;
    }
}
