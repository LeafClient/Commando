package com.leafclient.commando;

import com.leafclient.commando.argument.CommandArgument;
import com.leafclient.commando.context.CommandContext;
import com.leafclient.commando.exception.CommandException;
import com.leafclient.commando.exception.CommandNotFoundException;
import com.leafclient.commando.exception.ParserMissingException;
import com.leafclient.commando.executor.CommandExecutor;
import com.leafclient.commando.parser.ArgumentParser;
import com.leafclient.commando.parser.ArgumentParsers;
import com.leafclient.commando.utility.CommandUtils;

import java.util.*;

/**
 * Contains and manage the commands for Commando.
 *
 * @param <E> The command sender type
 */
public class CommandManager<E> {

    private char PREFIX;
    private char SEPARATOR;
    private final List<Command<E>> commands = new ArrayList<>();

    public CommandManager(char prefix, char separator) {
        this.PREFIX = prefix;
        this.SEPARATOR = separator;
    }

    /**
     * Checks whether specified text starts with {@link this#PREFIX} and runs
     * the command if it does.
     *
     * @param text Text
     */
    public boolean runIfCommand(E sender, String text) throws CommandException {
        if(!text.startsWith(String.valueOf(PREFIX)) || text.length() == 1)
            return false;

        String[] parts = text.substring(1).split(String.valueOf(SEPARATOR));
        for(Command<E> command: commands) {
            if(!CommandUtils.anyEqualsIgnoreCase(parts[0], command.getNames()))
                continue; // Not the command we expect

            final List<String> args = new ArrayList<>(Arrays.asList(parts));
            args.remove(0);
            final CommandContext context = new CommandContext(command, args);
            command.getExecutor()
                    .execute(sender, context);
            return true;
        }
        throw new CommandNotFoundException(parts[0]);
    }

    /**
     * @return The list of all registered commands.
     */
    public List<Command<E>> getCommands() {
        return commands;
    }

    /**
     * Creates a new {@link Builder} instance for this {@link CommandManager}.
     *
     * @param names Command names
     * @return Builder
     */
    public Builder<E> newCommand(String... names) {
        return new Builder<>(this, names);
    }

    /**
     * The command builder used to build a {@link Command} instance attached to this {@link CommandManager}.
     *
     * @param <E> Command sender type
     */
    public static class Builder<E> {
        private final CommandManager<E> commandManager;

        private final String[] names;
        private String description = "";
        private final List<CommandArgument<?>> arguments = new ArrayList<>();

        private CommandExecutor<E> executor;

        public Builder(CommandManager<E> commandManager, String... names) {
            this.commandManager = commandManager;
            this.names = names;
        }

        public Builder<E> description(String description) {
            this.description = description;
            return this;
        }

        public Builder<E> executor(CommandExecutor<E> executor) {
            this.executor = executor;
            return this;
        }

        public <T> Builder<E> argument(String name, Class<T> type) {
            final ArgumentParser<T> parser = ArgumentParsers.parserFor(type);
            if(parser == null)
                throw new ParserMissingException(type);

            arguments.add(CommandArgument.of(name, type));
            return this;
        }

        public <T> Builder<E> optionalArgument(String name, Class<T> type) {
            final ArgumentParser<T> parser = ArgumentParsers.parserFor(type);
            if(parser == null)
                throw new ParserMissingException(type);

            arguments.add(CommandArgument.of(name, type, true));
            return this;
        }

        public CommandManager<E> done() {
            commandManager
                    .commands
                    .add(
                            new Command<>(
                                    names, description,
                                    arguments.toArray(new CommandArgument[]{}), executor
                            )
                    );
            return commandManager;
        }
    }

}
