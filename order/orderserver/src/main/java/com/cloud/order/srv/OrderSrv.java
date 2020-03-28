package com.cloud.order.srv;

import com.cloud.order.dataobject.dto.OrderDTO;

public interface OrderSrv {
    public OrderDTO creat(OrderDTO orderDTO);
}
