package com.cloud.order.common.exception;

public final class ErrorCode {
    public static final String ERR_PARAMETER_ORDER_MSG = "订单参数有误";
    public static final Integer ERR_PARAMETER_ORDER_CODE = 10001;

    public static final String NULL_PARAMETER_CAR_MSG = "购物车为空";
    public static final Integer NULL_PARAMETER_CAR_CODE = 10002;

    public static final String NOT_EXISTS_ORDER_MSG = "订单%s不存在";
    public static final Integer NOT_EXISTS_ORDER_CODE = 10003;

    public static final String ERROR_ORDER_STATUS_MSG = "订单%s状态有误";
    public static final Integer ERROR_ORDER_STATUS_CODE = 10004;
}
