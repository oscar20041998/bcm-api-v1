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
import code88.oscar.bcm.common.StatusCommon;
import code88.oscar.bcm.model.UserModel;
import code88.oscar.bcm.request.SaveUserRequest;
import code88.oscar.bcm.services.AccountUserService;
import code88.oscar.bcm.services.UserService;
import code88.oscar.bcm.viewObjects.UserVO;

@RestController
@RequestMapping(value = "/user/api")
public class UserController {

    private static final Logger LOGGER = LogManager.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private AccountUserService accountUserService;

    @Autowired
    StatusCommon status;

    @Autowired
    private CommonMethod commonMethod;

    @RequestMapping(value = "/create-new-user", method = RequestMethod.POST)
    public ResponseEntity<String> createNewUser(@RequestBody SaveUserRequest userRequest) {
	LOGGER.log(Level.INFO, MessageCommon.LINE);
	LOGGER.log(Level.INFO, MessageCommon.START_CREATE_USER);
	try {
	    if (accountUserService.isAdminRole(userRequest.getAccountIdValid()) == true
		    || accountUserService.isMangerRole(userRequest.getAccountIdValid()) == true
		    || userRequest.getAccountIdValid() == "SUPPER_ADMIN") {
		userService.saveUser(userRequest);
		LOGGER.log(Level.INFO, MessageCommon.CREATE_USER_SUCCESS);
		LOGGER.log(Level.INFO, MessageCommon.LINE);
		return new ResponseEntity<String>(MessageCommon.CREATE_USER_FAILED, HttpStatus.OK);

	    } else {
		LOGGER.log(Level.INFO, MessageCommon.NOT_HAVE_PERMISSION);
		LOGGER.log(Level.INFO, MessageCommon.LINE);
		return new ResponseEntity<String>(userRequest.getAccountIdValid() + MessageCommon.NOT_HAVE_PERMISSION,
			HttpStatus.UNAUTHORIZED);

	    }
	} catch (Exception e) {
	    LOGGER.log(Level.ERROR, MessageCommon.CREATE_USER_FAILED);
	    LOGGER.log(Level.ERROR, e.getMessage().toString());
	    LOGGER.log(Level.INFO, MessageCommon.LINE);
	    return new ResponseEntity<String>(MessageCommon.CREATE_USER_FAILED, HttpStatus.INTERNAL_SERVER_ERROR);
	}
    }

    @RequestMapping(value = "/get-list-user/{accountIdValid}", method = RequestMethod.GET)
    public ResponseEntity<List<UserVO>> getAllListUser(@PathVariable("accountIdValid") String accountIdValid) {
	List<UserVO> listResult = new ArrayList<>();
	List<UserModel> listModel = new ArrayList<>();
	LOGGER.log(Level.INFO, MessageCommon.LINE);
	try {
	    LOGGER.log(Level.INFO, MessageCommon.START_GET_LIST_USER);
	    boolean isAdmin = accountUserService.isAdminRole(accountIdValid);
	    boolean isManager = accountUserService.isMangerRole(accountIdValid);
	    if (isAdmin == true || isManager == true) {
		listModel = userService.getAllUser();
		listResult = mappingUserList(listModel);
		if (!listModel.isEmpty()) {
		    LOGGER.log(Level.INFO, MessageCommon.GET_ALL_USER_SUCCESS);
		    return new ResponseEntity<List<UserVO>>(listResult, HttpStatus.OK);
		} else {
		    LOGGER.log(Level.INFO, MessageCommon.GET_ALL_USER_FAILED);
		    return new ResponseEntity<List<UserVO>>(listResult, HttpStatus.NOT_FOUND);
		}
	    } else {
		LOGGER.log(Level.INFO, MessageCommon.GET_ALL_USER_FAILED);
		LOGGER.log(Level.INFO, MessageCommon.NOT_HAVE_PERMISSION);
		LOGGER.log(Level.INFO, MessageCommon.LINE);
		return new ResponseEntity<List<UserVO>>(listResult, HttpStatus.UNAUTHORIZED);
	    }
	} catch (Exception ex) {
	    LOGGER.log(Level.ERROR, MessageCommon.CREATE_USER_FAILED);
	    LOGGER.log(Level.ERROR, ex.getMessage().toString());
	    LOGGER.log(Level.INFO, MessageCommon.LINE);
	    return new ResponseEntity<List<UserVO>>(listResult, HttpStatus.INTERNAL_SERVER_ERROR);
	}
    }

    @RequestMapping(value = "/search-user-by-criteria/{criteria}/{accountIdValid}", method = RequestMethod.GET)
    public ResponseEntity<List<UserVO>> searchUserByCriteria(@PathVariable("criteria") String criteria,
	    @PathVariable("accountIdValid") String accountIdValid) {
	List<UserVO> listResult = new ArrayList<>();
	List<UserModel> listModel = new ArrayList<>();
	LOGGER.log(Level.INFO, MessageCommon.LINE);
	try {
	    LOGGER.log(Level.INFO, MessageCommon.START_GET_LIST_USER);
	    boolean isAdmin = accountUserService.isAdminRole(accountIdValid);
	    boolean isManager = accountUserService.isMangerRole(accountIdValid);
	    if (isAdmin == true || isManager == true) {
		listModel = userService.searchUserByCriteria(criteria);
		listResult = mappingUserList(listModel);
		if (!listModel.isEmpty()) {
		    LOGGER.log(Level.INFO, MessageCommon.GET_ALL_USER_SUCCESS);
		    return new ResponseEntity<List<UserVO>>(listResult, HttpStatus.OK);
		} else {
		    LOGGER.log(Level.INFO, MessageCommon.GET_ALL_USER_FAILED);
		    return new ResponseEntity<List<UserVO>>(listResult, HttpStatus.NOT_FOUND);
		}
	    } else {
		LOGGER.log(Level.INFO, MessageCommon.GET_ALL_USER_FAILED);
		LOGGER.log(Level.INFO, MessageCommon.NOT_HAVE_PERMISSION);
		LOGGER.log(Level.INFO, MessageCommon.LINE);
		return new ResponseEntity<List<UserVO>>(listResult, HttpStatus.UNAUTHORIZED);
	    }
	} catch (Exception ex) {
	    LOGGER.log(Level.ERROR, MessageCommon.CREATE_USER_FAILED);
	    LOGGER.log(Level.ERROR, ex.getMessage().toString());
	    LOGGER.log(Level.INFO, MessageCommon.LINE);
	    return new ResponseEntity<List<UserVO>>(listResult, HttpStatus.INTERNAL_SERVER_ERROR);
	}
    }

    /**
     * @Function: mappingUserList(...)
     * @param: List<UserModel> - List
     */
    public List<UserVO> mappingUserList(List<UserModel> listModel) {
	List<UserVO> listVO = new ArrayList<>();
	for (UserModel model : listModel) {
	    UserVO vo = new UserVO();
	    vo.setUserId(model.getUserId());
	    vo.setEmail(model.getEmail());
	    vo.setFirstName(model.getFristName());
	    vo.setMidleName(model.getMidleName());
	    vo.setLastName(model.getLastName());
	    vo.setFullName(model.getFristName() + " " + model.getMidleName() + " " + model.getLastName());
	    vo.setIdCard(model.getIdCard());
	    vo.setPhoneNumber(model.getPhoneNumber());
	    vo.setDateOfBirth(model.getDateOfBirth());
	    vo.setAddress(model.getAddress());
	    vo.setCreatedBy(model.getCreateBy());
	    vo.setCreatedDate(commonMethod.convertDateTimeToString(model.getCreateDate()));
	    listVO.add(vo);
	}
	return listVO;
    }

    /**
     * @Function: mappingUserList(...)
     * @param: List<UserModel> - List
     */
    public UserVO mappingUser(UserModel model) {
	UserVO vo = new UserVO();
	vo.setUserId(model.getUserId());
	vo.setEmail(model.getEmail());
	vo.setFirstName(model.getFristName());
	vo.setMidleName(model.getMidleName());
	vo.setLastName(model.getLastName());
	vo.setFullName(model.getFristName() + " " + model.getMidleName() + " " + model.getLastName());
	vo.setIdCard(model.getIdCard());
	vo.setPhoneNumber(model.getPhoneNumber());
	vo.setDateOfBirth(model.getDateOfBirth());
	vo.setAddress(model.getAddress());
	vo.setCreatedBy(model.getCreateBy());
	vo.setCreatedDate(commonMethod.convertDateTimeToString(model.getCreateDate()));
	return vo;
    }

    @RequestMapping(value = "/get-user-by-id/{userId}/{accountUserValid}", method = RequestMethod.POST)
    public ResponseEntity<UserVO> getUserById(@PathVariable("userId") String userId,
	    @PathVariable("accountUserValid") String accountUserValid) {
	UserVO vo = new UserVO();
	UserModel model = new UserModel();
	LOGGER.log(Level.INFO, MessageCommon.LINE);
	LOGGER.log(Level.INFO, MessageCommon.START_GET_USER);
	try {
	    boolean isAdmin = accountUserService.isAdminRole(accountUserValid);
	    boolean isManager = accountUserService.isMangerRole(accountUserValid);
	    if (isAdmin == true || isManager == true) {
		model = userService.getUserProfile(userId);
		vo = mappingUser(model);
		LOGGER.log(Level.INFO, MessageCommon.GET_USER_SUCCESS);
		LOGGER.log(Level.INFO, MessageCommon.LINE);
		return new ResponseEntity<UserVO>(vo, HttpStatus.OK);
	    } else {
		LOGGER.log(Level.INFO, MessageCommon.GET_USER_FAILED);
		LOGGER.log(Level.INFO, MessageCommon.NOT_HAVE_PERMISSION);
		LOGGER.log(Level.INFO, MessageCommon.LINE);
		return new ResponseEntity<UserVO>(vo, HttpStatus.UNAUTHORIZED);
	    }

	} catch (Exception ex) {
	    LOGGER.log(Level.INFO, MessageCommon.GET_USER_FAILED);
	    LOGGER.log(Level.INFO, ex.getMessage());
	    LOGGER.log(Level.INFO, MessageCommon.LINE);
	}
	return new ResponseEntity<UserVO>(vo, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @RequestMapping(value = "/delete-user-by-id/{userId}/{accountUserValid}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteUserById(@PathVariable("userId") String userId,
	    @PathVariable("accountUserValid") String accountUserValid) {
	UserModel model = new UserModel();
	LOGGER.log(Level.INFO, MessageCommon.LINE);
	LOGGER.log(Level.INFO, MessageCommon.START_DELETE_USER);
	try {
	    boolean isAdmin = accountUserService.isAdminRole(accountUserValid);
	    if (isAdmin == true) {
		model = userService.getUserProfile(userId);
		if (model != null) {
		    userService.deleteUser(userId);
		} else {
		    return new ResponseEntity<String>(MessageCommon.DELETE_USER_FAILED, HttpStatus.NOT_FOUND);
		}
		LOGGER.log(Level.INFO, MessageCommon.GET_USER_SUCCESS);
		LOGGER.log(Level.INFO, MessageCommon.LINE);
		return new ResponseEntity<String>(MessageCommon.DELETE_USER_SUCCESS, HttpStatus.OK);
	    } else {
		LOGGER.log(Level.INFO, MessageCommon.GET_USER_FAILED);
		LOGGER.log(Level.INFO, MessageCommon.NOT_HAVE_PERMISSION);
		LOGGER.log(Level.INFO, MessageCommon.LINE);
		return new ResponseEntity<String>(MessageCommon.DELETE_USER_FAILED, HttpStatus.UNAUTHORIZED);
	    }

	} catch (Exception ex) {
	    LOGGER.log(Level.INFO, MessageCommon.GET_USER_FAILED);
	    LOGGER.log(Level.INFO, ex.getMessage());
	    LOGGER.log(Level.INFO, MessageCommon.LINE);
	}
	return new ResponseEntity<String>(MessageCommon.DELETE_USER_FAILED, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
