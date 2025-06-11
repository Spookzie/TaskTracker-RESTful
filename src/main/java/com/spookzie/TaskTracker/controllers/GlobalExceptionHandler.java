package com.spookzie.TaskTracker.controllers;

import com.spookzie.TaskTracker.domain.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;


/*  "listens" for exceptions thrown in controller methods   */
@ControllerAdvice
public class GlobalExceptionHandler
{
    /*  When any controller throws an IllegalArgumentException,
    * this handler catches it and returns a structured error response instead of a raw exception or stack trace
    *****************************/
    @ExceptionHandler({IllegalArgumentException.class})
    public ResponseEntity<ErrorResponse> handleExceptions(RuntimeException ex, WebRequest request)
    {
        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                ex.getMessage(),
                request.getDescription(false)
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}