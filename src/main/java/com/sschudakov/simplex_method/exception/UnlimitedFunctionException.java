package com.sschudakov.simplex_method.exception;

/**
 * Created by Semen Chudakov on 10.09.2017.
 */
public class UnlimitedFunctionException extends RuntimeException {

    public UnlimitedFunctionException(){
        super();
    }
    public UnlimitedFunctionException(String message){
        super(message);
    }
}
