package com.rubengv.portfolio.exceptions;

import org.springframework.http.HttpStatus;

public class IncorrectCredentialsException extends ExceptionWithStatus {
    public IncorrectCredentialsException(String email) {
        super("Incorrect credentials for " + email, HttpStatus.FORBIDDEN);
    }
}