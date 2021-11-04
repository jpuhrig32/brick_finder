package com.juhrig.bricktool.error;

public class APIMessageException extends RuntimeException{
    public APIMessageException() {
        super();
    }

    public APIMessageException(String message) {
        super(message);
    }

    public APIMessageException(String message, Throwable cause) {
        super(message, cause);
    }

    public APIMessageException(Throwable cause) {
        super(cause);
    }

    protected APIMessageException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
