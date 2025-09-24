package com.example.accounts.exception;

public class CustomerAlreadyExistsException extends RuntimeException{
    public CustomerAlreadyExistsException(String message) {
        super(message);
    }
}

