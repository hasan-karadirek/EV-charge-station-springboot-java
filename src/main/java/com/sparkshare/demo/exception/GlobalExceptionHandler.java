package com.sparkshare.demo.exception;

import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler{

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<CustomErrorResponse> handleApiException(ApiException ex, WebRequest request) {
        CustomErrorResponse errorResponse = new CustomErrorResponse(
            LocalDateTime.now(),
            ex.getStatusCode(),
            ex.getMessage()
        );
        HttpStatus status = HttpStatus.valueOf(ex.getStatusCode());
        return new ResponseEntity<>(errorResponse, status);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<CustomErrorResponse> handleGlobalException(Exception ex) {
        CustomErrorResponse errorResponse = new CustomErrorResponse(
            LocalDateTime.now(),
            500,
            ex.getMessage()
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
