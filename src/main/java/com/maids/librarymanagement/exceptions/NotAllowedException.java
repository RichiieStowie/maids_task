package com.maids.librarymanagement.exceptions;

public class NotAllowedException extends RuntimeException{
    public NotAllowedException(String message) {
        super(message);
    }
}
