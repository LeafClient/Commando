package com.leafclient.commando.exception;

/**
 * The {@link Exception} extension used by commando.
 */
public class CommandException extends Exception {

    /**
     * @return A one-line stack trace
     */
    public String getSmallStacktrace() {
        return "CommandException has been thrown :c";
    }

}
