package com.fastcampus.finalprojectbe.global.exception;

public class KeywordValidationException extends RuntimeException {

    public KeywordValidationException() {super();}

    public KeywordValidationException(String message) {
        super(message);
    }

    public KeywordValidationException(String message, Throwable cause) {
        super(message, cause);
    }

}
