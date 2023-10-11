package com.compassuol.sp.challenge.msproducts.exceptions;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ExceptionResposeWithDetails handleExceptionsBadRequest(MethodArgumentNotValidException e){

        List<FieldError> details =
        e.getBindingResult().getFieldErrors().stream().map(
                error -> new FieldError(error.getField(), error.getDefaultMessage())
        ).collect(Collectors.toList());
        
        ExceptionResposeWithDetails exceptionResposeWithDetails = new ExceptionResposeWithDetails(
                400,
                "Bad Request",
                e.getMessage(),
                details
        );
        return exceptionResposeWithDetails;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = {NotFound.class})
    public ExceptionsResponse handleExceptionNotFound(NotFound e){
        ExceptionsResponse exceptionsResponse = new ExceptionsResponse(
                404,
                "Not Found",
                e.getMessage()
        );
        return exceptionsResponse;
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = {Exception.class})
    public ExceptionsResponse handleAllException(Exception e){

        ExceptionsResponse exceptionsResponse = new ExceptionsResponse(
                500,
                "Internal Server Eror",
                "Ocorreu um erro inesperado"
        );
        return exceptionsResponse;
    }
}

record FieldError(String field, String message){}
record ExceptionResposeWithDetails(int code, String Status, String message, Object details){}
record ExceptionsResponse(int code,String Status, String message){}
