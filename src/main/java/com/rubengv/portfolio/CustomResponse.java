package com.rubengv.portfolio;

public class CustomResponse<T> {
    private T content;
    private String errorMessage;

    public CustomResponse(T content, String errorMessage) {
        this.content = content;
        this.errorMessage = errorMessage;
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}