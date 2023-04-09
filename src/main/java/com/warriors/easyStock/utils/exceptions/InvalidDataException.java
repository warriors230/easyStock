package com.warriors.easyStock.utils.exceptions;

import org.springframework.validation.BindingResult;

public class InvalidDataException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private final transient BindingResult result;

    public InvalidDataException(BindingResult result) {
        super();
        this.result = result;
    }

    public InvalidDataException(String mensaje, BindingResult result) {
        super(mensaje);
        this.result = result;
    }

    public BindingResult getResult() {
        return result;
    }
}
