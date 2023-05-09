package com.warriors.easyStock.utils.exceptions;

public class TokenMalFormadoException extends BadRequestException{
    private static final String DESCRIPTION = "Token invalido!";

    public TokenMalFormadoException(String detail){
        super(DESCRIPTION + ". "+detail);
    }
}
