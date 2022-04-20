package com.productblog.exception;

public class CustomerAlreadyExist extends RuntimeException {

    public CustomerAlreadyExist(String message) {
        super(message);
    }
}
