package org.telegram.chatbot.exception;

public class PlayerAlreadyRegisteredException extends Exception {
    public PlayerAlreadyRegisteredException() {
    }

    public PlayerAlreadyRegisteredException(String message) {
        super(message);
    }
}
