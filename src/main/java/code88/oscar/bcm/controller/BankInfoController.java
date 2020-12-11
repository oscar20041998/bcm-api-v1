package code88.oscar.bcm.controller;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import code88.oscar.bcm.common.MessageCommon;
import code88.oscar.bcm.services.AccountUserService;
import code88.oscar.bcm.services.BankInfoService;
import code88.oscar.bcm.viewObjects.BankInfoVO;

/**
 * @FileName: BankInfoController.java
 * @since: 11/12/2020
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/api/bank-info")
public class BankInfoController {

    private static final Logger LOGGER = LogManager.getLogger(BankInfoController.class);

    @Autowired
    private BankInfoService bankInfoService;

    @Autowired
    private AccountUserService accountUserService;

    @RequestMapping(value = "/get-list-bank-info/{accountUserValid}", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> getListBankInfo(
	    @PathVariable("accountUserValid") String accountUserValid) {
	Map<String, Object> map = new HashMap<>();
	List<BankInfoVO> list = new ArrayList<>();
	LOGGER.log(Level.INFO, MessageCommon.LINE);
	LOGGER.log(Level.INFO, MessageCommon.START_GET_LIST_BANK_INFO);
	try {
	    boolean isAdmin = accountUserService.isAdminRole(accountUserValid);
	    boolean isManager = accountUserService.isMangerRole(accountUserValid);
	    if (isAdmin == true || isManager == true) {
		list = bankInfoService.getAllBanksInfo();
		map.put("banks", list);
		LOGGER.log(Level.INFO, MessageCommon.GET_LIST_BANK_SUCCESS);
		LOGGER.log(Level.INFO, MessageCommon.LINE);
		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
	    } else {
		LOGGER.log(Level.INFO, MessageCommon.GET_LIST_BANK_FAILED);
		LOGGER.log(Level.INFO, MessageCommon.NOT_HAVE_PERMISSION);
		LOGGER.log(Level.INFO, MessageCommon.LINE);
		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.UNAUTHORIZED);
	    }
	} catch (Exception ex) {
	    LOGGER.log(Level.ERROR, MessageCommon.GET_LIST_BANK_FAILED + ": " + ex.getMessage());
	    LOGGER.log(Level.ERROR, MessageCommon.LINE);
	    return new ResponseEntity<Map<String, Object>>(map, HttpStatus.INTERNAL_SERVER_ERROR);

	}
    }

    @RequestMapping(value = "/get-list-bank-active/{accountUserValid}", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> getListBankActive(
	    @PathVariable("accountUserValid") String accountUserValid) {
	Map<String, Object> map = new HashMap<>();
	List<BankInfoVO> list = new ArrayList<>();
	LOGGER.log(Level.INFO, MessageCommon.LINE);
	LOGGER.log(Level.INFO, MessageCommon.START_GET_LIST_BANK_ACTIVE);
	try {
	    boolean isAdmin = accountUserService.isAdminRole(accountUserValid);
	    boolean isManager = accountUserService.isMangerRole(accountUserValid);
	    boolean isStaff = accountUserService.isStaffRole(accountUserValid);
	    if (isAdmin == true || isManager == true || isStaff == true) {
		list = bankInfoService.getBankInfoActive();
		map.put("banks", list);
		LOGGER.log(Level.INFO, MessageCommon.GET_LIST_BANK_ACTIVE_SUCCESS);
		LOGGER.log(Level.INFO, MessageCommon.LINE);
		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
	    } else {
		LOGGER.log(Level.INFO, MessageCommon.GET_LIST_BANK_ACTIVE_FAILED);
		LOGGER.log(Level.INFO, MessageCommon.NOT_HAVE_PERMISSION);
		LOGGER.log(Level.INFO, MessageCommon.LINE);
		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.UNAUTHORIZED);
	    }
	} catch (Exception ex) {
	    LOGGER.log(Level.INFO, MessageCommon.GET_LIST_BANK_ACTIVE_FAILED + ": " + ex.getMessage());
	    LOGGER.log(Level.INFO, MessageCommon.LINE);
	    return new ResponseEntity<Map<String, Object>>(map, HttpStatus.INTERNAL_SERVER_ERROR);
	}
    }

    @RequestMapping(value ="/disable-bank/{accountUserValid}/{bankCode}", method = RequestMethod.POST)
    public ResponseEntity<String> disableBankInfoByCode(@PathVariable("accountUserValid") String accountUserValid,
	    @PathVariable("bankCode") String bankCode) {
	LOGGER.log(Level.INFO, MessageCommon.LINE);
	LOGGER.log(Level.INFO, MessageCommon.START_DISABLED_BANK_INFO);
	try {
	    boolean isAdmin = accountUserService.isAdminRole(accountUserValid);
	    boolean isManager = accountUserService.isMangerRole(accountUserValid);
	    if (isAdmin == true || isManager == true) {
		bankInfoService.disableBankInfoByCode(bankCode);
		LOGGER.log(Level.INFO, MessageCommon.DISABLE_BANK_SUCCESS);
		LOGGER.log(Level.INFO, MessageCommon.LINE);
		return new ResponseEntity<String>(MessageCommon.DISABLE_BANK_SUCCESS, HttpStatus.OK);
	    } else {
		LOGGER.log(Level.INFO, MessageCommon.DISABLE_BANK_FAILED);
		LOGGER.log(Level.INFO, MessageCommon.NOT_HAVE_PERMISSION);
		LOGGER.log(Level.INFO, MessageCommon.LINE);
		return new ResponseEntity<String>(MessageCommon.DISABLE_BANK_FAILED, HttpStatus.UNAUTHORIZED);
	    }
	}catch (Exception ex) {
	    LOGGER.log(Level.INFO, MessageCommon.DISABLE_BANK_FAILED + ": " + ex.getMessage());
	    LOGGER.log(Level.INFO, MessageCommon.LINE);
	    return new ResponseEntity<String>(MessageCommon.DISABLE_BANK_FAILED, HttpStatus.INTERNAL_SERVER_ERROR);
	}
    }
}
