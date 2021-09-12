package com.mguzman.empledata.exception;

@SuppressWarnings("serial")
public class ModeloNotFoundException extends RuntimeException{

    public ModeloNotFoundException(String message) {
        super(message);
    }
}
