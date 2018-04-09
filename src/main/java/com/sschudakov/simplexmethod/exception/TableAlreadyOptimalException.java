package com.sschudakov.simplexmethod.exception;

/**
 * Created by Semen Chudakov on 10.09.2017.
 */
public class TableAlreadyOptimalException extends RuntimeException {

    public TableAlreadyOptimalException(){
        super();
    }
    public TableAlreadyOptimalException(String message){
        super(message);
    }

}
