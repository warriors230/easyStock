package com.warriors.easyStock.utils.exceptions;

public class ConflictException extends BadRequestException{
    private static final String DESCRIPTION = "Existe un Conflicto en la petición";

    public ConflictException(String detail){
        super(DESCRIPTION + ". "+detail);
    }
}
