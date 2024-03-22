package com.rubengv.portfolio.exceptions;

import org.springframework.http.HttpStatus;

public class InvalidFormatException extends ExceptionWithStatus {
    public InvalidFormatException(String field) {
        super("Invalid format: " + field, HttpStatus.BAD_REQUEST);
    }
}
