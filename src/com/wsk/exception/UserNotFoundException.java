package com.wsk.exception;

/**
 * 用户登录状态自定义异常
 */
public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException() {
    }

    public UserNotFoundException(String message) {
        super(message);
    }

    public UserNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
