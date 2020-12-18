package code88.oscar.bcm.services.implement;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import code88.oscar.bcm.common.CommonMethod;
import code88.oscar.bcm.model.OrderDetailModel;
import code88.oscar.bcm.repository.OrderDetailRepository;
import code88.oscar.bcm.request.OrderDetailRequest;
import code88.oscar.bcm.request.TransactionDetailRequest;
import code88.oscar.bcm.services.OrderDetailService;
import code88.oscar.bcm.viewObjects.OrderDetailVO;

/**
 * @FileName: OrderDetailServiceImplement.java
 * @since: 14/12/2020
 */
@Service
public class OrderDetailServiceImplement implements OrderDetailService {

    @Autowired
    private CommonMethod commonMethod;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Override
    public void saveOrderDetail(List<OrderDetailRequest> requestList, String tableId, String orderId) {
	try {
	    for (OrderDetailRequest rq : requestList) {
		String pr = rq.getPriceConvert().replace(",", "");
		BigDecimal price = new BigDecimal(pr);
		OrderDetailModel model = new OrderDetailModel();
		model.setTableId(tableId);
		model.setOrderId(orderId);
		model.setProductId(rq.getProductId());
		model.setQuantity(rq.getQuantity());
		model.setPrice(price);
		model.setCreateBy(rq.getCreateBy());
		model.setCreateDate(commonMethod.getDateTimeNow());
		orderDetailRepository.save(model);
	    }
	} catch (Exception ex) {

	}

    }

    /**
     * @Function: executeSaveOrderDetail(...)
     * @param: OrderDetailModel-object
     * */
    void executeSaveOrderDetail(OrderDetailModel model) {
	OrderDetailModel m = new OrderDetailModel();
	orderDetailRepository.insertOrderDetail(m.getOrderId(), m.getTableId(), m.getProductId(), m.getQuantity(),
		m.getPrice(), m.getCreateBy(), m.getCreateDate());
    }

    @Override
    public List<OrderDetailVO> getListOrderDetail(TransactionDetailRequest request) {
	List<OrderDetailModel> listModel = orderDetailRepository.getListOrderDetail(request.getTransactionId(), request.getOrderId(), request.getTableId());
	List<OrderDetailVO> listVO = mappingListDetailVO(listModel);
	return listVO;
    }

    /**
     * @Function: mappingListDetailVO(...)
     * @param: List<OrderDetailModel> listModel
     * */
    List<OrderDetailVO> mappingListDetailVO (List<OrderDetailModel> listModel){
	List<OrderDetailVO> listVO = new ArrayList<>();
	for(OrderDetailModel model : listModel) {
	    OrderDetailVO vo = new OrderDetailVO();
	    vo.setOrderId(model.getOrderId());
	    vo.setProductName(model.getProductId());
	    vo.setQuantity(model.getQuantity());
	    vo.setPrice(commonMethod.convertCurrencyToString(model.getPrice()));
	    vo.setCreateBy(model.getCreateBy());
	    vo.setCreateDate(commonMethod.convertDateTimeToString(model.getCreateDate()));
	    listVO.add(vo);
	}
	return listVO;
    }
}
