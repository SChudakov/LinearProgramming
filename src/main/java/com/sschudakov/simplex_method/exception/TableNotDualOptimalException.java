package com.sschudakov.simplex_method.exception;

/**
 * Created by Semen Chudakov on 20.10.2017.
 */
public class TableNotDualOptimalException extends RuntimeException{
    public TableNotDualOptimalException() {
    }

    public TableNotDualOptimalException(String message) {
        super(message);
    }

    public TableNotDualOptimalException(String message, Throwable cause) {
        super(message, cause);
    }

    public TableNotDualOptimalException(Throwable cause) {
        super(cause);
    }

    public TableNotDualOptimalException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
