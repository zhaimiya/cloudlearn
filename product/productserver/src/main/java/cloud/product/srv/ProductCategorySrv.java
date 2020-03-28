package cloud.product.srv;


import cloud.product.dataobject.pojo.ProductCategory;

import java.util.List;

public interface ProductCategorySrv {
    List<ProductCategory> findByCategoryTypeIn(List<Integer> CategoryTypeList);
}
