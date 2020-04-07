package com.cloud.order.controller;

import com.cloud.order.common.Constant;
import com.cloud.order.common.exception.ErrorCode;
import com.cloud.order.common.exception.OrderException;
import com.cloud.order.dataobject.OrderDetail;
import com.cloud.order.dataobject.dto.OrderDTO;
import com.cloud.order.dataobject.vo.OrderVo;
import com.cloud.order.dataobject.vo.ResultVo;
import com.cloud.order.srv.OrderSrv;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/order")
@Slf4j
public class OrderController {

    @Autowired
    private OrderSrv orderSrv;

    @RequestMapping("/create")
    public ResultVo create(@Valid OrderVo orderVo, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.error("【创建订单】参数不正确, orderVo={}", orderVo);
            throw new OrderException(ErrorCode.ERR_PARAMETER_ORDER_MSG, ErrorCode.ERR_PARAMETER_ORDER_CODE);
        }
        OrderDTO orderDTO = convert(orderVo);
        if(CollectionUtils.isEmpty(orderDTO.getOrderDetailList())){
            log.error("[订单为空]");
            throw new OrderException(ErrorCode.NULL_PARAMETER_CAR_MSG,ErrorCode.NULL_PARAMETER_CAR_CODE);
        }
        orderDTO= orderSrv.creat(orderDTO);
        return  ResultVo.sendSuccessResponse(orderDTO);
    }

    @RequestMapping("/finish")
    public  ResultVo finish(@RequestParam("orderId") String orderId){
        OrderDTO orderDTO = orderSrv.finish(orderId);
        return  ResultVo.sendSuccessResponse(orderDTO);
    }

    public OrderDTO convert(OrderVo orderVo) {
        OrderDTO orderDTO = new OrderDTO();
        List<OrderDetail> orderDetails = new ArrayList<>();
        Gson gson = new Gson();
        orderDetails = gson.fromJson(orderVo.getItems(), new TypeToken<List<OrderDetail>>(){}.getType());
        orderDTO.setOrderDetailList(orderDetails);
        orderDTO.setBuyerName(orderVo.getName());
        orderDTO.setBuyerAddress(orderVo.getName());
        orderDTO.setBuyerPhone(orderVo.getPhone());
        orderDTO.setBuyerOpenid(orderVo.getPhone());
        return orderDTO;
    }
}
