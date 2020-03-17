package com.leafclient.commando.executor;

import com.leafclient.commando.context.CommandContext;

/**
 * Interface used to mark a {@link CommandExecutor} that contains a {@link this#execute(Object, CommandContext)} method
 * executed when the command is ran.
 *
 * @param <CE> Command sender type
 */
public interface CommandExecutor<CE> {

    void execute(CE sender, CommandContext context);

}
