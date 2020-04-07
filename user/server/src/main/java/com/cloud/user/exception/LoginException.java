package com.cloud.user.exception;

public class LoginException extends  RuntimeException {

    private  Integer code;

    public LoginException() {
        super();
    }

    public LoginException(String message,Integer code) {
        super(message);
        this.code = code;
    }


}
