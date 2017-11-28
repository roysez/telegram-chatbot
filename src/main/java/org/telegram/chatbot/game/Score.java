package org.telegram.chatbot.game;


import lombok.Getter;

@Getter
public class Score {
    private int digitsGuessed ;
    private int placesGuessed ;

    public Score(int digitsGuessed, int placesGuessed) {
        this.digitsGuessed = digitsGuessed;
        this.placesGuessed = placesGuessed;
    }

    public boolean isValid(){
        return (digitsGuessed >= 0 && digitsGuessed <=3 ) &&  (placesGuessed >= 0 && placesGuessed <=3 );
    }

    @Override
    public String toString() {
        return digitsGuessed + " : " + placesGuessed ;
    }
}
