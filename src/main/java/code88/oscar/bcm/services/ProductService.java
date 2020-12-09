package code88.oscar.bcm.services;

import java.util.List;

import code88.oscar.bcm.model.ProductModel;
import code88.oscar.bcm.request.SaveProductRequest;
import code88.oscar.bcm.viewObjects.ProductVO;

/**
 * @FileName: ProductService.java
 * @since: 24/10/2020
 * */
public interface ProductService {

    List<ProductVO> getListProduct();
    
    ProductModel saveProduct(SaveProductRequest request);
    
    void delteProductById (String productId);
    
    List<ProductVO> getProductByCategoryId(String categoryId);
    
    List<ProductVO> getProductByProductName(String productName);
    
    ProductVO getProductById (String productId);

}
