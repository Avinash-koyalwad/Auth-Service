package com.authentication.exception;

public class AccountNotVerifiedException extends RuntimeException {
    public AccountNotVerifiedException(String s) {
        super(s);
    }
}
