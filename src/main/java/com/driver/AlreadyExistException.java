package com.driver;

public class AlreadyExistException extends RuntimeException {
    public AlreadyExistException(String name){
        super("" + name + " already exists.");
    }

}
