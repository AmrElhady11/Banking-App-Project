package com.cards.exception;

public class CardAlreadyExistException extends RuntimeException {
    public CardAlreadyExistException(String message) {
        super(message);
    }
    public CardAlreadyExistException(String resourceName, String fieldName, String fieldValue) {

        super(String.format("Resource '%s' with given input data %s : '%s' is already exist", resourceName, fieldName, fieldValue));
    }
}
