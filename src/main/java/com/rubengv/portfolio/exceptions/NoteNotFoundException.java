package com.rubengv.portfolio.exceptions;

import org.springframework.http.HttpStatus;

public class NoteNotFoundException extends ExceptionWithStatus{
    public NoteNotFoundException(String note) {
        super("Note " + note + " does not exist.", HttpStatus.NOT_FOUND);
    }
}
