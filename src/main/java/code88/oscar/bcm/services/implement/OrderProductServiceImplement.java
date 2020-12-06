package code88.oscar.bcm.services.implement;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import code88.oscar.bcm.common.CommonMethod;
import code88.oscar.bcm.common.StatusCommon;
import code88.oscar.bcm.model.OrderProductModel;
import code88.oscar.bcm.repository.OrderProductRepository;
import code88.oscar.bcm.repository.PositionRepository;
import code88.oscar.bcm.request.OrderProductRequest;
import code88.oscar.bcm.services.OrderProductService;
import code88.oscar.bcm.viewObjects.OrderProductPendingVO;
import code88.oscar.bcm.viewObjects.OrderProductVO;
import code88.oscar.bcm.viewObjects.OrderedVO;

/**
 * @FileName: OrderProductServiceImplement.java
 * @since: 29/10/2020
 */
@Service
public class OrderProductServiceImplement implements OrderProductService {

    @Autowired
    private OrderProductRepository orderProductRepository;

    @Autowired
    private PositionRepository positionRepository;

    @Autowired
    private CommonMethod commonMethod;

    @PersistenceContext
    private EntityManager entityManager;

    public static final String sql_countOrderProductExist = "" + " SELECT quantity"
	    + " FROM order_product WHERE position_id = :pPositionId AND product_id = :pProductId";

    @Override
    public String saveOrder(OrderProductRequest request) {
	String message = StatusCommon.FAILED;
	String positionId = request.getTableId();
	String productId = request.getProductId();
	int quantity = request.getQuantity();
	BigDecimal price = request.getPrice();
	String statusProduct = StatusCommon.PENDING;
	String createBy = request.getCreateBy();
	LocalDateTime createDate = commonMethod.getDateTimeNow();
	try {
	    int isExist = isExistOrder(request.getTableId(), request.getProductId());
	    if (isExist < 1) {
		orderProductRepository.insertNewOrderProduct(positionId, productId, quantity, price, statusProduct,
			createBy, createDate);
		message = StatusCommon.SUCCESS;
	    } else {
		orderProductRepository.increaseOrderProductExist(positionId, productId);
		message = StatusCommon.SUCCESS;

	    }
	} catch (Exception ex) {
	    System.err.print(ex.getMessage());
	}
	return message;
    }

    @Override
    public OrderProductVO getListOrderProductByTable(String tableId) {
	OrderProductVO vo = new OrderProductVO();
	List<OrderProductModel> orderProductModel = orderProductRepository.getListOrderByTable(tableId);
	if (orderProductModel.isEmpty()) {
	    positionRepository.closeTableById(tableId);
	} else {
	    vo.setTableName(getTableOrdered(orderProductModel));
	    vo.setListOrder(mappingProductOrderd(orderProductModel));
	    vo.setTotalPrice(commonMethod.convertCurrencyToString(getTotalPriceOrdered(orderProductModel)));
	}
	return vo;

    }

    /**
     * @Function: mappingProductOrderd(...)
     */
    List<OrderedVO> mappingProductOrderd(List<OrderProductModel> listModel) {
	List<OrderedVO> listVO = new ArrayList<>();
	for (OrderProductModel model : listModel) {
	    OrderedVO vo = new OrderedVO();
	    vo.setProductId(model.getProductId());
	    vo.setProductName(model.getProductName());
	    vo.setQuantity(model.getQuantity());
	    vo.setPriceConvert(commonMethod.convertCurrencyToString(model.getPrice()));
	    vo.setStatusProdct(model.getStatusProduct());
	    listVO.add(vo);
	}
	return listVO;
    }

    /**
     * @Function: getTableOrdered(...)
     */
    String getTableOrdered(List<OrderProductModel> listModel) {
	String table = "";
	List<String> listTable = null;
	for (OrderProductModel model : listModel) {
	    listTable = Arrays.asList(model.getPositionId());
	}
	table = listTable.get(0);
	return table;
    }

    /**
     * @Function: getTotalPriceOrdered(...)
     */
    BigDecimal getTotalPriceOrdered(List<OrderProductModel> listModel) {
	BigDecimal result = new BigDecimal(0);
	for (OrderProductModel model : listModel) {
	    result = result.add(model.getPrice());
	}
	return result;
    }

    int isExistOrder(String positionId, String productId) {
	int result = 0;
	try {
	    Query query = entityManager.createNativeQuery(sql_countOrderProductExist);
	    query.setParameter("pPositionId", positionId);
	    query.setParameter("pProductId", productId);
	    result = ((Number) query.getSingleResult()).intValue();
	} catch (Exception ex) {

	}
	return result;
    }

    @Override
    public String descreaseOrderProduct(String positionId, String productId) {
	String message = StatusCommon.FAILED;
	try {
	    int isExist = isExistOrder(positionId, productId);
	    if (isExist > 1) {
		orderProductRepository.decreaseOrderProductExist(positionId, productId);
		message = StatusCommon.SUCCESS;
		return message;
	    } else {
		orderProductRepository.deleteOrderProduct(positionId, productId);
		message = StatusCommon.SUCCESS;
	    }

	} catch (Exception ex) {
	    System.err.print(ex.getMessage());
	}
	return message;
    }

    @Override
    public String deleteOrderProduct(String positionId, String productId) {
	String message = StatusCommon.FAILED;
	try {
	    int isExist = isExistOrder(positionId, productId);
	    if (isExist >= 1) {
		orderProductRepository.deleteOrderProduct(positionId, productId);
		message = StatusCommon.SUCCESS;
		return message;
	    }
	} catch (Exception ex) {
	    System.err.print(ex.getMessage());
	}
	return message;
    }

    @Override
    public List<OrderProductPendingVO> getListOrderPending() {
	List<OrderProductModel> listModel = orderProductRepository.getListOrderPending();
	List<OrderProductPendingVO> listVO = mappingListPeding(listModel);
	return listVO;
    }

    @Override
    public String updateStatusProductPending(String positionId, String productId) {
	String message = "";
	try {
	    orderProductRepository.updateStatusProductPending(positionId, productId);
	    message = StatusCommon.SUCCESS;
	}catch(Exception ex) {
	    System.err.print(ex.getMessage());
	    message = StatusCommon.FAILED;
	}
	return message;
    }
    
    /**
     * @Function: mappingListPeding(...)
     * @param: List<OrderProductModel> - List
     * */
    List<OrderProductPendingVO> mappingListPeding (List<OrderProductModel> listModel){
	List<OrderProductPendingVO> listVO = new ArrayList<>();
	for(OrderProductModel model : listModel) {
	    OrderProductPendingVO vo = new OrderProductPendingVO();
	    vo.setTableId(model.getPositionId());
	    vo.setProductId(model.getProductId());
	    vo.setProductName(model.getProductName());
	    vo.setStatus(model.getStatusProduct());
	    vo.setQuantity(model.getQuantity());
	    vo.setCreateBy(model.getCreateBy());
	    vo.setCreateDate(commonMethod.convertDateTimeToString(model.getCreateDate()));
	    listVO.add(vo);
	}
	return listVO;
    }
}
