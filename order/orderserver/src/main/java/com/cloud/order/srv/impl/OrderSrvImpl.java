package com.cloud.order.srv.impl;

import cloud.product.OrderInfoVo;
import cloud.product.ProductInfoVo;
import cloud.product.client.ProductClient;
import com.cloud.order.dataobject.OrderDetail;
import com.cloud.order.dataobject.OrderMaster;
import com.cloud.order.dataobject.ProductInfo;
import com.cloud.order.dataobject.dto.CartDTO;
import com.cloud.order.dataobject.dto.OrderDTO;
import com.cloud.order.repository.OrderDetailRepository;
import com.cloud.order.repository.OrderMasterRepository;
import com.cloud.order.srv.OrderSrv;
import com.cloud.order.utils.IdUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderSrvImpl implements OrderSrv {
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @Autowired(required = false)
    private ProductClient productClient;

    @Override
    public OrderDTO creat(OrderDTO orderDTO) {
        String orderId = IdUtils.getUniqueKey();
        // 查询商品信息
        List<String> productIdList = orderDTO.getOrderDetailList().stream()
                .map(OrderDetail::getProductId)
                .collect(Collectors.toList());
        List<ProductInfoVo> productInfos = productClient.getProductList(productIdList);

        // 计算总价
        BigDecimal amount = new BigDecimal(0);
        for (ProductInfoVo productInfo : productInfos) {
            for (OrderDetail orderDetail : orderDTO.getOrderDetailList()) {
                if (orderDetail.getProductId().equals(productInfo.getProductId())) {
                    amount = productInfo.getProductPrice().multiply(new BigDecimal(orderDetail.getProductQuantity())).add(amount);
                    BeanUtils.copyProperties(productInfo, orderDetail, "productQuantity");
                    orderDetail.setDetailId(IdUtils.getUniqueKey());
                    orderDetail.setOrderId(orderId);
                    orderDetailRepository.save(orderDetail);
                }
            }
        }

        // 扣库存
        List<OrderInfoVo> cartDTOS = orderDTO.getOrderDetailList()
                .stream().map(e -> new OrderInfoVo(e.getProductId(), e.getProductQuantity()))
                .collect(Collectors.toList());
        productClient.decreaseStock(cartDTOS);


        OrderMaster orderMaster = new OrderMaster();
        orderDTO.setOrderId(orderId);
        BeanUtils.copyProperties(orderDTO, orderMaster);
        orderMaster.setOrderAmount(amount);
        orderMaster.setOrderStatus(1);
        orderMaster.setPayStatus(1);

        orderMasterRepository.save(orderMaster);
        return null;
    }
}
