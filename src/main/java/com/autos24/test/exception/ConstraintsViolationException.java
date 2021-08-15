package com.autos24.test.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "Some constraints are violated.")
public class ConstraintsViolationException extends Exception {

    public ConstraintsViolationException(String message) {
        super(message);
    }
}
