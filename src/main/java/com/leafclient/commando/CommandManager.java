package com.leafclient.commando;

import com.leafclient.commando.argument.CommandArgument;
import com.leafclient.commando.context.CommandContext;
import com.leafclient.commando.exception.CommandException;
import com.leafclient.commando.exception.CommandNotFoundException;
import com.leafclient.commando.executor.CommandExecutor;
import com.leafclient.commando.parser.ArgumentParser;
import com.leafclient.commando.parser.ArgumentParsers;
import com.leafclient.commando.utility.CommandUtils;

import java.util.*;

/**
 * Contains and manage the commands for Commando.
 *
 * @param <CE> The command sender type
 */
public class CommandManager<CE> {

    private char PREFIX, SEPARATOR;
    private final List<Command<CE>> commands = new ArrayList<>();

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
    public boolean runIfCommand(CE sender, String text) throws CommandException {
        if(!text.startsWith(String.valueOf(PREFIX)) || text.length() == 1)
            return false;

        String[] parts = text.substring(1).split(String.valueOf(SEPARATOR));
        for(Command<CE> command: commands) {
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
    public List<Command<CE>> getCommands() {
        return commands;
    }

    /**
     * Creates a new {@link Builder} instance for this {@link CommandManager}.
     *
     * @param names Command names
     * @return Builder
     */
    public Builder<CE> newCommand(String... names) {
        return new Builder<>(this, names);
    }

    /**
     * The command builder used to build a {@link Command} instance attached to this {@link CommandManager}.
     *
     * @param <CE> Command sender type
     */
    public static class Builder<CE> {
        private final CommandManager<CE> commandManager;

        private final String[] names;
        private String description = "";
        private final List<CommandArgument<?>> arguments = new ArrayList<>();

        private CommandExecutor<CE> executor;

        public Builder(CommandManager<CE> commandManager, String... names) {
            this.commandManager = commandManager;
            this.names = names;
        }

        public Builder<CE> description(String description) {
            this.description = description;
            return this;
        }

        public Builder<CE> executor(CommandExecutor<CE> executor) {
            this.executor = executor;
            return this;
        }

        public <T> Builder<CE> argument(String name, Class<T> type) {
            final ArgumentParser<T> parser = ArgumentParsers.parserFor(type);
            if(parser == null)
                throw new RuntimeException("No parser found for type " + type.getSimpleName());

            arguments.add(CommandArgument.of(name, type));
            return this;
        }

        public <T> Builder<CE> optionalArgument(String name, Class<T> type) {
            final ArgumentParser<T> parser = ArgumentParsers.parserFor(type);
            if(parser == null)
                throw new RuntimeException("No parser found for type " + type.getSimpleName());

            arguments.add(CommandArgument.of(name, type, true));
            return this;
        }

        public CommandManager<CE> done() {
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
