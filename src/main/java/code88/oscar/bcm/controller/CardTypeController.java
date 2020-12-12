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
import code88.oscar.bcm.services.CardTypeService;
import code88.oscar.bcm.viewObjects.CardTypeVO;

/**
 * @FileName: CardTypeController.java
 * @since: 12/12/2020
 * */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/api/card-type")
public class CardTypeController {

    private static final Logger LOGGER = LogManager.getLogger(CardTypeController.class);

    @Autowired
    private CardTypeService cardTypeService;

    @Autowired
    private AccountUserService accountUserService;

    @RequestMapping(value = "/get-list-card-type-info/{accountUserValid}", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> getListBankInfo(
	    @PathVariable("accountUserValid") String accountUserValid) {
	Map<String, Object> map = new HashMap<>();
	List<CardTypeVO> list = new ArrayList<>();
	LOGGER.log(Level.INFO, MessageCommon.LINE);
	LOGGER.log(Level.INFO, MessageCommon.START_GET_LIST_CARD_TYPE);
	try {
	    boolean isAdmin = accountUserService.isAdminRole(accountUserValid);
	    boolean isManager = accountUserService.isMangerRole(accountUserValid);
	    if (isAdmin == true || isManager == true) {
		list = cardTypeService.getAllCards();
		map.put("cards", list);
		LOGGER.log(Level.INFO, MessageCommon.GET_LIST_CARD_TYPE_SUCCESS);
		LOGGER.log(Level.INFO, MessageCommon.LINE);
		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
	    } else {
		LOGGER.log(Level.INFO, MessageCommon.GET_LIST_CARD_TYPE_FAILED);
		LOGGER.log(Level.INFO, MessageCommon.NOT_HAVE_PERMISSION);
		LOGGER.log(Level.INFO, MessageCommon.LINE);
		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.UNAUTHORIZED);
	    }
	} catch (Exception ex) {
	    LOGGER.log(Level.INFO, MessageCommon.GET_LIST_CARD_TYPE_FAILED + ": " + ex.getMessage());
	    LOGGER.log(Level.INFO, MessageCommon.LINE);
	    return new ResponseEntity<Map<String, Object>>(map, HttpStatus.INTERNAL_SERVER_ERROR);

	}
    }

    @RequestMapping(value = "/get-list-card-active/{accountUserValid}", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> getListBankActive(
	    @PathVariable("accountUserValid") String accountUserValid) {
	Map<String, Object> map = new HashMap<>();
	List<CardTypeVO> list = new ArrayList<>();
	LOGGER.log(Level.INFO, MessageCommon.LINE);
	LOGGER.log(Level.INFO, MessageCommon.START_GET_LIST_CARD_TYPE_ACTIVE);
	try {
	    boolean isAdmin = accountUserService.isAdminRole(accountUserValid);
	    boolean isManager = accountUserService.isMangerRole(accountUserValid);
	    boolean isStaff = accountUserService.isStaffRole(accountUserValid);
	    if (isAdmin == true || isManager == true || isStaff == true) {
		list = cardTypeService.getActiveCards();
		map.put("cards", list);
		LOGGER.log(Level.INFO, MessageCommon.GET_LIST_CARD_TYPE_ACTIVE_SUCCESS);
		LOGGER.log(Level.INFO, MessageCommon.LINE);
		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
	    } else {
		LOGGER.log(Level.INFO, MessageCommon.GET_LIST_CARD_TYPE_ACTIVE_FAILED);
		LOGGER.log(Level.INFO, MessageCommon.NOT_HAVE_PERMISSION);
		LOGGER.log(Level.INFO, MessageCommon.LINE);
		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.UNAUTHORIZED);
	    }
	} catch (Exception ex) {
	    LOGGER.log(Level.INFO, MessageCommon.GET_LIST_CARD_TYPE_ACTIVE_FAILED + ": " + ex.getMessage());
	    LOGGER.log(Level.INFO, MessageCommon.LINE);
	    return new ResponseEntity<Map<String, Object>>(map, HttpStatus.INTERNAL_SERVER_ERROR);
	}
    }

    @RequestMapping(value ="/disable-card/{accountUserValid}/{id}", method = RequestMethod.POST)
    public ResponseEntity<String> disableBankInfoByCode(@PathVariable("accountUserValid") String accountUserValid,
	    @PathVariable("id") int id) {
	LOGGER.log(Level.INFO, MessageCommon.LINE);
	LOGGER.log(Level.INFO, MessageCommon.START_DISABLE_CARD_TYPE);
	try {
	    boolean isAdmin = accountUserService.isAdminRole(accountUserValid);
	    boolean isManager = accountUserService.isMangerRole(accountUserValid);
	    if (isAdmin == true || isManager == true) {
		cardTypeService.disableCardById(id);
		LOGGER.log(Level.INFO, MessageCommon.DISABLE_CARD_TYPE_SUCCESS);
		LOGGER.log(Level.INFO, MessageCommon.LINE);
		return new ResponseEntity<String>(MessageCommon.DISABLE_BANK_SUCCESS, HttpStatus.OK);
	    } else {
		LOGGER.log(Level.INFO, MessageCommon.DISABLE_CARD_TYPE_FAILED);
		LOGGER.log(Level.INFO, MessageCommon.NOT_HAVE_PERMISSION);
		LOGGER.log(Level.INFO, MessageCommon.LINE);
		return new ResponseEntity<String>(MessageCommon.DISABLE_CARD_TYPE_FAILED, HttpStatus.UNAUTHORIZED);
	    }
	}catch (Exception ex) {
	    LOGGER.log(Level.INFO, MessageCommon.DISABLE_CARD_TYPE_FAILED + ": " + ex.getMessage());
	    LOGGER.log(Level.INFO, MessageCommon.LINE);
	    return new ResponseEntity<String>(MessageCommon.DISABLE_CARD_TYPE_FAILED, HttpStatus.INTERNAL_SERVER_ERROR);
	}
    }
    
    @RequestMapping(value ="/enable-card/{accountUserValid}/{id}", method = RequestMethod.POST)
    public ResponseEntity<String> enableBankInfoByCode(@PathVariable("accountUserValid") String accountUserValid,
	    @PathVariable("id") int id) {
	LOGGER.log(Level.INFO, MessageCommon.LINE);
	LOGGER.log(Level.INFO, MessageCommon.START_ENABLE_CARD_TYPE);
	try {
	    boolean isAdmin = accountUserService.isAdminRole(accountUserValid);
	    boolean isManager = accountUserService.isMangerRole(accountUserValid);
	    if (isAdmin == true || isManager == true) {
		cardTypeService.enableCardById(id);
		LOGGER.log(Level.INFO, MessageCommon.ENABLE_CARD_TYPE_SUCCESS);
		LOGGER.log(Level.INFO, MessageCommon.LINE);
		return new ResponseEntity<String>(MessageCommon.ENABLE_CARD_TYPE_SUCCESS, HttpStatus.OK);
	    } else {
		LOGGER.log(Level.INFO, MessageCommon.ENABLE_EWALLET_FAILED);
		LOGGER.log(Level.INFO, MessageCommon.NOT_HAVE_PERMISSION);
		LOGGER.log(Level.INFO, MessageCommon.LINE);
		return new ResponseEntity<String>(MessageCommon.ENABLE_EWALLET_FAILED, HttpStatus.UNAUTHORIZED);
	    }
	}catch (Exception ex) {
	    LOGGER.log(Level.INFO, MessageCommon.ENABLE_CARD_TYPE_FAILED + ": " + ex.getMessage());
	    LOGGER.log(Level.INFO, MessageCommon.LINE);
	    return new ResponseEntity<String>(MessageCommon.ENABLE_CARD_TYPE_FAILED, HttpStatus.INTERNAL_SERVER_ERROR);
	}
    }
}
