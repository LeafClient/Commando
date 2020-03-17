package com.leafclient.commando.exception;

public class ParserMissingException extends RuntimeException {

    public ParserMissingException(Class<?> parserClass) {
        super("No parser found for type \"" + parserClass.getSimpleName() + "\" !");
    }

}
