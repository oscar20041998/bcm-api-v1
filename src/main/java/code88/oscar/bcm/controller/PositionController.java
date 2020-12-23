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

import code88.oscar.bcm.common.ActionCommon;
import code88.oscar.bcm.common.CommonMethod;
import code88.oscar.bcm.common.MessageCommon;
import code88.oscar.bcm.common.StatusCommon;
import code88.oscar.bcm.services.AccountUserService;
import code88.oscar.bcm.services.PositionService;
import code88.oscar.bcm.viewObjects.PositionVO;

/**
 * @FileName: PositionController.java
 * @since: 29/10/2020
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/position/api")
public class PositionController {

    private static final Logger LOGGER = LogManager.getLogger(PositionController.class);

    @Autowired
    private PositionService positionService;

    @Autowired
    private AccountUserService accountUserService;

    @Autowired
    private CommonMethod commonMethod;

    @RequestMapping(value = "/get-positions/{accountUserValid}", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> getPositions(@PathVariable("accountUserValid") String accountId) {
	LOGGER.log(Level.INFO, MessageCommon.LINE);
	LOGGER.log(Level.INFO, MessageCommon.START_GET_ALL_POSITION);
	Map<String, Object> map = new HashMap<>();
	List<PositionVO> result = new ArrayList<>();
	try {
	    boolean isManager = accountUserService.isMangerRole(accountId);
	    boolean isAdmin = accountUserService.isAdminRole(accountId);
	    boolean isStaff = accountUserService.isStaffRole(accountId);
	    if (isManager == true || isAdmin == true || isStaff == true) {
		result = positionService.getListPosition();
		int opening = positionService.opening();
		int closed = positionService.closed();
		if (!result.isEmpty()) {
		    map.put("positions", result);
		    map.put("isOpening", opening);
		    map.put("isClosed", closed);
		    LOGGER.log(Level.INFO, MessageCommon.GET_ALL_POSITION_SUCCESS);
		    LOGGER.log(Level.INFO, MessageCommon.LINE);
		    return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		} else {
		    LOGGER.log(Level.INFO, MessageCommon.GET_ALL_POSITION_FAILED);
		    LOGGER.log(Level.INFO, MessageCommon.LINE);
		    return new ResponseEntity<Map<String, Object>>(map, HttpStatus.NO_CONTENT);
		}
	    } else {
		LOGGER.log(Level.INFO, MessageCommon.GET_ALL_POSITION_FAILED);
		LOGGER.log(Level.INFO, MessageCommon.NOT_HAVE_PERMISSION);
		LOGGER.log(Level.INFO, MessageCommon.LINE);
		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.UNAUTHORIZED);
	    }
	} catch (Exception ex) {
	    LOGGER.log(Level.ERROR, MessageCommon.GET_ALL_POSITION_FAILED);
	    LOGGER.log(Level.ERROR, ex.getMessage());
	    LOGGER.log(Level.ERROR, MessageCommon.GET_ALL_POSITION_FAILED);
	}
	return new ResponseEntity<Map<String, Object>>(map, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @RequestMapping(value = "/get-positions-opening/{accountUserValid}", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> getPositionsOpening(@PathVariable("accountUserValid") String accountId) {
	LOGGER.log(Level.INFO, MessageCommon.LINE);
	LOGGER.log(Level.INFO, MessageCommon.START_GET_POSITION_OPENING);
	Map<String, Object> map = new HashMap<>();
	List<PositionVO> result = new ArrayList<>();
	try {
	    boolean isManager = accountUserService.isMangerRole(accountId);
	    boolean isAdmin = accountUserService.isAdminRole(accountId);
	    boolean isStaff = accountUserService.isStaffRole(accountId);
	    if (isManager == true || isAdmin == true || isStaff == true) {
		result = positionService.getListPositionOpening();
		int opening = positionService.opening();
		int closed = positionService.closed();
		if (!result.isEmpty()) {
		    map.put("positions", result);
		    map.put("isOpening", opening);
		    map.put("isClosed", closed);
		    LOGGER.log(Level.INFO, MessageCommon.GET_POSITION_OPENING_SUCCESS);
		    LOGGER.log(Level.INFO, MessageCommon.LINE);
		    return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		} else {
		    LOGGER.log(Level.INFO, MessageCommon.GET_POSITION_OPENING_FAILED);
		    LOGGER.log(Level.INFO, MessageCommon.LINE);
		    return new ResponseEntity<Map<String, Object>>(map, HttpStatus.NO_CONTENT);
		}
	    } else {
		LOGGER.log(Level.INFO, MessageCommon.GET_POSITION_OPENING_FAILED);
		LOGGER.log(Level.INFO, MessageCommon.NOT_HAVE_PERMISSION);
		LOGGER.log(Level.INFO, MessageCommon.LINE);
		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.UNAUTHORIZED);
	    }
	} catch (Exception ex) {
	    LOGGER.log(Level.ERROR, MessageCommon.GET_POSITION_OPENING_FAILED);
	    LOGGER.log(Level.ERROR, ex.getMessage());
	    LOGGER.log(Level.ERROR, MessageCommon.GET_POSITION_OPENING_FAILED);
	}
	return new ResponseEntity<Map<String, Object>>(map, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @RequestMapping(value = "/get-positions-closed/{accountUserValid}", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> getPositionsClosed(@PathVariable("accountUserValid") String accountId) {
	LOGGER.log(Level.INFO, MessageCommon.LINE);
	LOGGER.log(Level.INFO, MessageCommon.START_GET_POSITION_CLOSED);
	Map<String, Object> map = new HashMap<>();
	List<PositionVO> result = new ArrayList<>();
	String userName = accountUserService.getUserNameByAccountId(accountId);
	try {
	    boolean isManager = accountUserService.isMangerRole(accountId);
	    boolean isAdmin = accountUserService.isAdminRole(accountId);
	    boolean isStaff = accountUserService.isStaffRole(accountId);
	    if (isManager == true || isAdmin == true || isStaff == true) {
		result = positionService.getListPositionClosed();
		int opening = positionService.opening();
		int closed = positionService.closed();
		if (!result.isEmpty()) {
		    map.put("positions", result);
		    map.put("isOpening", opening);
		    map.put("isClosed", closed);
		    LOGGER.log(Level.INFO, MessageCommon.GET_POSITION_CLOSED_SUCCESS);
		    LOGGER.log(Level.INFO, MessageCommon.LINE);
		    return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		} else {
		    commonMethod.insertSystemLog(userName, ActionCommon.STAY_SALE_SCREEN, StatusCommon.FAILED);
		    LOGGER.log(Level.INFO, MessageCommon.GET_POSITION_CLOSED_FAILED);
		    LOGGER.log(Level.INFO, MessageCommon.LINE);
		    return new ResponseEntity<Map<String, Object>>(map, HttpStatus.NO_CONTENT);
		}
	    } else {
		commonMethod.insertSystemLog(userName, ActionCommon.STAY_SALE_SCREEN, StatusCommon.FAILED);
		LOGGER.log(Level.INFO, MessageCommon.GET_POSITION_CLOSED_FAILED);
		LOGGER.log(Level.INFO, MessageCommon.NOT_HAVE_PERMISSION);
		LOGGER.log(Level.INFO, MessageCommon.LINE);
		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.UNAUTHORIZED);
	    }
	} catch (Exception ex) {
	    commonMethod.insertSystemLog(userName, ActionCommon.STAY_SALE_SCREEN, StatusCommon.FAILED);
	    LOGGER.log(Level.ERROR, MessageCommon.GET_POSITION_CLOSED_FAILED);
	    LOGGER.log(Level.ERROR, ex.getMessage());
	    LOGGER.log(Level.ERROR, MessageCommon.GET_POSITION_CLOSED_FAILED);
	}
	return new ResponseEntity<Map<String, Object>>(map, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @RequestMapping(value = "/move-current-table/{currentTable}/{newTable}/{accountUserValid}", method = RequestMethod.POST)
    public ResponseEntity<String> moveCurrentTable(@PathVariable("currentTable") String currenTable,
	    @PathVariable("newTable") String newTable, @PathVariable("accountUserValid") String accountUserValid) {
	LOGGER.log(Level.INFO, MessageCommon.LINE);
	LOGGER.log(Level.INFO, MessageCommon.START_MOVE_CURRENT_POSITION);
	try {
	    String userName = accountUserService.getUserNameByAccountId(accountUserValid);
	    boolean isManager = accountUserService.isMangerRole(accountUserValid);
	    boolean isAdmin = accountUserService.isAdminRole(accountUserValid);
	    boolean isStaff = accountUserService.isStaffRole(accountUserValid);
	    if (isManager == true || isAdmin == true || isStaff == true) {
		positionService.moveTableCurrent(currenTable, newTable, userName);
		LOGGER.log(Level.INFO, MessageCommon.MOVE_CURRENT_POSITION_SUCCESS);
		LOGGER.log(Level.INFO, MessageCommon.LINE);
		return new ResponseEntity<String>(MessageCommon.MOVE_CURRENT_POSITION_SUCCESS, HttpStatus.OK);
	    } else {
		LOGGER.log(Level.INFO, MessageCommon.MOVE_POSITION_FAILED);
		LOGGER.log(Level.INFO, MessageCommon.NOT_HAVE_PERMISSION);
		LOGGER.log(Level.INFO, MessageCommon.LINE);
		return new ResponseEntity<String>(MessageCommon.NOT_HAVE_PERMISSION, HttpStatus.UNAUTHORIZED);
	    }
	} catch (Exception ex) {
	    LOGGER.log(Level.INFO, MessageCommon.MOVE_POSITION_FAILED);
	    LOGGER.log(Level.INFO, ex.getMessage());
	    LOGGER.log(Level.INFO, MessageCommon.LINE);
	    return new ResponseEntity<String>(MessageCommon.MOVE_POSITION_FAILED, HttpStatus.INTERNAL_SERVER_ERROR);
	}
    }
}
