package com.leafclient.commando.parser.generic;

import com.leafclient.commando.parser.ArgumentParser;

import java.util.List;

@SuppressWarnings("all")
public final class EnumParser implements ArgumentParser<Enum> {

    @Override
    public Enum parseFrom(Class<Enum> objectClass, List<String> textArguments) {
        final String arg = textArguments.get(0);
        Enum<?>[] constants = objectClass.getEnumConstants();
        for(Enum<?> constant: constants) {
            if(constant.name().equalsIgnoreCase(arg)) {
                textArguments.remove(0);
                return constant;
            }
        }
        return null;
    }

}
