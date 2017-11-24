package org.telegram.chatbot.utils;

public class StringUtils {
    public static boolean isCommand(String string) {
        return string.startsWith("/");
    }
}
