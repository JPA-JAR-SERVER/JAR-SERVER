package com.app.projectjar.exception;


import com.app.projectjar.type.ErrorCode;

public class CustomAuthenticationException extends RuntimeException{
    public CustomAuthenticationException() {
        super(ErrorCode.AUTHENTICATION_FAILED.getMessage());
    }

    public CustomAuthenticationException(String message) {
        super(message);
    }
}
