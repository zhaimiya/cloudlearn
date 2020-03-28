package cloud.product.common.exception;

public class ProductException extends RuntimeException {
    private Integer code;

    public ProductException(String message, Integer code) {
        super(message);
        this.code = code;
    }
}
