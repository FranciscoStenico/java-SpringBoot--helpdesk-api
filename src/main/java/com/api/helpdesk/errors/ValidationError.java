package com.api.helpdesk.errors;

import java.util.List;

import java.util.ArrayList;

public class ValidationError extends AppError {

    private static final long serialVersionUID = 1L;

    private List<String> errors = new ArrayList<>();
    private Integer status = 400;
    private String error = "BAD_REQUEST: Validation error";

    public ValidationError(String message) {
        super(message);
    }

    public List<String> getErrors() {
        return errors;
    }

    public void addErrors(String message) {
        this.errors.add(message);
    }

    public Integer getStatus() {
        return status;
    }

    public String getError() {
        return error;
    }

}
