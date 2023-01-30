package com.api.helpdesk.errors;

import java.time.LocalDateTime;

import org.hibernate.annotations.Any;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.servlet.http.HttpServletRequest;

public class AppError extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private int status;
    private String error;
    private String message;
    private StringBuffer path;
    private LocalDateTime timestamp;

    public AppError(String message) {
        this.message = message;
        this.status = 400;
        setError();
        setTimestamp();
    }

    public AppError(String message, int status) {
        this.message = message;
        this.status = status;
        setError();
        setTimestamp();
    }

    public AppError(String message, int status, String error) {
        this.message = message;
        this.status = status;
        setError();
        this.error = this.error + ": " + error;
        setTimestamp();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError() {
        switch (this.status) {
            case 401:
                this.error = "UNAUTHORIZED";
                break;
            case 403:
                this.error = "FORBIDDEN";
                break;
            case 404:
                this.error = "NOT_FOUND";
                break;
            case 409:
                this.error = "CONFLICT";
                break;
            default:
                this.error = "BAD_REQUEST";
                break;
        }
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public StringBuffer getPath() {
        return path;
    }

    public void setPath(HttpServletRequest request) {
        this.path = request.getRequestURL();
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp() {
        this.timestamp = LocalDateTime.now();
    }

    @JsonIgnore
    private Any cause;
    @JsonIgnore
    private Any stackTrace;
    @JsonIgnore
    private Any suppressed;
    @JsonIgnore
    private Any localizedMessage;

}
