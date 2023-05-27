package com.warriors.easyStock.Utils.exceptions;

public class ExistException extends BadRequestException{
    private static final String DESCRIPTION = "Ya esxiste el recurso";

    public ExistException(String detail){
        super(DESCRIPTION + ". "+detail);
    }
}
