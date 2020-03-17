package com.leafclient.commando.parser.generic;

import com.leafclient.commando.parser.ArgumentParser;

import java.util.Iterator;
import java.util.List;

/**
 * Default parser used for strings.
 */
public final class StringParser implements ArgumentParser<String> {

    @Override
    public String parseFrom(Class<String> stringClass, List<String> textArguments) {
        StringBuilder builder = new StringBuilder();
        Iterator<String> iterator = textArguments.iterator();
        while (iterator.hasNext()) {
            String text = iterator.next();
            if(builder.length() == 0) {
                if(text.startsWith("\"")) {
                    builder
                            .append(text, 1, text.length())
                            .append(" ");

                    iterator.remove();
                } else {
                    builder.append(text);
                    iterator.remove();
                    break;
                }
            } else {
                if(text.endsWith("\"")) {
                    builder
                            .append(text, 0, text.length() - 1);

                    iterator.remove();
                    break;
                }
                builder
                        .append(text)
                        .append(" ");
                iterator.remove();
            }
        }
        if(builder.length() == 0)
            return null;

        return builder.toString();
    }
}
