package com.accounts.exception;

public class CustomerAlreadyExistException extends RuntimeException {
    public CustomerAlreadyExistException(String message) {
        super(message);
    }
    public CustomerAlreadyExistException(String resourceName, String fieldName, String fieldValue) {

        super(String.format("Resource '%s' with given input data %s : '%s' is already exist", resourceName, fieldName, fieldValue));
    }
}
