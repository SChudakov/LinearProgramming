package com.sschudakov.lp.simplexmethod.exception;

/**
 * Created by Semen Chudakov on 30.09.2017.
 */
public class NoBasisException extends RuntimeException{
    public NoBasisException() {
    }
    public NoBasisException(String message) {
        super(message);
    }
}
