package com.cloud.apigateway.exception;

public class RateLimitException extends RuntimeException {
    private  final  String RATE_LIMIT_EXCEPTION = "RATE_LIMIT";

    public RateLimitException() {
        super();
    }

    public RateLimitException(String message) {
        super(message);
    }

    public RateLimitException(String message, Throwable cause) {
        super(message, cause);
    }

}
