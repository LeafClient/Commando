package com.leafclient.commando.parser.generic;

import com.leafclient.commando.parser.ArgumentParser;

import java.util.List;

public final class BooleanParser implements ArgumentParser<Boolean> {

    @Override
    public Boolean parseFrom(Class<Boolean> booleanClass, List<String> textArguments) {
        String booleanText = textArguments.get(0).toLowerCase();

        if(booleanText.equalsIgnoreCase("true")) {
            textArguments.remove(0);
            return true;
        } else if(booleanText.equalsIgnoreCase("false")) {
            textArguments.remove(0);
            return false;
        } else {
            return null;
        }
    }
}
