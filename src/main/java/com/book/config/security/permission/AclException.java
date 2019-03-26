package com.book.config.security.permission;

public class AclException extends Exception {

    public AclException(){}

    public AclException(String message) {
        super(message);
    }
}
