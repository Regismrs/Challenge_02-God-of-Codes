package com.compassuol.sp.challenge.msfeedback.exceptions;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ApplicationExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({ConstraintViolationException.class})
    public ExceptionsResponse handleExceptionsBadRequest(ConstraintViolationException e){

        List<FieldError> details = new ArrayList<>();

        for (ConstraintViolation<?> violation : e.getConstraintViolations()) {
            String field = violation.getPropertyPath().toString();
            String message = violation.getMessage();
            details.add(new FieldError(field, message));
        }

        return new ExceptionsResponse(
                400,
                "Bad Request",
                "Invalid request",
                details
        );
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ExceptionsResponse handleSQLExceptionsBadRequest(){
        return new ExceptionsResponse(
                400,
                "Bad Request",
                "invalid data",
                new ArrayList<>()
        );
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFound.class)
    public ExceptionsResponse handleExceptionNotFound(NotFound e){
        return new ExceptionsResponse(
                404,
                "Not Found",
                e.getMessage(),
                new ArrayList<>()
        );
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ExceptionsResponse handleAllException(){

        return new ExceptionsResponse(
                500,
                "Internal Server Error",
                "Unexpected Error",
                new ArrayList<>()
        );
    }
}
