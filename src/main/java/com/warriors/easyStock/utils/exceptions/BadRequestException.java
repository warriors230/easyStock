package com.warriors.easyStock.utils.exceptions;

public class BadRequestException extends RuntimeException{
    private static final String DESCRIPTION = "Bad Request Exception ";

    public BadRequestException(String detail){
        super(DESCRIPTION + ". "+detail);
    }
}
