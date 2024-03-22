package com.rubengv.portfolio.exceptions;

import org.springframework.http.HttpStatus;

public class IncorrectTokenException extends ExceptionWithStatus{
    public IncorrectTokenException(String token) {
        super("Token " + token + " doesn't have permissions for the user.", HttpStatus.FORBIDDEN);
    }
}
