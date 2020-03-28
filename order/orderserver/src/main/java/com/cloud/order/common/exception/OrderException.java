package com.cloud.order.common.exception;

public class OrderException extends RuntimeException {
    private Integer code;

    public OrderException(String message, Integer code) {
        super(message);
        this.code = code;
    }
}
