package com.leafclient.commando.exception;

import com.leafclient.commando.argument.CommandArgument;

/**
 * An exception thrown by the {@link com.leafclient.commando.context.CommandContext} when an argument that is not optional is
 * missing.
 */
public class ArgumentMissingException extends CommandException {

    private final CommandArgument<?> argument;

    public ArgumentMissingException(CommandArgument<?> argument) {
        this.argument = argument;
    }

    @Override
    public String getSmallStacktrace() {
        return "Can't find argument \"" + argument.getName() + "\" of type \"" + argument.getType().getSimpleName() + "\"";
    }

}
