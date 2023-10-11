package com.compassuol.sp.challenge.msproducts.exceptions;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ApplicationExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ExceptionsResponse handleExceptionsBadRequest(MethodArgumentNotValidException e){

        List<FieldError> details =
        e.getBindingResult().getFieldErrors().stream().map(
                error -> new FieldError(error.getField(), error.getDefaultMessage())
        ).collect(Collectors.toList());
        
        ExceptionsResponse exceptionResponse = new ExceptionsResponse(
                400,
                "Bad Request",
                "Invalid request",
                details
        );
        return exceptionResponse;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ExceptionsResponse handleSQLExceptionsBadRequest(SQLIntegrityConstraintViolationException e){
        ExceptionsResponse exceptionsResponse = new ExceptionsResponse(
                400,
                "Bad Request",
                "Product name need be an unique value",
                new ArrayList<>()
        );
        return exceptionsResponse;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFound.class)
    public ExceptionsResponse handleExceptionNotFound(NotFound e){
        ExceptionsResponse exceptionsResponse = new ExceptionsResponse(
                404,
                "Not Found",
                e.getMessage(),
                new ArrayList<>()
        );
        return exceptionsResponse;
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ExceptionsResponse handleAllException(Exception e){

        ExceptionsResponse exceptionsResponse = new ExceptionsResponse(
                500,
                "Internal Server Eror",
                "Ocorreu um erro inesperado",
                new ArrayList<>()
        );
        return exceptionsResponse;
    }
}

record FieldError(String field, String message){}
record ExceptionsResponse(int code, String Status, String message, Object details){}

