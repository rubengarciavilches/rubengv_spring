package com.rubengv.portfolio.exceptions;

import org.springframework.http.HttpStatus;

public class NoteEmptyException extends ExceptionWithStatus{
    public NoteEmptyException(String note) {
        super("Note " + note + " cant be empty.", HttpStatus.BAD_REQUEST);
    }
}
