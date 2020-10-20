package com.senla.carservice.security.exceptions;

public class MalformedLoginDataException extends RuntimeException {
    public MalformedLoginDataException(String message) {
        super(message);
    }
}
