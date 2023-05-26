package com.warriors.easyStock.Utils.exceptions;

public class NoSoporteJWTException extends BadRequestException{
    private static final String DESCRIPTION = "token no soportado!";

    public NoSoporteJWTException(String detail){
        super(DESCRIPTION + ". "+detail);
    }
}
