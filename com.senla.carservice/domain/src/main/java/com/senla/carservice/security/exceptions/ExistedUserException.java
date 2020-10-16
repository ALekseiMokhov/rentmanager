package com.senla.carservice.security.exceptions;

public class ExistedUserException extends RuntimeException {
    public ExistedUserException(String message) {
        super(message);
    }
}
