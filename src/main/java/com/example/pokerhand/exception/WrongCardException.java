package com.example.pokerhand.exception;

public class WrongCardException extends RuntimeException {
    public WrongCardException(String message) {
        super(message);
    }
}
