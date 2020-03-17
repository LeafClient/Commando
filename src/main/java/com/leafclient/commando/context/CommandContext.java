package com.leafclient.commando.context;

import com.leafclient.commando.Command;
import com.leafclient.commando.argument.CommandArgument;
import com.leafclient.commando.exception.ArgumentMissingException;
import com.leafclient.commando.exception.CommandException;
import com.leafclient.commando.parser.ArgumentParser;
import com.leafclient.commando.parser.ArgumentParsers;

import java.util.*;

@SuppressWarnings("all")
public final class CommandContext {

    private final Map<String, Object> parsedArguments = new HashMap<>();

    /**
     * Creates a {@link CommandContext} with specified information
     *
     * @param command Command
     * @param arguments Arguments
     */
    public CommandContext(Command<?> command, List<String> arguments) throws CommandException {
        for(CommandArgument<?> argument: command.getArguments()) {
            if(arguments.isEmpty()) {
                if (!argument.isOptional())
                    throw new ArgumentMissingException(argument);
                continue;
            }

            final Object result = argument.parser().parseFrom((Class)argument.getType(), arguments);
            if(result == null && !argument.isOptional())
                throw new ArgumentMissingException(argument);

            parsedArguments.put(argument.getName(), result);
        }
    }
    /**
     * @param name Argument's name
     * @param <T> Argument's type
     * @return Optional argument with result wrapped inside of the {@link Optional} object
     * @see Optional
     */
    public <T> Optional<T> optional(String name) {
        return Optional.ofNullable((T)parsedArguments.get(name));
    }

    /**
     * @param name Argument's name
     * @param <T> Argument's type
     * @return Argument value
     */
    public <T> T arg(String name) {
        return (T)parsedArguments.get(name);
    }

    /**
     * Tries to parse on the fly an argument
     * @param type Argument type
     * @param text Argument text
     * @param <T> Argument type
     * @return Optional value
     */
    public <T> Optional<T> parse(Class<T> type, String text) {
        final ArgumentParser<T> parser = ArgumentParsers.parserFor(type);
        if(parser == null)
            return Optional.empty();

        return Optional.ofNullable(parser.parseFrom(type, new ArrayList<>(Arrays.asList(text))));
    }

}
