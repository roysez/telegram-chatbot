package org.telegram.chatbot.command;

import java.util.HashMap;
import java.util.Map;

public class CommandSet {

    private Map<String, BotCommand> commands;

    public CommandSet() {
        commands = new HashMap<>();

    }

    public BotCommand getCommand(String key) {
        return commands.get(key);
    }

    public void addCommand(BotCommand command) {
        commands.putIfAbsent(command.getCommandName(), command);
    }

    public boolean contains(String command) {
        return commands.containsKey(command);
    }
}
