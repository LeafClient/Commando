package com.leafclient.commando.parser.generic.number;

import com.leafclient.commando.parser.ArgumentParser;

import java.util.List;

public final class IntParser implements ArgumentParser<Integer> {

    @Override
    public Integer parseFrom(Class<Integer> objectClass, List<String> textArguments) {
        String numberText = textArguments.get(0)
                .toLowerCase();

        try {
            final int returnValue = Integer.parseInt(numberText);
            textArguments.remove(0);
            return returnValue;
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
