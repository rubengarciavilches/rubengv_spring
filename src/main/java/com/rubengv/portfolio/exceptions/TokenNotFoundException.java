package com.rubengv.portfolio.exceptions;

import org.springframework.http.HttpStatus;

public class TokenNotFoundException extends ExceptionWithStatus{
    public TokenNotFoundException(String token) {
        super("Token " + token + " does not exist.", HttpStatus.NOT_FOUND);
    }
}
