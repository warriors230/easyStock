package com.warriors.easyStock.utils.exceptions;

public class UnauthorizedException extends BadRequestException{
    private static final String DESCRIPTION = "No esta authorizado para ingresar al recurso";
    public UnauthorizedException(String detail) {
        super(DESCRIPTION + ". "+ detail);
    }
}
