package com.thoughtworks.capability.gtb.restfulapidesign.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandle {

    @ExceptionHandler({NoSuchStudentIdFoundException.class,
            NoSuchGroupIdFoundException.class})
    public ResponseEntity<ErrorResponse> handle(Exception exception) {
        return ResponseEntity.notFound().build();
    }
}
