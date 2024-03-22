package com.rubengv.portfolio.exceptions;

import org.springframework.http.HttpStatus;

public class EmailTakenException extends ExceptionWithStatus{
    public EmailTakenException(String email) {
        super("The email " + email + " is already registered.", HttpStatus.CONFLICT);
    }
}
