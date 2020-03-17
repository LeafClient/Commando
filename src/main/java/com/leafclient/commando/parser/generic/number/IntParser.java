package com.leafclient.commando.parser.generic.number;

import com.leafclient.commando.parser.ArgumentParser;

import java.util.List;

public final class IntParser implements ArgumentParser<Integer> {

    @Override
    public Integer parseFrom(Class<Integer> objectClass, List<String> textArguments) {
        String numberText = textArguments.get(0)
                .toLowerCase();

        try {
            return Integer.valueOf(numberText);
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
