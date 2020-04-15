package com.leafclient.commando.parser.generic.number;

import com.leafclient.commando.parser.ArgumentParser;

import java.util.List;

public final class FloatParser implements ArgumentParser<Float> {

    @Override
    public Float parseFrom(Class<Float> objectClass, List<String> textArguments) {
        String numberText = textArguments.get(0)
                .toLowerCase()
                .replaceAll(",", ".");

        try {
            final float returnValue = Float.parseFloat(numberText);
            textArguments.remove(0);
            return returnValue;
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
