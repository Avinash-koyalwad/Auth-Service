package com.authentication.exception;

public class InvalidVerificationCodeException extends RuntimeException {
    public InvalidVerificationCodeException(String invalidVerificationCode) {
        super(invalidVerificationCode);
    }
}
