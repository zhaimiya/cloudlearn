package cloud.product.srv.impl;

import cloud.product.dataobject.pojo.ProductCategory;
import cloud.product.repository.ProductCategoryRepository;
import cloud.product.srv.ProductCategorySrv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductCategorySrvImpl implements ProductCategorySrv {
    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Override
    public List<ProductCategory> findByCategoryTypeIn(List<Integer> CategoryTypeList) {
        return productCategoryRepository.findByCategoryTypeIn(CategoryTypeList);
    }
}
