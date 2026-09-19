package com.example.Employee_Management_System.Exception;

import org.springframework.validation.Errors;

public class MethodArgumentsNotValidException extends RuntimeException {
    public MethodArgumentsNotValidException(String message) {
        super(message);
    }

    public Errors getBindingResult() {
        return null;
    }
}
