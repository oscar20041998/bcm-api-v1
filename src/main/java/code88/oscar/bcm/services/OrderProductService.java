package code88.oscar.bcm.services;

import java.util.List;

import code88.oscar.bcm.request.OrderProductRequest;
import code88.oscar.bcm.viewObjects.OrderProductPendingVO;
import code88.oscar.bcm.viewObjects.OrderProductVO;

/**
 * @FileName: OrderProductService.java
 * @since: 29/10/2020
 * */
public interface OrderProductService {

    String saveOrder (OrderProductRequest request);
    
    OrderProductVO getListOrderProductByTable (String tableId, String createBy);
    
    String descreaseOrderProduct (String positionId, String productId);
    
    String deleteOrderProduct (String positionId, String productId);
    
    List<OrderProductPendingVO> getListOrderPending();
    
    String updateStatusProductPending(String positionId, String productId);
}
