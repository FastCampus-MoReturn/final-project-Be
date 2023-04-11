package com.fastcampus.finalprojectbe.global.exception;

public class DataNotFoundException extends Throwable {

    public DataNotFoundException() {super();}

    public DataNotFoundException(String message) {
        super(message);
    }

    public DataNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
