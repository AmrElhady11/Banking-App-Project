package com.cards.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {

        super(message);
    }
    public ResourceNotFoundException(String resourceName, String fieldName, String fieldValue) {

        super(String.format("Resource '%s' does not found with given input data %s : '%s'", resourceName, fieldName, fieldValue));
    }
}
