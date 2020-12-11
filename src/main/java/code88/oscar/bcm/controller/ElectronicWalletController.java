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
import code88.oscar.bcm.services.ElectronicWalletService;
import code88.oscar.bcm.viewObjects.ElectronicWalletVO;

/**
 * @FileName: ElectronicWalletConller.java
 * @since: 11/12/2020
 * */
@CrossOrigin(origins ="*")
@RestController
@RequestMapping("/api/electronic-wallet")
public class ElectronicWalletController {

    private static final Logger LOGGER = LogManager.getLogger(ElectronicWalletController.class);

    @Autowired
    private ElectronicWalletService electronicWalletService;

    @Autowired
    private AccountUserService accountUserService;

    @RequestMapping(value = "/get-list-ewallet-info/{accountUserValid}", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> getListBankInfo(
	    @PathVariable("accountUserValid") String accountUserValid) {
	Map<String, Object> map = new HashMap<>();
	List<ElectronicWalletVO> list = new ArrayList<>();
	LOGGER.log(Level.INFO, MessageCommon.LINE);
	LOGGER.log(Level.INFO, MessageCommon.START_GET_LIST_EWALLET_INFO);
	try {
	    boolean isAdmin = accountUserService.isAdminRole(accountUserValid);
	    boolean isManager = accountUserService.isMangerRole(accountUserValid);
	    if (isAdmin == true || isManager == true) {
		list = electronicWalletService.getListWallet();
		map.put("wallets", list);
		LOGGER.log(Level.INFO, MessageCommon.GET_LIST_EWALLET_SUCCESS);
		LOGGER.log(Level.INFO, MessageCommon.LINE);
		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
	    } else {
		LOGGER.log(Level.INFO, MessageCommon.GET_LIST_EWALLET_FAILED);
		LOGGER.log(Level.INFO, MessageCommon.NOT_HAVE_PERMISSION);
		LOGGER.log(Level.INFO, MessageCommon.LINE);
		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.UNAUTHORIZED);
	    }
	} catch (Exception ex) {
	    LOGGER.log(Level.INFO, MessageCommon.GET_LIST_EWALLET_FAILED + ": " + ex.getMessage());
	    LOGGER.log(Level.INFO, MessageCommon.LINE);
	    return new ResponseEntity<Map<String, Object>>(map, HttpStatus.INTERNAL_SERVER_ERROR);

	}
    }

    @RequestMapping(value = "/get-list-ewallet-active/{accountUserValid}", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> getListBankActive(
	    @PathVariable("accountUserValid") String accountUserValid) {
	Map<String, Object> map = new HashMap<>();
	List<ElectronicWalletVO> list = new ArrayList<>();
	LOGGER.log(Level.INFO, MessageCommon.LINE);
	LOGGER.log(Level.INFO, MessageCommon.START_GET_EWALLET_BANK_ACTIVE);
	try {
	    boolean isAdmin = accountUserService.isAdminRole(accountUserValid);
	    boolean isManager = accountUserService.isMangerRole(accountUserValid);
	    boolean isStaff = accountUserService.isStaffRole(accountUserValid);
	    if (isAdmin == true || isManager == true || isStaff == true) {
		list = electronicWalletService.getListWalletActive();
		map.put("wallets", list);
		LOGGER.log(Level.INFO, MessageCommon.GET_LIST_EWALLET_ACTIVE_SUCCESS);
		LOGGER.log(Level.INFO, MessageCommon.LINE);
		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
	    } else {
		LOGGER.log(Level.INFO, MessageCommon.GET_LIST_EWALLET_ACTIVE_FAILED);
		LOGGER.log(Level.INFO, MessageCommon.NOT_HAVE_PERMISSION);
		LOGGER.log(Level.INFO, MessageCommon.LINE);
		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.UNAUTHORIZED);
	    }
	} catch (Exception ex) {
	    LOGGER.log(Level.INFO, MessageCommon.GET_LIST_EWALLET_ACTIVE_FAILED + ": " + ex.getMessage());
	    LOGGER.log(Level.INFO, MessageCommon.LINE);
	    return new ResponseEntity<Map<String, Object>>(map, HttpStatus.INTERNAL_SERVER_ERROR);
	}
    }

    @RequestMapping(value ="/disable-ewallet/{accountUserValid}/{id}", method = RequestMethod.POST)
    public ResponseEntity<String> disableBankInfoByCode(@PathVariable("accountUserValid") String accountUserValid,
	    @PathVariable("id") int id) {
	LOGGER.log(Level.INFO, MessageCommon.LINE);
	LOGGER.log(Level.INFO, MessageCommon.START_DISABLED_EWALLET);
	try {
	    boolean isAdmin = accountUserService.isAdminRole(accountUserValid);
	    boolean isManager = accountUserService.isMangerRole(accountUserValid);
	    if (isAdmin == true || isManager == true) {
		electronicWalletService.disableElectronicWalletById(id);
		LOGGER.log(Level.INFO, MessageCommon.DISABLE_EWALLET_SUCCESS);
		LOGGER.log(Level.INFO, MessageCommon.LINE);
		return new ResponseEntity<String>(MessageCommon.DISABLE_BANK_SUCCESS, HttpStatus.OK);
	    } else {
		LOGGER.log(Level.INFO, MessageCommon.DISABLE_EWALLET_FAILED);
		LOGGER.log(Level.INFO, MessageCommon.NOT_HAVE_PERMISSION);
		LOGGER.log(Level.INFO, MessageCommon.LINE);
		return new ResponseEntity<String>(MessageCommon.DISABLE_EWALLET_FAILED, HttpStatus.UNAUTHORIZED);
	    }
	}catch (Exception ex) {
	    LOGGER.log(Level.INFO, MessageCommon.DISABLE_EWALLET_FAILED + ": " + ex.getMessage());
	    LOGGER.log(Level.INFO, MessageCommon.LINE);
	    return new ResponseEntity<String>(MessageCommon.DISABLE_BANK_FAILED, HttpStatus.INTERNAL_SERVER_ERROR);
	}
    }

}
