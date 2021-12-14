package com.example.restapi.librarymanagement.exception;

import java.time.LocalDateTime;


public class ApiExceptionResponse {

      private String errror;
      private String errorMessage;
      private LocalDateTime timestamp;

    public ApiExceptionResponse() {
    }

    public ApiExceptionResponse(String errror, String errorMessage, LocalDateTime timestamp) {
        this.errror = errror;
        this.errorMessage = errorMessage;
        this.timestamp = timestamp;
    }

    public String getErrror() {
        return errror;
    }

    public void setErrror(String errror) {
        this.errror = errror;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
