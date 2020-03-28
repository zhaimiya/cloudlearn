package cloud.product.client;


import cloud.product.OrderInfoVo;
import cloud.product.ProductInfoVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@FeignClient(name = "PRODUCT")
public interface ProductClient {

    @GetMapping("/hello")
    public String sayHi();

    @PostMapping("/product/listfororder")
    public List<ProductInfoVo> getProductList(List<String> productIds);

    @PostMapping("/product/decreasestock")
    public void decreaseStock(List<OrderInfoVo> cartDTOS);
}
