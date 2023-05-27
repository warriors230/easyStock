package com.warriors.easyStock.Utils.exceptions;

public class ForbiddenException extends BadRequestException{
    private static final String DESCRIPTION = "No tiene Permiso Para Acceder al Recurso";

    public ForbiddenException(String detail){
        super(DESCRIPTION + ". "+detail);
    }
}
