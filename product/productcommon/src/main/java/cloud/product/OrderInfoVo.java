package cloud.product;

import lombok.Data;

@Data
public class OrderInfoVo {

    private String productId;

    private Integer productQuantity;

    public OrderInfoVo() {
    }

    public OrderInfoVo(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}
