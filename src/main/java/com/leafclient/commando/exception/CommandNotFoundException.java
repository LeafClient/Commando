package com.leafclient.commando.exception;

/**
 * Exception thrown by the {@link com.leafclient.commando.CommandManager} when there's no valid command.
 */
public class CommandNotFoundException extends CommandException {

    private final String buffer;

    public CommandNotFoundException(String buffer) {
        this.buffer = buffer;
    }

    @Override
    public String getSmallStacktrace() {
        return "There is no command \"" + buffer + "\"!";
    }
}
