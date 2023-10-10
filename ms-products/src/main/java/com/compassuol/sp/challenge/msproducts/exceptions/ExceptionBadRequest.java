package com.compassuol.sp.challenge.msproducts.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class ExceptionsRequest  extends RuntimeException {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    private static final long serialVersionUID = 1L;

    public ExceptionsRequest(String exception){
        super(exception);
    }
}
