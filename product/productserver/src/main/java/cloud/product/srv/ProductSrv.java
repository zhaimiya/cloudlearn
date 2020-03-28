package cloud.product.srv;


import cloud.product.common.dto.CartDTO;
import cloud.product.dataobject.pojo.ProductInfo;

import java.util.List;

public interface ProductSrv {

    List<ProductInfo> findUpAll();

    List<ProductInfo> findByProductIdIn(List<String> productIds);

    void decreaseStock(List<CartDTO> cartDTOS);
}
