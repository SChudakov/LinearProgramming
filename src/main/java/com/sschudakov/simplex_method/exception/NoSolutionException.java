package com.sschudakov.simplex_method.exception;

/**
 * Created by Semen Chudakov on 26.10.2017.
 */
public class NoSolutionException extends RuntimeException{
    public NoSolutionException() {
    }

    public NoSolutionException(String message) {
        super(message);
    }

    public NoSolutionException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoSolutionException(Throwable cause) {
        super(cause);
    }

    public NoSolutionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
