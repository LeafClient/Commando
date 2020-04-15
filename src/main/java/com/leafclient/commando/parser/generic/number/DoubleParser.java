package com.leafclient.commando.parser.generic.number;

import com.leafclient.commando.parser.ArgumentParser;

import java.util.List;

public final class DoubleParser implements ArgumentParser<Double> {

    @Override
    public Double parseFrom(Class<Double> doubleClass, List<String> textArguments) {
        String numberText = textArguments.get(0)
                .toLowerCase()
                .replaceAll(",", ".");

        try {
            final double returnValue = Double.parseDouble(numberText);
            textArguments.remove(0);
            return returnValue;
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
