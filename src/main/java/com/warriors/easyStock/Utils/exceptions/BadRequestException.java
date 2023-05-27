package com.warriors.easyStock.Utils.exceptions;

public class BadRequestException extends RuntimeException{
    private static final String DESCRIPTION = "Bad Request Exception ";

    public BadRequestException(String detail){
        super(DESCRIPTION + ". "+detail);
    }
}
