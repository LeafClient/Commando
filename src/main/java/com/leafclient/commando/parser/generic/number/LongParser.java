package com.leafclient.commando.parser.generic.number;

import com.leafclient.commando.parser.ArgumentParser;

import java.util.List;

public final class LongParser implements ArgumentParser<Long> {

    @Override
    public Long parseFrom(Class<Long> objectClass, List<String> textArguments) {
        String numberText = textArguments.get(0)
                .toLowerCase();

        try {
            final long returnValue = Long.parseLong(numberText);
            textArguments.remove(0);
            return returnValue;
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
