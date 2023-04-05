package com.fastcampus.finalprojectbe.global.exception;


public class NoSearchAdressException extends RuntimeException{
    public NoSearchAdressException() {
        super();
    }

    public NoSearchAdressException(String message) {
        super(message);
    }

    public NoSearchAdressException(String message, Throwable cause) {
        super(message, cause);
    }
}