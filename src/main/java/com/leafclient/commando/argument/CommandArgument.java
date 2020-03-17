package com.leafclient.commando.argument;

import com.leafclient.commando.parser.ArgumentParser;
import com.leafclient.commando.parser.ArgumentParsers;

public final class CommandArgument<T> {

    /**
     * Creates a {@link CommandArgument} instance.
     *
     * @param name Argument's name
     * @param type Argument's type class
     * @param <T> Argument's type
     */
    public static <T> CommandArgument<T> of(String name, Class<T> type) {
        return new CommandArgument<>(name, type, false);
    }

    /**
     * Creates a {@link CommandArgument} instance.
     *
     * @param name Argument's name
     * @param type Argument's type class
     * @param optional Optional
     * @param <T> Argument's type
     */
    public static <T> CommandArgument<T> of(String name, Class<T> type, boolean optional) {
        return new CommandArgument<>(name, type, optional);
    }

    private final String name;
    private final Class<T> type;
    private final boolean optional;

    CommandArgument(String name, Class<T> type, boolean optional) {
        this.name = name;
        this.type = type;
        this.optional = optional;
    }

    /**
     * @return The name used by the developers to find this {@link CommandArgument} value.
     */
    public String getName() {
        return name;
    }

    /**
     * @return The type of this {@link CommandArgument}
     */
    public Class<T> getType() {
        return type;
    }

    /**
     * @return True if this argument can be skipped when parsing (if no objects were found
     * for the provided argument, we go to the next one).
     */
    public boolean isOptional() {
        return optional;
    }

    /**
     * @return The parser for this argument
     * @see ArgumentParsers#parserFor(Class)
     */
    public ArgumentParser<T> parser() {
        return ArgumentParsers.parserFor(getType());
    }

}
