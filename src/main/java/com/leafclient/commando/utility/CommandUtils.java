package com.leafclient.commando.utility;

public final class CommandUtils {

    /**
     * @param text The text we look into
     * @param arguments The text we look for
     * @return True if one of the argument is contained in specified text.
     */
    public static boolean anyEqualsIgnoreCase(String text, String[] arguments) {
        final int size = arguments.length;
        for(int i = 0; i < size; i++) {
            if(text.equalsIgnoreCase(arguments[i]))
                return true;
        }
        return false;
    }

}
