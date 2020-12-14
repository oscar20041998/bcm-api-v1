package code88.oscar.bcm.services;

import java.util.List;

import code88.oscar.bcm.request.OrderDetailRequest;
import code88.oscar.bcm.viewObjects.OrderDetailVO;

/**
 * @FileName: OrderDetailService.java
 * @since: 14/12/2020
 * */
public interface OrderDetailService {

    void saveOrderRetail(OrderDetailRequest request, String orderId);
    
    List<OrderDetailVO> getListOrderDetailById(String id);
}
