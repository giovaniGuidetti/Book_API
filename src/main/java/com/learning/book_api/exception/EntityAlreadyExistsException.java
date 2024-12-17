package com.learning.book_api.exception;

public class EntityAlreadyExistsException extends RuntimeException{

    public EntityAlreadyExistsException(String message) {
        super(message);
    }

}
