package cloud.product.controller;

import cloud.product.common.dto.CartDTO;
import cloud.product.common.vo.ProductInfoVO;
import cloud.product.common.vo.ProductVo;
import cloud.product.common.vo.ResultVo;
import cloud.product.dataobject.pojo.ProductCategory;
import cloud.product.dataobject.pojo.ProductInfo;
import cloud.product.srv.ProductCategorySrv;
import cloud.product.srv.ProductSrv;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductSrv productSrv;
    @Autowired
    private ProductCategorySrv productCategorySrv;

    /**
     * 查询所有在架的商品
     *
     * @return
     */
    @GetMapping("/list")
    public ResultVo list() {

        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        List<ProductInfo> productInfos = productSrv.findUpAll();
        List<Integer> categoryTypes = productInfos.stream().map(ProductInfo::getCategoryType).collect(Collectors.toList());
        if (null == categoryTypes) {
            return new ResultVo();
        }
        List<ProductCategory> productCategories = productCategorySrv.findByCategoryTypeIn(categoryTypes);

        List<ProductVo> productVos = new ArrayList<>();
        for (ProductCategory productCategory : productCategories) {
            ProductVo productVo = new ProductVo();
            productVo.setCategoryType(productCategory.getCategoryType());
            productVo.setCategoryName(productCategory.getCategoryName());
            List<ProductInfoVO> productInfoVOS = new ArrayList<>();
            for (ProductInfo productInfo : productInfos) {
                if (productCategory.getCategoryType().equals(productInfo.getCategoryType())) {
                    ProductInfoVO productInfoVO = new ProductInfoVO();
                    BeanUtils.copyProperties(productInfo, productInfoVO);
                    productInfoVOS.add(productInfoVO);
                }
            }
            productVo.setProductInfoVOList(productInfoVOS);
            productVos.add(productVo);
        }

        return ResultVo.sendSuccessResponse(productVos);
    }


    /**
     * 获取商品列表
     */
    @PostMapping("/listfororder")
    public List<ProductInfo> listForOrder(@RequestBody List<String> productIds) {
        return productSrv.findByProductIdIn(productIds);
    }

    @PostMapping("/decreasestock")
    public void decreaseStock(@RequestBody List<CartDTO> cartDTOS) {
        productSrv.decreaseStock(cartDTOS);
    }

}
