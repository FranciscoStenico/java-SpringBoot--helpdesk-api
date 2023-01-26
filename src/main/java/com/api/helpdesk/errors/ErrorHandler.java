package com.api.helpdesk.errors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(AppError.class)
    public ResponseEntity<AppError> main(AppError error, HttpServletRequest request) {
        error.setPath(request);

        switch (error.getStatus()) {
            case 401:
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
            case 403:
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(error);
            case 404:
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
            default:
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }
    }

}
