package com.loans.exception;

public class LoanAlreadyExistException extends RuntimeException {
    public LoanAlreadyExistException(String message) {
        super(message);
    }
    public LoanAlreadyExistException(String resourceName, String fieldName, String fieldValue) {

        super(String.format("Resource '%s' with given input data %s : '%s' is already exist", resourceName, fieldName, fieldValue));
    }
}
