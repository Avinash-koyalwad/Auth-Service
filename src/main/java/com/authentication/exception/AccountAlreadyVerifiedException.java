package com.authentication.exception;

public class AccountAlreadyVerifiedException extends RuntimeException {
    public AccountAlreadyVerifiedException(String accountIsAlreadyVerified) {
        super(accountIsAlreadyVerified);
    }
}
