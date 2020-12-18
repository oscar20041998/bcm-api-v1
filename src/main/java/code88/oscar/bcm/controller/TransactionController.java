package code88.oscar.bcm.controller;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import code88.oscar.bcm.common.PaymentTypeCommon;
import code88.oscar.bcm.common.StatusCommon;
import code88.oscar.bcm.request.SaveTransactionRequest;
import code88.oscar.bcm.request.TransactionDetailRequest;
import code88.oscar.bcm.services.AccountUserService;
import code88.oscar.bcm.services.OrderDetailService;
import code88.oscar.bcm.services.TransactionService;
import code88.oscar.bcm.viewObjects.CountPaymentTypeVO;
import code88.oscar.bcm.viewObjects.OrderDetailVO;
import code88.oscar.bcm.viewObjects.TransactionDetailVO;
import code88.oscar.bcm.viewObjects.TransactionVO;

/**
 * @FileName: TransactionController.java
 * @since: 07/11/2020
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/transaction/api")
public class TransactionController {

    private static final Logger LOGGER = LogManager.getLogger(TransactionController.class);

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private OrderDetailService orderDetailService;

    @Autowired
    private AccountUserService accountUserService;

    @Autowired
    private CommonMethod commonMethod;

    @RequestMapping(value = "/save-transaction/{accountUserValid}", method = RequestMethod.POST)
    public ResponseEntity<String> saveTransaction(@PathVariable("accountUserValid") String accountUserValid,
	    @RequestBody SaveTransactionRequest request) {
	LOGGER.log(Level.INFO, MessageCommon.LINE);
	LOGGER.log(Level.INFO, MessageCommon.START_SAVE_TRANSACTION);
	String userName = accountUserService.getUserNameByAccountId(accountUserValid);
	try {
	    boolean isAdmin = accountUserService.isAdminRole(accountUserValid);
	    boolean isManager = accountUserService.isMangerRole(accountUserValid);
	    boolean isStaff = accountUserService.isStaffRole(accountUserValid);
	    if (isAdmin == true || isManager == true || isStaff == true) {
		transactionService.saveTransaction(request);
		commonMethod.insertSystemLog(userName, ActionCommon.EXECUTE_TRANSACTION + " " + request.getTotalPrice(),
			StatusCommon.SUCCESS);
		LOGGER.log(Level.INFO, MessageCommon.SAVE_TRANSACTION_SUCCESS);
		LOGGER.log(Level.INFO, MessageCommon.LINE);
		return new ResponseEntity<String>(MessageCommon.SAVE_TRANSACTION_SUCCESS, HttpStatus.OK);
	    } else {
		commonMethod.insertSystemLog(userName, ActionCommon.EXECUTE_TRANSACTION + " " + request.getTotalPrice(),
			StatusCommon.FAILED);
		LOGGER.log(Level.INFO, MessageCommon.SAVE_TRANSACTION_FAILED);
		LOGGER.log(Level.INFO, MessageCommon.NOT_HAVE_PERMISSION);
		LOGGER.log(Level.INFO, MessageCommon.LINE);
		return new ResponseEntity<String>(MessageCommon.SAVE_TRANSACTION_FAILED, HttpStatus.UNAUTHORIZED);
	    }
	} catch (Exception ex) {
	    commonMethod.insertSystemLog(userName, ActionCommon.EXECUTE_TRANSACTION + " " + request.getTotalPrice(),
		    StatusCommon.FAILED);
	    LOGGER.log(Level.INFO, MessageCommon.SAVE_TRANSACTION_FAILED);
	    LOGGER.log(Level.ERROR, ex.getMessage());
	    return new ResponseEntity<String>(MessageCommon.SAVE_TRANSACTION_FAILED, HttpStatus.INTERNAL_SERVER_ERROR);
	}
    }

    @RequestMapping(value = "/get-transactions/{accountUserValid}", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> getTransactions(
	    @PathVariable("accountUserValid") String accountUserValid) {
	LOGGER.log(Level.INFO, MessageCommon.LINE);
	LOGGER.log(Level.INFO, MessageCommon.START_GET_TRANSACTIONS);
	Map<String, Object> map = new HashMap<>();
	List<TransactionVO> lists = new ArrayList<>();
	CountPaymentTypeVO paymentVO = new CountPaymentTypeVO();
	String totalPrice = "";
	try {
	    boolean isAdmin = accountUserService.isAdminRole(accountUserValid);
	    boolean isManager = accountUserService.isMangerRole(accountUserValid);
	    if (isAdmin == true || isManager == true) {
		lists = transactionService.getTransactionsToday();
		totalPrice = commonMethod.convertCurrencyToString(getTotalPriceTransaction(lists));
		paymentVO = countPaymentType(lists);
		map.put("listTransactions", lists);
		map.put("totalPrice", totalPrice);
		map.put("sumPaymentType", paymentVO);
		map.put("totalTransaction", lists.size());
		LOGGER.log(Level.INFO, MessageCommon.GET_TRANSACTIONS_SUCCESS);
		LOGGER.log(Level.INFO, MessageCommon.LINE);
		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
	    } else {
		LOGGER.log(Level.INFO, MessageCommon.GET_TRANSACTIONS_FAILED);
		LOGGER.log(Level.INFO, MessageCommon.NOT_HAVE_PERMISSION);
		LOGGER.log(Level.INFO, MessageCommon.LINE);
		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.UNAUTHORIZED);
	    }
	} catch (Exception ex) {
	    LOGGER.log(Level.INFO, MessageCommon.GET_TRANSACTIONS_FAILED);
	    LOGGER.log(Level.ERROR, ex.getMessage());
	    return new ResponseEntity<Map<String, Object>>(map, HttpStatus.INTERNAL_SERVER_ERROR);
	}
    }

    @RequestMapping(value = "/search-transactions/{accountUserValid}/{criteria}", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> searchTransactions(
	    @PathVariable("accountUserValid") String accountUserValid, @PathVariable("criteria") String criteria) {
	LOGGER.log(Level.INFO, MessageCommon.LINE);
	LOGGER.log(Level.INFO, MessageCommon.START_GET_TRANSACTIONS);
	String userName = accountUserService.getUserNameByAccountId(accountUserValid);
	Map<String, Object> map = new HashMap<>();
	List<TransactionVO> lists = new ArrayList<>();
	String totalPrice = "";
	CountPaymentTypeVO paymentVO = new CountPaymentTypeVO();
	try {
	    boolean isAdmin = accountUserService.isAdminRole(accountUserValid);
	    boolean isManager = accountUserService.isMangerRole(accountUserValid);
	    if (isAdmin == true || isManager == true) {
		if (criteria.equals("TODAY")) {
		    lists = transactionService.getTransactionsToday();
		    totalPrice = commonMethod.convertCurrencyToString(getTotalPriceTransaction(lists));
		    paymentVO = countPaymentType(lists);
		    map.put("listTransactions", lists);
		    map.put("totalPrice", totalPrice);
		    map.put("sumPaymentType", paymentVO);
		    map.put("totalTransaction", lists.size());
		    commonMethod.insertSystemLog(userName, ActionCommon.SHOW_TRANSACTION_TODAY, StatusCommon.SUCCESS);
		    LOGGER.log(Level.INFO, MessageCommon.GET_TRANSACTIONS_SUCCESS);
		    LOGGER.log(Level.INFO, MessageCommon.LINE);
		    return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		} else if (criteria.equals("YESTERDAY")) {
		    lists = transactionService.getTransactionsYesterday();
		    totalPrice = commonMethod.convertCurrencyToString(getTotalPriceTransaction(lists));
		    paymentVO = countPaymentType(lists);
		    map.put("listTransactions", lists);
		    map.put("totalPrice", totalPrice);
		    map.put("sumPaymentType", paymentVO);
		    map.put("totalTransaction", lists.size());
		    commonMethod.insertSystemLog(userName, ActionCommon.SHOW_TRANSACTION_YESTERDAY,
			    StatusCommon.SUCCESS);
		    LOGGER.log(Level.INFO, MessageCommon.GET_TRANSACTIONS_SUCCESS);
		    LOGGER.log(Level.INFO, MessageCommon.LINE);
		    return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		} else {
		    lists = transactionService.getAllTransactions();
		    totalPrice = commonMethod.convertCurrencyToString(getTotalPriceTransaction(lists));
		    paymentVO = countPaymentType(lists);
		    map.put("listTransactions", lists);
		    map.put("totalPrice", totalPrice);
		    map.put("sumPaymentType", paymentVO);
		    map.put("totalTransaction", lists.size());
		    commonMethod.insertSystemLog(userName, ActionCommon.SHOW_ALL_TRANSACTION, StatusCommon.SUCCESS);
		    LOGGER.log(Level.INFO, MessageCommon.GET_TRANSACTIONS_SUCCESS);
		    LOGGER.log(Level.INFO, MessageCommon.LINE);
		    return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		}
	    } else {
		commonMethod.insertSystemLog(userName, MessageCommon.GET_TRANSACTIONS_FAILED, StatusCommon.SUCCESS);
		LOGGER.log(Level.INFO, MessageCommon.GET_TRANSACTIONS_FAILED);
		LOGGER.log(Level.INFO, MessageCommon.NOT_HAVE_PERMISSION);
		LOGGER.log(Level.INFO, MessageCommon.LINE);
		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.UNAUTHORIZED);
	    }
	} catch (Exception ex) {
	    commonMethod.insertSystemLog(userName, MessageCommon.GET_TRANSACTIONS_FAILED, StatusCommon.SUCCESS);
	    LOGGER.log(Level.INFO, MessageCommon.GET_TRANSACTIONS_FAILED);
	    LOGGER.log(Level.ERROR, ex.getMessage());
	    return new ResponseEntity<Map<String, Object>>(map, HttpStatus.INTERNAL_SERVER_ERROR);
	}
    }

    @RequestMapping(value = "/search-transactions-by-date/{accountUserValid}/{date}", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> searchTransactionsByDate(
	    @PathVariable("accountUserValid") String accountUserValid, @PathVariable("date") Date date) {
	LOGGER.log(Level.INFO, MessageCommon.LINE);
	LOGGER.log(Level.INFO, MessageCommon.START_GET_TRANSACTIONS);
	Map<String, Object> map = new HashMap<>();
	List<TransactionVO> lists = new ArrayList<>();
	String totalPrice = "";
	String userName = accountUserService.getUserNameByAccountId(accountUserValid);
	CountPaymentTypeVO paymentVO = new CountPaymentTypeVO();
	try {
	    boolean isAdmin = accountUserService.isAdminRole(accountUserValid);
	    boolean isManager = accountUserService.isMangerRole(accountUserValid);
	    if (isAdmin == true || isManager == true) {
		if (date != null) {
		    lists = transactionService.getTransactionsByDate(date);
		    totalPrice = commonMethod.convertCurrencyToString(getTotalPriceTransaction(lists));
		    paymentVO = countPaymentType(lists);
		    map.put("listTransactions", lists);
		    map.put("totalPrice", totalPrice);
		    map.put("sumPaymentType", paymentVO);
		    map.put("totalTransaction", lists.size());
		    commonMethod.insertSystemLog(userName, ActionCommon.SHOW_TRANSACTION_BY_DAY, StatusCommon.SUCCESS);
		    LOGGER.log(Level.INFO, MessageCommon.GET_TRANSACTIONS_SUCCESS);
		    LOGGER.log(Level.INFO, MessageCommon.LINE);
		    return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		} else {
		    commonMethod.insertSystemLog(userName, ActionCommon.SHOW_TRANSACTION_BY_DAY, StatusCommon.FAILED);
		    LOGGER.log(Level.INFO, MessageCommon.GET_TRANSACTIONS_FAILED);
		    LOGGER.log(Level.INFO, MessageCommon.LINE);
		    return new ResponseEntity<Map<String, Object>>(map, HttpStatus.BAD_REQUEST);
		}
	    } else {
		commonMethod.insertSystemLog(userName, ActionCommon.SHOW_TRANSACTION_BY_DAY, StatusCommon.FAILED);
		LOGGER.log(Level.INFO, MessageCommon.GET_TRANSACTIONS_FAILED);
		LOGGER.log(Level.INFO, MessageCommon.NOT_HAVE_PERMISSION);
		LOGGER.log(Level.INFO, MessageCommon.LINE);
		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.UNAUTHORIZED);
	    }
	} catch (Exception ex) {
	    commonMethod.insertSystemLog(userName, ActionCommon.SHOW_TRANSACTION_BY_DAY, StatusCommon.FAILED);
	    LOGGER.log(Level.INFO, MessageCommon.GET_TRANSACTIONS_FAILED);
	    LOGGER.log(Level.ERROR, ex.getMessage());
	    return new ResponseEntity<Map<String, Object>>(map, HttpStatus.INTERNAL_SERVER_ERROR);
	}
    }

    @RequestMapping(value = "/get-transaction-detail/{accountUserValid}")
    public ResponseEntity<Map<String, Object>> getTransactionDetail(
	    @PathVariable("accountUserValid") String accountUserValid, @RequestBody TransactionDetailRequest request) {
	LOGGER.log(Level.INFO, MessageCommon.LINE);
	LOGGER.log(Level.INFO, MessageCommon.START_GET_TRANSACTION_DETAIL);
	Map<String, Object> map = new HashMap<>();
	try {
	    boolean isAdmin = accountUserService.isAdminRole(accountUserValid);
	    boolean isManager = accountUserService.isMangerRole(accountUserValid);
	    if (isAdmin == true || isManager == true) {
		TransactionDetailVO transactionDetail = transactionService.getTransactionDetail(request);
		List<OrderDetailVO> listOrdered = orderDetailService.getListOrderDetail(request);
		map.put("transactionDetail", transactionDetail);
		map.put("listOrdered", listOrdered);
		LOGGER.log(Level.INFO, MessageCommon.GET_TRANSACTION_DETAIL_SUCCESS);
		LOGGER.log(Level.INFO, MessageCommon.LINE);
		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
	    } else {
		LOGGER.log(Level.INFO, MessageCommon.GET_TRANSACTION_DETAIL_FAILED);
		LOGGER.log(Level.INFO, MessageCommon.NOT_HAVE_PERMISSION);
		LOGGER.log(Level.INFO, MessageCommon.LINE);
		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.UNAUTHORIZED);
	    }
	} catch (Exception ex) {
	    LOGGER.log(Level.INFO, MessageCommon.GET_TRANSACTIONS_FAILED);
	    LOGGER.log(Level.ERROR, ex.getMessage());
	    return new ResponseEntity<Map<String, Object>>(map, HttpStatus.INTERNAL_SERVER_ERROR);
	}

    }

    /**
     * @Function: getTotalPriceOrdered(...)
     */
    BigDecimal getTotalPriceTransaction(List<TransactionVO> listModel) {
	BigDecimal result = new BigDecimal(0);
	for (TransactionVO model : listModel) {
	    String value = model.getTotalPrice().replace(",", "");
	    BigDecimal subResult = new BigDecimal(value);
	    result = result.add(subResult);
	}
	return result;
    }

    /**
     * @Function: countPaymentType(...)
     */
    CountPaymentTypeVO countPaymentType(List<TransactionVO> listVO) {
	int card = 0;
	int cash = 0;
	int electronic = 0;
	CountPaymentTypeVO countResult = new CountPaymentTypeVO();
	for (TransactionVO vo : listVO) {
	    if (vo.getPaymentType().equals(PaymentTypeCommon.CASH_OPTION)) {
		cash++;
	    } else if (vo.getCardType().equals(PaymentTypeCommon.CARD_OPTION)) {
		card++;
	    } else {
		electronic++;
	    }
	}
	countResult.setCountCardOption(card);
	countResult.setCountCashOption(cash);
	countResult.setElectronicWalletOption(electronic);
	return countResult;
    }
}
