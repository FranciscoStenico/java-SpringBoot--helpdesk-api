package com.api.helpdesk.errors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(AppError.class)
    public ResponseEntity<AppError> app(AppError error, HttpServletRequest request) {
        error.setPath(request);

        switch (error.getStatus()) {
            case 401:
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
            case 403:
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(error);
            case 404:
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
            case 409:
                return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
            default:
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationError> validations(MethodArgumentNotValidException error,
            HttpServletRequest request) {
        ValidationError errors = new ValidationError("Request is missing required fields");

        errors.setPath(request);
        for (FieldError message : error.getBindingResult().getFieldErrors()) {
            errors.addErrors(message.getDefaultMessage());
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

    @ExceptionHandler(TransactionSystemException.class)
    public ResponseEntity<AppError> invalidData(TransactionSystemException error, HttpServletRequest request) {
        AppError applicationError = new AppError(
                "Please insert a valid CPF", 400, "Could not commit JPA transaction");

        applicationError.setPath(request);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(applicationError);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<AppError> invalidData(HttpMessageNotReadableException error, HttpServletRequest request) {
        AppError applicationError = new AppError(
                "The request body has syntax errors or is null", 400, "Http Message not Readable");

        applicationError.setPath(request);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(applicationError);
    }
    
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<AppError> invalidData(AccessDeniedException error, HttpServletRequest request) {
        AppError applicationError = new AppError(
                "Only administrator accounts can perform this action", 400, "Access denied");

        applicationError.setPath(request);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(applicationError);
    }
}
