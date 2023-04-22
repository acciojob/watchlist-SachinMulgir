package com.driver;

public class NoDataException extends RuntimeException{
    public NoDataException(){
        super("No Data Found");
    }
}
