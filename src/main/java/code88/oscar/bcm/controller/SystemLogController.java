package code88.oscar.bcm.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import code88.oscar.bcm.common.CommonMethod;
import code88.oscar.bcm.common.MessageCommon;
import code88.oscar.bcm.model.PersonalLogModel;
import code88.oscar.bcm.request.SearchLogRequest;
import code88.oscar.bcm.services.AccountUserLogService;
import code88.oscar.bcm.services.AccountUserService;
import code88.oscar.bcm.services.PersonalLogService;
import code88.oscar.bcm.services.ProductLogService;
import code88.oscar.bcm.services.UserLogService;
import code88.oscar.bcm.viewObjects.AccountUserLogVO;
import code88.oscar.bcm.viewObjects.ProductLogVO;
import code88.oscar.bcm.viewObjects.SystemLogByAccountVO;
import code88.oscar.bcm.viewObjects.UserLogVO;

/**
 * @FileName: SystemLogController.java
 * @since: 13/10/2020
 */
@RestController
@RequestMapping("/system-log/api")
public class SystemLogController {

    private static final Logger LOGGER = LogManager.getLogger(SystemLogController.class);

    @Autowired
    private CommonMethod commonMethod;

    @Autowired
    private PersonalLogService personalLogService;

    @Autowired
    private UserLogService userLogService;

    @Autowired
    private AccountUserLogService accountUserLogService;

    @Autowired
    private AccountUserService accountUserService;

    @Autowired
    private ProductLogService productLogService;

    @RequestMapping(value = "/get-system-log-by-account/{userName}", method = RequestMethod.GET)
    public ResponseEntity<List<SystemLogByAccountVO>> getActionLogByAccount(@PathVariable("userName") String userName) {
	List<SystemLogByAccountVO> listResult = new ArrayList<>();
	LOGGER.log(Level.INFO, MessageCommon.LINE);
	LOGGER.log(Level.INFO, MessageCommon.START_GET_PERSONAL_LOG);
	try {
	    List<PersonalLogModel> listModel = personalLogService.getSystemLogByAccountUser(userName);
	    listResult = mappingListSystemLog(listModel);
	    if (listResult.size() > 0) {
		LOGGER.log(Level.INFO, MessageCommon.GET_PERSONAL_LOG_SUCCESS);
		return new ResponseEntity<List<SystemLogByAccountVO>>(listResult, HttpStatus.OK);
	    }
	} catch (Exception ex) {
	    LOGGER.log(Level.ERROR, ex.getMessage());
	    LOGGER.log(Level.ERROR, MessageCommon.GET_PERSONAL_LOG_FAILED);
	    LOGGER.log(Level.INFO, MessageCommon.LINE);

	}
	return new ResponseEntity<List<SystemLogByAccountVO>>(listResult, HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/search-personal-log", method = RequestMethod.POST)
    public ResponseEntity<List<SystemLogByAccountVO>> getActionLogByAccountAndDate(
	    @RequestBody SearchLogRequest request) {
	List<SystemLogByAccountVO> listResult = new ArrayList<>();
	List<PersonalLogModel> listModel = new ArrayList<>();
	LOGGER.log(Level.INFO, MessageCommon.LINE);
	LOGGER.log(Level.INFO, MessageCommon.START_GET_PERSONAL_LOG);
	try {
	    if (request.getDate() != null) {
		listModel = personalLogService.searchSystemLogByDateAndAcountId(request);
		listResult = mappingListSystemLog(listModel);
	    } else {
		listModel = personalLogService.getSystemLogByAccountUser(request.getUserName());
		listResult = mappingListSystemLog(listModel);
	    }
	    if (listResult.size() > 0) {
		LOGGER.log(Level.INFO, MessageCommon.GET_PERSONAL_LOG_SUCCESS);
		return new ResponseEntity<List<SystemLogByAccountVO>>(listResult, HttpStatus.OK);
	    }
	} catch (Exception ex) {
	    LOGGER.log(Level.ERROR, ex.getMessage());
	    LOGGER.log(Level.ERROR, MessageCommon.GET_PERSONAL_LOG_FAILED);
	    LOGGER.log(Level.INFO, MessageCommon.LINE);

	}
	return new ResponseEntity<List<SystemLogByAccountVO>>(listResult, HttpStatus.NOT_FOUND);
    }

    List<SystemLogByAccountVO> mappingListSystemLog(List<PersonalLogModel> listModel) {
	List<SystemLogByAccountVO> listResult = new ArrayList<>();
	for (PersonalLogModel model : listModel) {
	    SystemLogByAccountVO vo = new SystemLogByAccountVO();
	    vo.setUserName(model.getAccountId());
	    vo.setAction(model.getAction());
	    vo.setStatus(model.getActionStatus());
	    vo.setActionDate(commonMethod.convertDateTimeToString(model.getActionDate()));
	    ;
	    listResult.add(vo);
	}
	return listResult;
    }

    @RequestMapping(value = "/get-account-user-log/{accountUserValid}", method = RequestMethod.GET)
    public ResponseEntity<List<AccountUserLogVO>> getListAccountUserLog(
	    @PathVariable("accountUserValid") String accountUserValid) {
	List<AccountUserLogVO> listVO = new ArrayList<>();
	LOGGER.log(Level.INFO, MessageCommon.LINE);
	LOGGER.log(Level.INFO, MessageCommon.START_GET_ACCOUNT_USER_LOG);
	try {
	    boolean isAdmin = accountUserService.isAdminRole(accountUserValid);
	    boolean isManager = accountUserService.isMangerRole(accountUserValid);
	    if (isAdmin == true || isManager == true) {
		listVO = accountUserLogService.getListAccountUserLog();
		LOGGER.log(Level.INFO, MessageCommon.GET_ACCOUNT_USER_LOG_SUCCESS);
		LOGGER.log(Level.INFO, MessageCommon.LINE);
		return new ResponseEntity<List<AccountUserLogVO>>(listVO, HttpStatus.OK);
	    } else {
		LOGGER.log(Level.ERROR, MessageCommon.GET_ACCOUNT_USER_LOG_FAILED);
		LOGGER.log(Level.ERROR, MessageCommon.NOT_HAVE_PERMISSION);
		LOGGER.log(Level.INFO, MessageCommon.LINE);
		return new ResponseEntity<List<AccountUserLogVO>>(listVO, HttpStatus.OK);
	    }
	} catch (Exception ex) {
	    LOGGER.log(Level.ERROR, ex.getMessage());
	    LOGGER.log(Level.ERROR, MessageCommon.GET_ACCOUNT_USER_LOG_FAILED);
	    LOGGER.log(Level.INFO, MessageCommon.LINE);
	}
	return new ResponseEntity<List<AccountUserLogVO>>(listVO, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @RequestMapping(value = "/get-user-log/{accountUserValid}", method = RequestMethod.GET)
    public ResponseEntity<List<UserLogVO>> getListtUserLog(@PathVariable("accountUserValid") String accountUserValid) {
	List<UserLogVO> listVO = new ArrayList<>();
	LOGGER.log(Level.INFO, MessageCommon.LINE);
	LOGGER.log(Level.INFO, MessageCommon.START_GET_USER_LOG);
	try {
	    boolean isAdmin = accountUserService.isAdminRole(accountUserValid);
	    boolean isManager = accountUserService.isMangerRole(accountUserValid);
	    if (isAdmin == true || isManager == true) {
		listVO = userLogService.getListLogUser();
		LOGGER.log(Level.INFO, MessageCommon.GET_USER_LOG_SUCCESS);
		LOGGER.log(Level.INFO, MessageCommon.LINE);
		return new ResponseEntity<List<UserLogVO>>(listVO, HttpStatus.OK);
	    } else {
		LOGGER.log(Level.INFO, MessageCommon.GET_USER_LOG_FAILED);
		LOGGER.log(Level.INFO, MessageCommon.NOT_HAVE_PERMISSION);
		LOGGER.log(Level.INFO, MessageCommon.LINE);
		return new ResponseEntity<List<UserLogVO>>(listVO, HttpStatus.OK);
	    }
	} catch (Exception ex) {
	    LOGGER.log(Level.ERROR, ex.getMessage());
	    LOGGER.log(Level.ERROR, MessageCommon.GET_USER_LOG_FAILED);
	    LOGGER.log(Level.INFO, MessageCommon.LINE);
	}
	return new ResponseEntity<List<UserLogVO>>(listVO, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @RequestMapping(value = "/get-product-log/{accountUserValid}", method = RequestMethod.GET)
    public ResponseEntity<List<ProductLogVO>> getProductLog(@PathVariable("accountUserValid") String accountUserValid) {
	List<ProductLogVO> listVO = new ArrayList<>();
	LOGGER.log(Level.INFO, MessageCommon.LINE);
	LOGGER.log(Level.INFO, MessageCommon.START_GET_PRODUCT_LOG);
	try {
	    boolean isAdmin = accountUserService.isAdminRole(accountUserValid);
	    boolean isManager = accountUserService.isMangerRole(accountUserValid);
	    if (isAdmin == true || isManager == true) {
		listVO = productLogService.getProductLog();
		LOGGER.log(Level.INFO, MessageCommon.GET_PRODUCT_LOG_SUCCESS);
		LOGGER.log(Level.INFO, MessageCommon.LINE);
		return new ResponseEntity<List<ProductLogVO>>(listVO, HttpStatus.OK);
	    } else {
		LOGGER.log(Level.INFO, MessageCommon.GET_PRODUCT_LOG_FAILED);
		LOGGER.log(Level.INFO, MessageCommon.NOT_HAVE_PERMISSION);
		LOGGER.log(Level.INFO, MessageCommon.LINE);
		return new ResponseEntity<List<ProductLogVO>>(listVO, HttpStatus.OK);
	    }
	} catch (Exception ex) {
	    LOGGER.log(Level.ERROR, ex.getMessage());
	    LOGGER.log(Level.ERROR, MessageCommon.GET_PRODUCT_LOG_FAILED);
	    LOGGER.log(Level.INFO, MessageCommon.LINE);
	}
	return new ResponseEntity<List<ProductLogVO>>(listVO, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
