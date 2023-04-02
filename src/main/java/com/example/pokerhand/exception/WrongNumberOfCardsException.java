package com.example.pokerhand.exception;

public class WrongNumberOfCardsException extends RuntimeException {
    public WrongNumberOfCardsException(String message) {
        super(message);
    }
}
