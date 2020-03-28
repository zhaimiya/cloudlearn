package com.cloud.order.common;

import org.omg.PortableInterceptor.INACTIVE;

public final class Constant {
    public static final Integer ORDER_STATUS_NEW = 0;
    public static final Integer ORDER_STATUS_OLD = 1;
    public static final Integer ORDER_STATUS_CANCEL = 2;
    public static final Integer SUCCESS_CODE = 200;
    public static final String SUCCESS_MSG = "成功!";

    public static final String ERR_PARAMETER_ORDER_MSG = "订单参数有误";
    public static final Integer ERR_PARAMETER_ORDER_CODE = 10001;

    public static final String NULL_PARAMETER_CAR_MSG = "购物车为空";
    public static final Integer NULL_PARAMETER_CAR_CODE = 20001;


    public static final String SEND_MSG = "sendmsg";

    public static final String RECEIVED_MSG = "receivedmsg";

}
