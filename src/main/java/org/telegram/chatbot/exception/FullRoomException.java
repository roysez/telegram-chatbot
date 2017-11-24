package org.telegram.chatbot.exception;

public class FullRoomException extends Exception {
    public FullRoomException(String full_room) {
        super(full_room);
    }
}
