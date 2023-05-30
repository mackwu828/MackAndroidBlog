package com.mackwu.orr.error;

public class ErrorException extends Exception {

    private ErrorType errorType;

    public ErrorException(ErrorType errorType) {
        this.errorType = errorType;
    }

    public ErrorException(ErrorType errorType, String message) {
        super(message);
        this.errorType = errorType;
    }

    public ErrorType getErrorType() {
        return errorType;
    }

    public void setErrorType(ErrorType errorType) {
        this.errorType = errorType;
    }
}
