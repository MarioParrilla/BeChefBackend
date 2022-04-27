package com.mp.bechefbackend.bechefbackend.Exceptions;

public class ApiErrorMessage{

    private String error;

    public ApiErrorMessage(String message) {
        this.error = message;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return "ApiErrorMessage{" +
                "message='" + error + '\'' +
                '}';
    }
}
