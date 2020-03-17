package com.leafclient.commando.parser;

import com.leafclient.commando.parser.generic.BooleanParser;
import com.leafclient.commando.parser.generic.EnumParser;
import com.leafclient.commando.parser.generic.StringParser;
import com.leafclient.commando.parser.generic.number.DoubleParser;
import com.leafclient.commando.parser.generic.number.FloatParser;
import com.leafclient.commando.parser.generic.number.IntParser;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("unchecked")
public final class ArgumentParsers {

    private static final Map<Class<?>, ArgumentParser<?>> GENERIC_PARSERS = new HashMap<>();

    /**
     * Registers specified {@link ArgumentParser} to the generic parsers used by every commands.
     *
     * @param objectClass Object's class
     * @param parser Parser of the object specified using the object's class
     * @param <T> Object's type
     */
    public static <T> void register(Class<T> objectClass, ArgumentParser<T> parser) {
        GENERIC_PARSERS.put(objectClass, parser);
    }

    /**
     * @param objectClass Object's class
     * @param <T> Object's type
     * @return the {@link ArgumentParser} for specified object class
     */
    public static <T> ArgumentParser<T> parserFor(Class<T> objectClass) {
        ArgumentParser<T> parser = (ArgumentParser<T>) GENERIC_PARSERS.get(objectClass);
        if(parser != null)
            return parser;

        for(Map.Entry<Class<?>, ArgumentParser<?>> entry: GENERIC_PARSERS.entrySet()) {
            if(entry.getKey().isAssignableFrom(objectClass))
                return (ArgumentParser<T>) entry.getValue();
        }
        return null;
    }

    static {
        register(String.class, new StringParser());
        register(Boolean.class, new BooleanParser());
        register(Double.class, new DoubleParser());
        register(Float.class, new FloatParser());
        register(Integer.class, new IntParser());
        register(Enum.class, new EnumParser());
    }

}
