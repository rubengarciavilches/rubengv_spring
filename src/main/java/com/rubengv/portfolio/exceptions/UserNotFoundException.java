package com.rubengv.portfolio.exceptions;

import org.springframework.http.HttpStatus;

public class UserNotFoundException extends ExceptionWithStatus {
    public UserNotFoundException(String userID) {
        super("User with email " + userID + " does not exist.", HttpStatus.NOT_FOUND);
    }
}