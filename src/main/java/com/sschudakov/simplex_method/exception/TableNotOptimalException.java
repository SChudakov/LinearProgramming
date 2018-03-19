package com.sschudakov.simplex_method.exception;

/**
 * Created by Semen Chudakov on 20.10.2017.
 */
public class TableNotOptimalException extends RuntimeException{
    public TableNotOptimalException() {
    }

    public TableNotOptimalException(String message) {
        super(message);
    }

    public TableNotOptimalException(String message, Throwable cause) {
        super(message, cause);
    }

    public TableNotOptimalException(Throwable cause) {
        super(cause);
    }

    public TableNotOptimalException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
