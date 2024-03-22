package com.rubengv.portfolio.exceptions;

import org.springframework.http.HttpStatus;

public class OutdatedTokenException extends ExceptionWithStatus{
    public OutdatedTokenException(String token) {
        super("The token " + token + " is outdated, please log in again.", HttpStatus.FORBIDDEN);
    }
}
