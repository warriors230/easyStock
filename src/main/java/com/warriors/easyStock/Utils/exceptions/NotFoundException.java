package com.warriors.easyStock.Utils.exceptions;

public class NotFoundException extends BadRequestException{
    private static final String DESCRIPTION = "No se encontro el recurso";
    public NotFoundException(String detail) {
        super(DESCRIPTION + ". "+ detail);
    }


}
