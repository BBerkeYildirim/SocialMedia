package com.berke.socialmedia.exception;


import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class CustomControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {ConstraintViolationException.class})
    public ResponseEntity<?> handleUniqueConstraintException(ConstraintViolationException e, WebRequest webRequest){
        ErrorResponse errorResponse = new ErrorResponse(new Date(), "Bu email veya username daha önce kullanılmış");
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = {NotFoundException.class})
    public ResponseEntity<?> handleNotFoundException(NotFoundException e, WebRequest webRequest){
        ErrorResponse errorResponse = new ErrorResponse(new Date(), e.getMessage(), e.getDetails());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {AlreadyExistsException.class})
    public ResponseEntity<ErrorResponse> handleAlreadyExistsException(AlreadyExistsException e, WebRequest webRequest){
        ErrorResponse errorResponse = new ErrorResponse(new Date(), e.getMessage(), e.getDetails());
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleExceptions(Exception e, WebRequest webRequest) {
        ErrorResponse errorResponse = new ErrorResponse(new Date(), e.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.EXPECTATION_FAILED);
    }
}
