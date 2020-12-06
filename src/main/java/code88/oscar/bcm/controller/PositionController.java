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
	String userName = accountUserService.getUserNameByAccountId(accountId);
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
		    commonMethod.insertSystemLog(userName, ActionCommon.STAY_SALE_SCREEN, StatusCommon.SUCCESS);
		    LOGGER.log(Level.INFO, MessageCommon.GET_ALL_POSITION_SUCCESS);
		    LOGGER.log(Level.INFO, MessageCommon.LINE);
		    return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		} else {
		    commonMethod.insertSystemLog(userName, ActionCommon.STAY_SALE_SCREEN, StatusCommon.FAILED);
		    LOGGER.log(Level.INFO, MessageCommon.GET_ALL_POSITION_FAILED);
		    LOGGER.log(Level.INFO, MessageCommon.LINE);
		    return new ResponseEntity<Map<String, Object>>(map, HttpStatus.NO_CONTENT);
		}
	    } else {
		commonMethod.insertSystemLog(userName, ActionCommon.STAY_SALE_SCREEN, StatusCommon.FAILED);
		LOGGER.log(Level.INFO, MessageCommon.GET_ALL_POSITION_FAILED);
		LOGGER.log(Level.INFO, MessageCommon.NOT_HAVE_PERMISSION);
		LOGGER.log(Level.INFO, MessageCommon.LINE);
		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.UNAUTHORIZED);
	    }
	} catch (Exception ex) {
	    commonMethod.insertSystemLog(userName, ActionCommon.STAY_SALE_SCREEN, StatusCommon.FAILED);
	    LOGGER.log(Level.ERROR, MessageCommon.GET_ALL_POSITION_FAILED);
	    LOGGER.log(Level.ERROR, ex.getMessage());
	    LOGGER.log(Level.ERROR, MessageCommon.GET_ALL_POSITION_FAILED);
	}
	return new ResponseEntity<Map<String, Object>>(map, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
