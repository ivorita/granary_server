package com.ivorita.management.exception;

public class AdminExistException extends Exception {

    public AdminExistException() {
        super();
    }

    public AdminExistException(String message, Throwable cause, boolean enableSuppression,
                                       boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public AdminExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public AdminExistException(String message) {
        super(message);
    }

    public AdminExistException(Throwable cause) {
        super(cause);
    }

}
