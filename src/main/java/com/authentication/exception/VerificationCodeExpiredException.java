package com.authentication.exception;

public class VerificationCodeExpiredException extends RuntimeException {
    public VerificationCodeExpiredException(String verificationCodeHasExpired) {
        super(verificationCodeHasExpired);
    }
}
