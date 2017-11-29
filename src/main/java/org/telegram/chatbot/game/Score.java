package org.telegram.chatbot.game;


import lombok.Getter;

@Getter
public class Score {
    private int value;
    private int digitsGuessed;
    private int placesGuessed;
    private boolean guessed;

    public Score(int value,int digitsGuessed, int placesGuessed) {
        this.value = value;
        this.digitsGuessed = digitsGuessed;
        this.placesGuessed = placesGuessed;
        guessed = digitsGuessed == 3 && digitsGuessed == placesGuessed;
    }

    public boolean isValid() {
        return (digitsGuessed >= 0 && digitsGuessed <= 3)
                && (placesGuessed >= 0 && placesGuessed <= 3);
    }

    @Override
    public String toString() {
        return digitsGuessed + " : " + placesGuessed;
    }
}
