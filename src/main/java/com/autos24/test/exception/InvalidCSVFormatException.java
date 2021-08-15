package com.autos24.test.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "The CSV file used has an invalid format.")
public class InvalidCSVFormatException extends Exception {

    public InvalidCSVFormatException(String message) {
        super(message);
    }
}
