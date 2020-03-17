package com.leafclient.commando;

import com.leafclient.commando.argument.CommandArgument;
import com.leafclient.commando.executor.CommandExecutor;

/**
 * Represent a {@link Command} to the {@link CommandManager} instance and contains
 * all the information required about a command.
 *
 * @param <E> Command sender type
 */
public final class Command<E> {

    private final String[] names;
    private final String description;
    private final CommandArgument<?>[] arguments;
    private final CommandExecutor<E> executor;

    public Command(String[] names, String description, CommandArgument<?>[] arguments, CommandExecutor<E> executor) {
        this.names = names;
        this.description = description;
        this.arguments = arguments;
        this.executor = executor;
    }

    /**
     * @return The names of this {@link Command}
     */
    public String[] getNames() {
        return names;
    }

    /**
     * @return The description of this {@link Command}
     */
    public String getDescription() {
        return description;
    }

    /**
     * @return The arguments of this {@link Command}
     */
    public CommandArgument<?>[] getArguments() {
        return arguments;
    }

    /**
     * @return The {@link CommandExecutor} of this {@link Command}
     * @see CommandExecutor
     */
    public CommandExecutor<E> getExecutor() {
        return executor;
    }
}
