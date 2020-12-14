package code88.oscar.bcm.services.implement;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import code88.oscar.bcm.common.CommonMethod;
import code88.oscar.bcm.model.OrderDetailModel;
import code88.oscar.bcm.repository.OrderDetailRepository;
import code88.oscar.bcm.request.OrderDetailRequest;
import code88.oscar.bcm.services.OrderDetailService;
import code88.oscar.bcm.viewObjects.OrderDetailVO;

/**
 * @FileName: OrderDetailServiceImplement.java
 * @since: 14/12/2020
 * */
@Service
public class OrderDetailServiceImplement implements OrderDetailService {

    @Autowired
    private CommonMethod commonMethod;
    
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    
    @Override
    public void saveOrderRetail(OrderDetailRequest request, String orderId) {
	try {
	    String pr = request.getPriceConvert().replace(",", "");
	    BigDecimal price = new BigDecimal(pr);
	    OrderDetailModel model = new OrderDetailModel(); 
	    model.setOrderId(orderId);
	    model.setProductId(request.getProductId());
	    model.setQuantity(request.getQuantity());
	    model.setPrice(price);
	    model.setCreateBy(request.getCreateBy());
	    model.setCreateDate(commonMethod.getDateTimeNow());
	    orderDetailRepository.save(model);
	} catch(Exception ex) {
	    
	}
	
    }

    @Override
    public List<OrderDetailVO> getListOrderDetailById(String id) {
	// TODO Auto-generated method stub
	return null;
    }


}
