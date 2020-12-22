package code88.oscar.bcm.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import code88.oscar.bcm.common.ActionCommon;
import code88.oscar.bcm.common.CommonMethod;
import code88.oscar.bcm.common.MessageCommon;
import code88.oscar.bcm.common.StatusCommon;
import code88.oscar.bcm.model.OrderProductModel;
import code88.oscar.bcm.repository.OrderProductRepository;
import code88.oscar.bcm.repository.PositionRepository;
import code88.oscar.bcm.request.OrderProductRequest;
import code88.oscar.bcm.services.AccountUserService;
import code88.oscar.bcm.services.OrderProductService;
import code88.oscar.bcm.viewObjects.OrderProductPendingVO;
import code88.oscar.bcm.viewObjects.OrderProductVO;

/**
 * @FileName: OrderProductController.java
 * @since: 29/10/2020
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/order-product/api")
public class OrderProductController {

    private static final Logger LOGGER = LogManager.getLogger(OrderProductController.class);

    @Autowired
    private OrderProductService orderProductService;

    @Autowired
    private AccountUserService accountUserService;

    @Autowired
    private CommonMethod commonMethod;
    
    @Autowired
    private OrderProductRepository orderProductRepository;
    
    @Autowired
    private PositionRepository positionRepository;

    @RequestMapping(value = "/save-order/{accountUserValid}", method = RequestMethod.POST)
    public ResponseEntity<String> saveOrderProduct(@RequestBody OrderProductRequest request,
	    @PathVariable("accountUserValid") String accountUserValid) {
	LOGGER.log(Level.INFO, MessageCommon.LINE);
	LOGGER.log(Level.INFO, MessageCommon.START_SAVE_ORDER);
	String userName = accountUserService.getUserNameByAccountId(accountUserValid);
	try {
	    boolean isAdmin = accountUserService.isAdminRole(accountUserValid);
	    boolean isManager = accountUserService.isMangerRole(accountUserValid);
	    boolean isStaff = accountUserService.isStaffRole(accountUserValid);
	    if (isAdmin == true || isManager == true || isStaff == true) {
		String status = orderProductService.saveOrder(request);
		if (status.equals(StatusCommon.SUCCESS)) {
		    commonMethod.insertSystemLog(userName,
			    ActionCommon.ADD_ORDER + " " + request.getProductId() + " for " + request.getTableId(),
			    StatusCommon.SUCCESS);
		    LOGGER.log(Level.INFO, MessageCommon.SAVE_ORDER_SUCCESS);
		    LOGGER.log(Level.INFO, MessageCommon.LINE);
		    return new ResponseEntity<String>(MessageCommon.SAVE_ORDER_SUCCESS, HttpStatus.OK);
		} else {
		    commonMethod.insertSystemLog(userName,
			    ActionCommon.ADD_ORDER + " " + request.getProductId() + " for " + request.getTableId(),
			    StatusCommon.FAILED);
		    LOGGER.log(Level.INFO, MessageCommon.SAVE_ORDER_FAILED);
		    LOGGER.log(Level.INFO, MessageCommon.LINE);
		    return new ResponseEntity<String>(MessageCommon.SAVE_ORDER_FAILED, HttpStatus.NO_CONTENT);
		}
	    } else {
		commonMethod.insertSystemLog(userName,
			ActionCommon.ADD_ORDER + " " + request.getProductId() + " for " + request.getTableId(),
			StatusCommon.FAILED);
		LOGGER.log(Level.INFO, MessageCommon.SAVE_ORDER_FAILED);
		LOGGER.log(Level.INFO, MessageCommon.NOT_HAVE_PERMISSION);
		LOGGER.log(Level.INFO, MessageCommon.LINE);
	    }
	} catch (Exception ex) {
	    commonMethod.insertSystemLog(userName,
		    ActionCommon.ADD_ORDER + " " + request.getProductId() + " for " + request.getTableId(),
		    StatusCommon.FAILED);
	    LOGGER.log(Level.INFO, MessageCommon.SAVE_ORDER_FAILED);
	    LOGGER.log(Level.INFO, ex.getMessage());
	    LOGGER.log(Level.INFO, MessageCommon.LINE);
	}
	return new ResponseEntity<String>(MessageCommon.SAVE_ORDER_FAILED, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @RequestMapping(value = "/decrease-order/{accountUserValid}/{positionId}/{productId}", method = RequestMethod.POST)
    public ResponseEntity<String> decreaseOrderProduct(@PathVariable("accountUserValid") String accountUserValid,
	    @PathVariable("positionId") String positionId, @PathVariable("productId") String productId) {
	LOGGER.log(Level.INFO, MessageCommon.LINE);
	LOGGER.log(Level.INFO, MessageCommon.START_SAVE_ORDER);
	String userName = accountUserService.getUserNameByAccountId(accountUserValid);
	try {
	    boolean isAdmin = accountUserService.isAdminRole(accountUserValid);
	    boolean isManager = accountUserService.isMangerRole(accountUserValid);
	    boolean isStaff = accountUserService.isStaffRole(accountUserValid);
	    if (isAdmin == true || isManager == true || isStaff == true) {
		String status = orderProductService.descreaseOrderProduct(positionId, productId);
		if (status.equals(StatusCommon.SUCCESS)) {
		    commonMethod.insertSystemLog(userName,
			    ActionCommon.DECREASE_ORDER + " " + productId + " for " + positionId, StatusCommon.SUCCESS);
		    LOGGER.log(Level.INFO, MessageCommon.SAVE_ORDER_SUCCESS);
		    LOGGER.log(Level.INFO, MessageCommon.LINE);
		    return new ResponseEntity<String>(MessageCommon.SAVE_ORDER_SUCCESS, HttpStatus.OK);
		} else {
		    commonMethod.insertSystemLog(userName,
			    ActionCommon.DECREASE_ORDER + " " + productId + " for " + positionId, StatusCommon.FAILED);
		    LOGGER.log(Level.INFO, MessageCommon.SAVE_ORDER_FAILED);
		    LOGGER.log(Level.INFO, MessageCommon.LINE);
		    return new ResponseEntity<String>(MessageCommon.SAVE_ORDER_FAILED, HttpStatus.NO_CONTENT);
		}
	    } else {
		commonMethod.insertSystemLog(userName,
			ActionCommon.DECREASE_ORDER + " " + productId + " for " + positionId, StatusCommon.FAILED);
		LOGGER.log(Level.INFO, MessageCommon.SAVE_ORDER_FAILED);
		LOGGER.log(Level.INFO, MessageCommon.NOT_HAVE_PERMISSION);
		LOGGER.log(Level.INFO, MessageCommon.LINE);
	    }
	} catch (Exception ex) {
	    commonMethod.insertSystemLog(userName, ActionCommon.DECREASE_ORDER + " " + productId + " for " + positionId,
		    StatusCommon.FAILED);
	    LOGGER.log(Level.INFO, MessageCommon.SAVE_ORDER_FAILED);
	    LOGGER.log(Level.INFO, ex.getMessage());
	    LOGGER.log(Level.INFO, MessageCommon.LINE);
	}
	return new ResponseEntity<String>(MessageCommon.SAVE_ORDER_FAILED, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @RequestMapping(value = "/delete-order/{accountUserValid}/{positionId}/{productId}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteOrderProduct(@PathVariable("accountUserValid") String accountUserValid,
	    @PathVariable("positionId") String positionId, @PathVariable("productId") String productId) {
	LOGGER.log(Level.INFO, MessageCommon.LINE);
	LOGGER.log(Level.INFO, MessageCommon.START_DELETE_ORDER);
	String userName = accountUserService.getUserNameByAccountId(accountUserValid);
	try {
	    String status = orderProductService.deleteOrderProduct(positionId, productId);
	    boolean isAdmin = accountUserService.isAdminRole(accountUserValid);
	    boolean isManager = accountUserService.isMangerRole(accountUserValid);
	    if (isAdmin == true || isManager == true) {
		if (status.equals(StatusCommon.SUCCESS)) {
		    commonMethod.insertSystemLog(userName,
			    ActionCommon.DELETE_ORDER + " " + productId + " for " + positionId, StatusCommon.SUCCESS);
		    LOGGER.log(Level.INFO, MessageCommon.DELETE_ORDER_SUCCESS);
		    LOGGER.log(Level.INFO, MessageCommon.LINE);
		    return new ResponseEntity<String>(MessageCommon.SAVE_ORDER_SUCCESS, HttpStatus.OK);
		} else {
		    commonMethod.insertSystemLog(userName,
			    ActionCommon.DELETE_ORDER + " " + productId + " for " + positionId, StatusCommon.FAILED);
		    LOGGER.log(Level.INFO, MessageCommon.DELETE_ORDER_FAILED);
		    LOGGER.log(Level.INFO, MessageCommon.LINE);
		    return new ResponseEntity<String>(MessageCommon.DELETE_ORDER_FAILED, HttpStatus.NO_CONTENT);
		}
	    } else {
		commonMethod.insertSystemLog(userName,
			ActionCommon.DELETE_ORDER + " " + productId + " for " + positionId, StatusCommon.FAILED);
		LOGGER.log(Level.INFO, MessageCommon.DELETE_ORDER_FAILED);
		LOGGER.log(Level.INFO, MessageCommon.NOT_HAVE_PERMISSION);
		LOGGER.log(Level.INFO, MessageCommon.LINE);
	    }
	} catch (Exception ex) {
	    commonMethod.insertSystemLog(userName, ActionCommon.DELETE_ORDER + " " + productId + " for " + positionId,
		    StatusCommon.FAILED);
	    LOGGER.log(Level.INFO, MessageCommon.DELETE_ORDER_FAILED);
	    LOGGER.log(Level.INFO, ex.getMessage());
	    LOGGER.log(Level.INFO, MessageCommon.LINE);
	}
	return new ResponseEntity<String>(MessageCommon.SAVE_ORDER_FAILED, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @RequestMapping(value = "/get-order-product-by-table-id/{tableId}/{accountIdValid}", method = RequestMethod.GET)
    public ResponseEntity<OrderProductVO> getOrderProductByTable(@PathVariable("tableId") String tableId,
	    @PathVariable("accountIdValid") String accountIdValid) {
	LOGGER.log(Level.INFO, MessageCommon.LINE);
	LOGGER.log(Level.INFO, MessageCommon.START_GET_ORDER_PRODUCT_BY_TABLE);
	OrderProductVO vo = new OrderProductVO();
	String userName = accountUserService.getUserNameByAccountId(accountIdValid);
	try {
	    boolean isAdmin = accountUserService.isAdminRole(accountIdValid);
	    boolean isManager = accountUserService.isMangerRole(accountIdValid);
	    boolean isStaff = accountUserService.isStaffRole(accountIdValid);
	    if (isAdmin == true || isManager == true || isStaff == true) {
		vo = orderProductService.getListOrderProductByTable(tableId, userName);
		if (vo != null) {
		    commonMethod.insertSystemLog(userName, ActionCommon.VISIT_ORDER_DETAIL + " " + tableId,
			    StatusCommon.SUCCESS);
		    LOGGER.log(Level.INFO, MessageCommon.GET_ORDER_PRODUCT_BY_TABLE_SUCCESS);
		    LOGGER.log(Level.INFO, MessageCommon.LINE);
		    return new ResponseEntity<OrderProductVO>(vo, HttpStatus.OK);
		} else {
		    commonMethod.insertSystemLog(userName, ActionCommon.VISIT_ORDER_DETAIL + " " + tableId,
			    StatusCommon.FAILED);
		    LOGGER.log(Level.INFO, MessageCommon.GET_ORDER_PRODUCT_BY_TABLE_FAILED);
		    LOGGER.log(Level.INFO, MessageCommon.LINE);
		    return new ResponseEntity<OrderProductVO>(vo, HttpStatus.OK);
		}
	    } else {
		commonMethod.insertSystemLog(userName, ActionCommon.VISIT_ORDER_DETAIL + " " + tableId,
			StatusCommon.FAILED);
		LOGGER.log(Level.INFO, MessageCommon.GET_ORDER_PRODUCT_BY_TABLE_FAILED);
		LOGGER.log(Level.INFO, MessageCommon.NOT_HAVE_PERMISSION);
		LOGGER.log(Level.INFO, MessageCommon.LINE);
		return new ResponseEntity<OrderProductVO>(vo, HttpStatus.UNAUTHORIZED);
	    }
	} catch (Exception ex) {
	    commonMethod.insertSystemLog(userName, ActionCommon.VISIT_ORDER_DETAIL + " " + tableId,
		    StatusCommon.FAILED);
	    LOGGER.log(Level.INFO, MessageCommon.GET_ORDER_PRODUCT_BY_TABLE_FAILED);
	    LOGGER.log(Level.INFO, ex.getMessage());
	    LOGGER.log(Level.INFO, MessageCommon.LINE);
	}
	return new ResponseEntity<OrderProductVO>(vo, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @RequestMapping(value = "/get-order-product-pending/{accountUserValid}", method = RequestMethod.GET)
    public ResponseEntity<List<OrderProductPendingVO>> getOrderProductPending(
	    @PathVariable("accountUserValid") String accountIdValid) {
	LOGGER.log(Level.INFO, MessageCommon.LINE);
	LOGGER.log(Level.INFO, MessageCommon.START_GET_ORDER_PRODUCT_PENDING);
	List<OrderProductPendingVO> listVO = new ArrayList<>();
	String userName = accountUserService.getUserNameByAccountId(accountIdValid);
	try {
	    boolean isAdmin = accountUserService.isAdminRole(accountIdValid);
	    boolean isManager = accountUserService.isMangerRole(accountIdValid);
	    boolean isStaff = accountUserService.isStaffRole(accountIdValid);
	    if (isAdmin == true || isManager == true || isStaff == true) {
		listVO = orderProductService.getListOrderPending();
		if (listVO != null) {
		    commonMethod.insertSystemLog(userName, ActionCommon.SHOW_ORDER_PENDING, StatusCommon.SUCCESS);
		    LOGGER.log(Level.INFO, MessageCommon.GET_ORDER_PRODUCT_PENDING_SUCCESS);
		    LOGGER.log(Level.INFO, MessageCommon.LINE);
		    return new ResponseEntity<List<OrderProductPendingVO>>(listVO, HttpStatus.OK);
		} else {
		    commonMethod.insertSystemLog(userName, ActionCommon.SHOW_ORDER_PENDING, StatusCommon.FAILED);
		    LOGGER.log(Level.INFO, MessageCommon.GET_ORDER_PRODUCT_PENDING_FAILED);
		    LOGGER.log(Level.INFO, MessageCommon.LINE);
		    return new ResponseEntity<List<OrderProductPendingVO>>(listVO, HttpStatus.OK);
		}
	    } else {
		commonMethod.insertSystemLog(userName, ActionCommon.SHOW_ORDER_PENDING, StatusCommon.FAILED);
		LOGGER.log(Level.INFO, MessageCommon.GET_ORDER_PRODUCT_PENDING_FAILED);
		LOGGER.log(Level.INFO, MessageCommon.NOT_HAVE_PERMISSION);
		LOGGER.log(Level.INFO, MessageCommon.LINE);
		return new ResponseEntity<List<OrderProductPendingVO>>(listVO, HttpStatus.UNAUTHORIZED);
	    }
	} catch (Exception ex) {
	    commonMethod.insertSystemLog(userName, ActionCommon.SHOW_ORDER_PENDING, StatusCommon.FAILED);
	    LOGGER.log(Level.INFO, MessageCommon.GET_ORDER_PRODUCT_PENDING_FAILED);
	    LOGGER.log(Level.INFO, ex.getMessage());
	    LOGGER.log(Level.INFO, MessageCommon.LINE);
	}
	return new ResponseEntity<List<OrderProductPendingVO>>(listVO, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @RequestMapping(value = "/update-order-product-pending/{accountUserValid}/{positionId}/{productId}", method = RequestMethod.POST)
    public ResponseEntity<String> updateOrderProductPending(@PathVariable("accountUserValid") String accountIdValid,
	    @PathVariable("positionId") String positionId, @PathVariable("productId") String productId) {
	LOGGER.log(Level.INFO, MessageCommon.LINE);
	LOGGER.log(Level.INFO, MessageCommon.START_GET_ORDER_PRODUCT_PENDING);
	String userName = accountUserService.getUserNameByAccountId(accountIdValid);
	try {
	    boolean isAdmin = accountUserService.isAdminRole(accountIdValid);
	    boolean isManager = accountUserService.isMangerRole(accountIdValid);
	    boolean isStaff = accountUserService.isStaffRole(accountIdValid);
	    if (isAdmin == true || isManager == true || isStaff == true) {
		String message = orderProductService.updateStatusProductPending(positionId, productId);
		if (message.equals(StatusCommon.SUCCESS)) {
		    commonMethod.insertSystemLog(userName, ActionCommon.CHECK_DONE_ORDER_PENDING + productId,
			    StatusCommon.SUCCESS);
		    LOGGER.log(Level.INFO, MessageCommon.UPDATE_PRODUCT_PENDING_SUCCESS);
		    LOGGER.log(Level.INFO, MessageCommon.LINE);
		    return new ResponseEntity<String>(MessageCommon.UPDATE_PRODUCT_PENDING_SUCCESS, HttpStatus.OK);
		} else {
		    commonMethod.insertSystemLog(userName, ActionCommon.CHECK_DONE_ORDER_PENDING + productId,
			    StatusCommon.FAILED);
		    LOGGER.log(Level.INFO, MessageCommon.UPDATE_ORDER_PRODUCT_PENDING_FAILED);
		    LOGGER.log(Level.INFO, MessageCommon.LINE);
		    return new ResponseEntity<String>(MessageCommon.UPDATE_ORDER_PRODUCT_PENDING_FAILED,
			    HttpStatus.INTERNAL_SERVER_ERROR);
		}
	    } else {
		commonMethod.insertSystemLog(userName, ActionCommon.CHECK_DONE_ORDER_PENDING + productId,
			StatusCommon.FAILED);
		LOGGER.log(Level.INFO, MessageCommon.GET_ORDER_PRODUCT_PENDING_FAILED);
		LOGGER.log(Level.INFO, MessageCommon.NOT_HAVE_PERMISSION);
		LOGGER.log(Level.INFO, MessageCommon.LINE);
		return new ResponseEntity<String>(MessageCommon.NOT_HAVE_PERMISSION, HttpStatus.UNAUTHORIZED);
	    }
	} catch (Exception ex) {
	    commonMethod.insertSystemLog(userName, ActionCommon.CHECK_DONE_ORDER_PENDING + productId,
		    StatusCommon.FAILED);
	    LOGGER.log(Level.INFO, MessageCommon.GET_ORDER_PRODUCT_PENDING_FAILED);
	    LOGGER.log(Level.INFO, ex.getMessage());
	    LOGGER.log(Level.INFO, MessageCommon.LINE);
	}
	return new ResponseEntity<String>(MessageCommon.UPDATE_ORDER_PRODUCT_PENDING_FAILED,
		HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @RequestMapping(value = "/split-order/{accountUserValid}/{currentTable}/{selectedTable}", method = RequestMethod.POST)
    public ResponseEntity<String> splitOrderProduct(@PathVariable("accountUserValid") String accountUserValid,
	    @PathVariable("currentTable") String currentTable, @PathVariable("selectedTable") String selectedTable,
	    @RequestBody List<OrderProductRequest> listRequest) {
	String userName = accountUserService.getUserNameByAccountId(accountUserValid);
	try {
	    LOGGER.log(Level.INFO, MessageCommon.LINE);
	    LOGGER.log(Level.INFO, MessageCommon.START_GET_ORDER_PRODUCT_PENDING);
	    boolean isAdmin = accountUserService.isAdminRole(accountUserValid);
	    boolean isManager = accountUserService.isMangerRole(accountUserValid);
	    boolean isStaff = accountUserService.isStaffRole(accountUserValid);
	    if (isAdmin == true || isManager == true || isStaff == true) {
		String status = "";
		for (OrderProductRequest request : listRequest) {
		    request.setTableId(selectedTable);
		    request.setCreateBy(userName);
		    status = orderProductService.saveOrder(request);
		    if (status.equals(StatusCommon.SUCCESS)) {
			orderProductService.deleteOrderProduct(currentTable, request.getProductId());
		    }
		}
		commonMethod.insertSystemLog(userName, ActionCommon.SPLIT_PRODUCT + " " + currentTable,
			StatusCommon.SUCCESS);
		List<OrderProductModel> checkList = orderProductRepository.getListOrderByTable(currentTable);
		if(checkList.size() == 0) {
		    positionRepository.closeTableById(currentTable,userName);
		}
		return new ResponseEntity<String>(status, HttpStatus.OK);
	    } else {
		commonMethod.insertSystemLog(userName, ActionCommon.SPLIT_PRODUCT + " " + currentTable,
			StatusCommon.FAILED);
		LOGGER.log(Level.INFO, MessageCommon.GET_ORDER_PRODUCT_PENDING_FAILED);
		LOGGER.log(Level.INFO, MessageCommon.NOT_HAVE_PERMISSION);
		LOGGER.log(Level.INFO, MessageCommon.LINE);
		return new ResponseEntity<String>(MessageCommon.NOT_HAVE_PERMISSION, HttpStatus.UNAUTHORIZED);
	    }
	} catch (Exception ex) {
	    commonMethod.insertSystemLog(userName, ActionCommon.SPLIT_PRODUCT + " " + currentTable,
		    StatusCommon.FAILED);
	    LOGGER.log(Level.INFO, MessageCommon.SPLIT_ORDER_PRODUCT_FAILED);
	    LOGGER.log(Level.INFO, ex.getMessage());
	    LOGGER.log(Level.INFO, MessageCommon.LINE);
	    return new ResponseEntity<String>(MessageCommon.SPLIT_ORDER_PRODUCT_FAILED,
		    HttpStatus.INTERNAL_SERVER_ERROR);
	}
    }
}
