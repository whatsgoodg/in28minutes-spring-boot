package com.in28minutes.rest.webservice.restfulwebservices.exception;

import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDate;

public class ErrorDetails {
    private LocalDate timestamp;
    private String message;
    private String details;

    public ErrorDetails(LocalDate timestamp, String message, String details) {
        super();
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
    }

    public LocalDate getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

    public String getDetails() {
        return details;
    }

}
