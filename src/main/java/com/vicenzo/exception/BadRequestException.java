package com.vicenzo.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class BadRequestException extends Exception {
    public BadRequestException(String message) {
        super(message);
    }
    public BadRequestException() {
        super();
    }
}