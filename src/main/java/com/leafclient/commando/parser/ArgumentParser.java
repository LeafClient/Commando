package com.leafclient.commando.parser;

import java.util.List;
import java.util.Optional;

/**
 * Represents a {@link ArgumentParser} which parses a String (argument of a command) into an object instance.
 *
 * @param <T> Object type
 */
public interface ArgumentParser<T> {

    /**
     * @return an empty {@link ArgumentParser} implementation.
     */
    static <T> ArgumentParser<T> empty() {
        return (oClass, text) -> null;
    }

    /**
     * Do not forget to remove the text arguments you use from the list!
     *
     * @param textArguments The text argument
     * @return A potential parsed object from the text argument or null
     */
    T parseFrom(Class<T> objectClass, List<String> textArguments);

}
