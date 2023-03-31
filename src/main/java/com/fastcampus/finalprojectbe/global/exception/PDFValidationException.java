package com.fastcampus.finalprojectbe.global.exception;


public class PDFValidationException extends RuntimeException{
    public PDFValidationException() {
        super();
    }

    public PDFValidationException(String message) {
        super(message);
    }

    public PDFValidationException(String message, Throwable cause) {
        super(message, cause);
    }
}