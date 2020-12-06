package code88.oscar.bcm.services;

import java.util.List;

import code88.oscar.bcm.viewObjects.ProductLogVO;

/**
 * @FileName: ProductLogService.java
 * @since: 28/10/2020
 * */
public interface ProductLogService {
    
    List<ProductLogVO> getProductLog();
}
