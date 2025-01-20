package com.bridgelabz.book_store.exception;

public class UnauthorizedAccessException extends RuntimeException {

    public UnauthorizedAccessException(String message){

        super(message);
    }

}
