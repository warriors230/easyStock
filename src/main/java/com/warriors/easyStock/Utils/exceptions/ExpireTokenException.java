package com.warriors.easyStock.Utils.exceptions;

public class ExpireTokenException extends BadRequestException{
    private static final String DESCRIPTION = "token expirado!";

    public ExpireTokenException(String detail){
        super(DESCRIPTION + ". "+detail);
    }
}
