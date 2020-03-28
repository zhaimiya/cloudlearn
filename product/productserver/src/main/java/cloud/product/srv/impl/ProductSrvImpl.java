package cloud.product.srv.impl;


import cloud.product.common.Constant;
import cloud.product.common.dto.CartDTO;
import cloud.product.common.exception.ProductException;
import cloud.product.dataobject.pojo.ProductInfo;
import cloud.product.repository.ProductInfoRepository;
import cloud.product.srv.ProductSrv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductSrvImpl implements ProductSrv {
    @Autowired
    private ProductInfoRepository productInfoRepository;


    @Override
    public List<ProductInfo> findUpAll() {
        List<ProductInfo> productInfos = productInfoRepository.findByProductStatus(Constant.PRODUCT_UP);
        return productInfos;
    }

    @Override
    public List<ProductInfo> findByProductIdIn(List<String> productIds) {
        return productInfoRepository.findByProductIdIn(productIds);
    }

    @Override
    @Transactional
    public void decreaseStock(List<CartDTO> cartDTOS) {
        for (CartDTO inst : cartDTOS) {
            Optional<ProductInfo> productInfoOptional = productInfoRepository.findById(inst.getProductId());
            if (null == productInfoOptional) {
                throw new ProductException(Constant.PRODUCT_NOT_EXISTS_MSG, Constant.PRODUCT_NOT_EXISTS_CODE);
            }

            ProductInfo productInfo = productInfoOptional.get();
            Integer result = productInfo.getProductStock() - inst.getProductQuantity();
            if (result < 0) {
                throw new ProductException(Constant.PRODUCT_NOT_ENOUGH_MSG, Constant.PRODUCT_NOT_ENOUGH_CODE);
            }
            productInfo.setProductStock(result);
            productInfoRepository.save(productInfo);
        }
    }
}
