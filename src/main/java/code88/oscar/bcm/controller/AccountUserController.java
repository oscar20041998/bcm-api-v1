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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import code88.oscar.bcm.common.ActionCommon;
import code88.oscar.bcm.common.CommonMethod;
import code88.oscar.bcm.common.MessageCommon;
import code88.oscar.bcm.common.StatusCommon;
import code88.oscar.bcm.model.AccountUserModel;
import code88.oscar.bcm.model.ResponseLogInModel;
import code88.oscar.bcm.model.UserModel;
import code88.oscar.bcm.repository.AccountUserRepository;
import code88.oscar.bcm.request.ChangePasswordRequest;
import code88.oscar.bcm.request.LogInRequest;
import code88.oscar.bcm.services.AccountUserService;
import code88.oscar.bcm.services.UserService;
import code88.oscar.bcm.viewObjects.AccountUserVO;
import code88.oscar.bcm.viewObjects.ProfileUserVO;

@CrossOrigin(origins = "coffee-management-fe-0498.herokuapp.com", maxAge = 3600)
@RestController
@RequestMapping("/api/account")
public class AccountUserController {

    private static final Logger LOGGER = LogManager.getLogger(AccountUserController.class);

    @Autowired
    private AccountUserService accountUserService;

    @Autowired
    private AccountUserRepository accountUserRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private CommonMethod commonMethod;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> logIn(@RequestBody LogInRequest request) {
	LOGGER.log(Level.INFO, MessageCommon.LINE);
	LOGGER.log(Level.INFO, MessageCommon.START_VALID_ACCOUNT);
	Map<String, Object> map = new HashMap<>();
	ResponseLogInModel response = new ResponseLogInModel();
	AccountUserModel model = new AccountUserModel();
	String userName = request.getUserName().trim();
	String password = request.getPassWord().trim();
	try {
	    if (userName != null && password != null) {
		int isValid = accountUserRepository.countAccountByUserNameAndPassword(userName,
			commonMethod.encryptString(password.getBytes()));
		if (isValid != 0) {
		    model = accountUserService.getAccountLogin(request);
		    // is login before, avoid user access twice when account is being used
		    if (model.getIsLogin().equals("0")) {
			map.put("message", MessageCommon.DUPLICATE_LOGIN);
			LOGGER.log(Level.ERROR, MessageCommon.DUPLICATE_LOGIN);
			LOGGER.log(Level.ERROR, MessageCommon.LINE);
			return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		    } else {
			accountUserService.updateAccountIsLogin(userName, password);
			accountUserService.resetNumberLoginFailed(userName);
			model = accountUserService.getAccountLogin(request);
			response = mappingResponseLogin(model);
			commonMethod.insertSystemLog(request.getUserName(), ActionCommon.LOG_IN, StatusCommon.SUCCESS);
			map.put("user", response);
			map.put("message", MessageCommon.LOGIN_SUCCES);
			LOGGER.log(Level.ERROR, MessageCommon.LOGIN_SUCCES);
			LOGGER.log(Level.ERROR, MessageCommon.LINE);
			return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		    }
		} else {
		    map.put("message", MessageCommon.NOT_HAVE_PERMISSION);
		    accountUserService.increaseNumberLoginFailed(userName);
		    commonMethod.insertSystemLog(request.getUserName(), ActionCommon.LOG_IN, StatusCommon.FAILED);
		    LOGGER.log(Level.ERROR, MessageCommon.USER_INVALID);
		    LOGGER.log(Level.ERROR, MessageCommon.LINE);
		    return new ResponseEntity<Map<String, Object>>(map, HttpStatus.NOT_FOUND);
		}
	    } else {
		map.put("message", MessageCommon.NOT_HAVE_PERMISSION);
		LOGGER.log(Level.ERROR, MessageCommon.USER_INVALID);
		LOGGER.log(Level.ERROR, MessageCommon.LINE);
		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	} catch (Exception ex) {
	    LOGGER.log(Level.ERROR, ex.getMessage());
	    LOGGER.log(Level.ERROR, MessageCommon.USER_INVALID);
	    LOGGER.log(Level.ERROR, MessageCommon.LINE);
	    return new ResponseEntity<Map<String, Object>>(map, HttpStatus.INTERNAL_SERVER_ERROR);
	}
    }

    @RequestMapping(value = "/logout-user/{accountId}/{userName}", method = RequestMethod.POST)
    public ResponseEntity<String> logout(@PathVariable("accountId") String accountId,
	    @PathVariable("userName") String userName) {
	LOGGER.log(Level.INFO, MessageCommon.LINE);
	LOGGER.log(Level.INFO, MessageCommon.START_VALID_ACCOUNT);
	try {
	    LOGGER.log(Level.INFO, MessageCommon.USER_VALID);
	    LOGGER.log(Level.INFO, MessageCommon.LINE);
	    commonMethod.insertSystemLog(userName, ActionCommon.LOG_OUT, StatusCommon.SUCCESS);
	    accountUserService.updateAccountIsLogout(accountId, userName);
	    return new ResponseEntity<String>(StatusCommon.FAILED, HttpStatus.OK);

	} catch (Exception ex) {
	    LOGGER.log(Level.ERROR, ex.getMessage());
	    LOGGER.log(Level.ERROR, MessageCommon.USER_INVALID);
	    LOGGER.log(Level.ERROR, MessageCommon.LINE);
	    commonMethod.insertSystemLog(userName, ActionCommon.LOG_OUT, StatusCommon.FAILED);
	    return new ResponseEntity<String>(StatusCommon.FAILED, HttpStatus.NOT_FOUND);
	}
    }

    /**
     * @Function: mappingResponseLogin(...)
     * @param: AccountUserModel - Object
     */
    ResponseLogInModel mappingResponseLogin(AccountUserModel model) {
	String key = commonMethod.encryptString(model.getUserName().getBytes())
		+ commonMethod.encryptString(model.getPassWord().getBytes())
		+ commonMethod.encryptString(model.getAccountId().getBytes())
		+ commonMethod.encryptString(model.getUserId().getBytes());
	ResponseLogInModel modelResponse = new ResponseLogInModel();
	modelResponse.setAccountId(model.getAccountId());
	modelResponse.setUserId(model.getUserId());
	modelResponse.setUserName(model.getUserName());
	modelResponse.setStatus(model.getStatus());
	modelResponse.setRoleCode(model.getRoleCode());
	modelResponse.setKey(key);
	modelResponse.setIsLogin(model.getIsLogin());
	return modelResponse;

    }

    @RequestMapping(value ="/get-profile-user/{userId}/{accountId}", method = RequestMethod.GET)
    public ResponseEntity<ProfileUserVO> getProfileUser(@PathVariable("userId") String userId,
	    @PathVariable("accountId") String accountId) {
	ProfileUserVO profileVO = new ProfileUserVO();
	AccountUserModel accountUserModel = new AccountUserModel();
	UserModel userModel = new UserModel();
	LOGGER.log(Level.INFO, MessageCommon.LINE);
	LOGGER.log(Level.INFO, MessageCommon.START_GET_PROFILE);
	try {
	    accountUserModel = accountUserService.getAccountProfile(accountId);
	    userModel = userService.getUserProfile(userId);
	    profileVO = mappingProfileUser(accountUserModel, userModel);
	    if (profileVO != null) {
		LOGGER.log(Level.INFO, MessageCommon.GET_PROFILE_SUCCESS);
		return new ResponseEntity<ProfileUserVO>(profileVO, HttpStatus.OK);
	    } else {
		LOGGER.log(Level.INFO, MessageCommon.GET_PROFILE_FAILED);
		return new ResponseEntity<ProfileUserVO>(profileVO, HttpStatus.NOT_FOUND);
	    }
	} catch (Exception ex) {
	    LOGGER.log(Level.ERROR, ex.getMessage());
	    LOGGER.log(Level.ERROR, MessageCommon.GET_PROFILE_FAILED);
	    LOGGER.log(Level.INFO, MessageCommon.LINE);
	}
	return new ResponseEntity<ProfileUserVO>(HttpStatus.OK);
    }

    /**
     * @Function: mappingProfileUser(...)
     */
    public ProfileUserVO mappingProfileUser(AccountUserModel account, UserModel user) {
	ProfileUserVO profileVO = new ProfileUserVO();
	profileVO.setUserId(user.getUserId());
	profileVO.setFirstName(user.getFristName());
	profileVO.setMidleName(user.getMidleName());
	profileVO.setLastName(user.getLastName());
	profileVO.setIdCard(user.getIdCard());
	profileVO.setAddress(user.getAddress());
	profileVO.setPhoneNumber(user.getPhoneNumber());
	profileVO.setEmail(user.getEmail());
	profileVO.setDateOfBirth(user.getDateOfBirth());
	profileVO.setCreateBy(user.getCreateBy());
	profileVO.setCreateDate(commonMethod.convertDateToString(user.getCreateDate()));
	profileVO.setAccountId(account.getAccountId());
	profileVO.setUserName(account.getUserName());
	profileVO.setAccountCreatedBy(account.getCreateBy());
	profileVO.setRole(account.getRoleCode());
	profileVO.setAccountCreatedDate(commonMethod.convertDateToString(account.getCreateDate()));
	profileVO.setStatusAccount(account.getStatus());
	return profileVO;
    }

    @RequestMapping(value ="/change-password", method = RequestMethod.POST)
    public ResponseEntity<String> changePassword(@RequestBody ChangePasswordRequest request) {
	LOGGER.log(Level.INFO, MessageCommon.LINE);
	LOGGER.log(Level.INFO, MessageCommon.START_CHANGE_PASSWORD);
	try {
	    AccountUserModel result = accountUserService.changePasswordAccountUser(request);
	    if (result != null) {
		LOGGER.log(Level.INFO, MessageCommon.CHANGE_PASSWORD_SUCCESS);
		LOGGER.log(Level.INFO, MessageCommon.LINE);
		return new ResponseEntity<String>(StatusCommon.SUCCESS, HttpStatus.OK);
	    } else {
		LOGGER.log(Level.ERROR, MessageCommon.CHANGE_PASSWORD_FAILED);
		LOGGER.log(Level.ERROR, MessageCommon.LINE);
		return new ResponseEntity<String>(StatusCommon.FAILED, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	} catch (Exception ex) {
	    LOGGER.log(Level.ERROR, ex.getMessage());
	    LOGGER.log(Level.ERROR, MessageCommon.CHANGE_PASSWORD_FAILED);
	    LOGGER.log(Level.ERROR, MessageCommon.LINE);
	}
	return new ResponseEntity<String>(StatusCommon.FAILED, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @RequestMapping(value = "/get-list-account-user/{accountValid}", method = RequestMethod.GET)
    public ResponseEntity<List<AccountUserVO>> getListAccountUser(@PathVariable("accountValid") String accountValid) {
	List<AccountUserVO> listResult = new ArrayList<>();
	LOGGER.log(Level.INFO, MessageCommon.LINE);
	LOGGER.log(Level.INFO, MessageCommon.START_GET_LIST_ACCOUNT);
	String userName = accountUserService.getUserNameByAccountId(accountValid);
	try {
	    boolean isAdmin = accountUserService.isAdminRole(accountValid);
	    boolean isManager = accountUserService.isMangerRole(accountValid);
	    if (isAdmin == true || isManager == true) {
		listResult = accountUserService.getListAccountUser();
		commonMethod.insertSystemLog(userName, ActionCommon.VISIT_ACCOUNTS, StatusCommon.SUCCESS);
		LOGGER.log(Level.INFO, MessageCommon.GET_LIST_ACCOUNT_SUCCESS);
		LOGGER.log(Level.INFO, MessageCommon.LINE);
		return new ResponseEntity<List<AccountUserVO>>(listResult, HttpStatus.OK);

	    } else {
		commonMethod.insertSystemLog(userName, ActionCommon.VISIT_ACCOUNTS, StatusCommon.FAILED);
		LOGGER.log(Level.INFO, MessageCommon.GET_LIST_ACCOUNT_SUCCESS);
		LOGGER.log(Level.INFO, MessageCommon.NOT_HAVE_PERMISSION);
		LOGGER.log(Level.INFO, MessageCommon.LINE);
		return new ResponseEntity<List<AccountUserVO>>(HttpStatus.UNAUTHORIZED);
	    }
	} catch (Exception ex) {
	    commonMethod.insertSystemLog(userName, ActionCommon.VISIT_ACCOUNTS + ex.getMessage(), StatusCommon.FAILED);
	    LOGGER.log(Level.ERROR, ex.getMessage());
	    LOGGER.log(Level.ERROR, MessageCommon.GET_LIST_ACCOUNT_FAILED);
	    LOGGER.log(Level.INFO, MessageCommon.LINE);

	}
	return new ResponseEntity<List<AccountUserVO>>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/search-account-by-username/{accountValid}/{userName}", method = RequestMethod.GET)
    public ResponseEntity<List<AccountUserVO>> searchAcountByUserName(@PathVariable("accountValid") String accountValid,
	    @PathVariable("userName") String userName) {
	List<AccountUserVO> listResult = new ArrayList<>();
	LOGGER.log(Level.INFO, MessageCommon.LINE);
	LOGGER.log(Level.INFO, MessageCommon.START_GET_LIST_ACCOUNT);
	try {
	    boolean isAdmin = accountUserService.isAdminRole(accountValid);
	    boolean isManager = accountUserService.isMangerRole(accountValid);
	    if (isAdmin == true || isManager == true) {
		listResult = accountUserService.searchAccountByUserName(userName);
		LOGGER.log(Level.INFO, MessageCommon.GET_LIST_ACCOUNT_SUCCESS);
		LOGGER.log(Level.INFO, MessageCommon.LINE);
		return new ResponseEntity<List<AccountUserVO>>(listResult, HttpStatus.OK);

	    } else {
		LOGGER.log(Level.INFO, MessageCommon.GET_LIST_ACCOUNT_SUCCESS);
		LOGGER.log(Level.INFO, MessageCommon.NOT_HAVE_PERMISSION);
		LOGGER.log(Level.INFO, MessageCommon.LINE);
		return new ResponseEntity<List<AccountUserVO>>(HttpStatus.UNAUTHORIZED);
	    }
	} catch (Exception ex) {
	    LOGGER.log(Level.ERROR, ex.getMessage());
	    LOGGER.log(Level.ERROR, MessageCommon.GET_LIST_ACCOUNT_FAILED);
	    LOGGER.log(Level.INFO, MessageCommon.LINE);

	}
	return new ResponseEntity<List<AccountUserVO>>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/get-list-account-login-nearly", method = RequestMethod.GET)
    public ResponseEntity<List<AccountUserVO>> getListAccountLoginNearly() {
	List<AccountUserVO> listResult = new ArrayList<>();
	LOGGER.log(Level.INFO, MessageCommon.LINE);
	LOGGER.log(Level.INFO, MessageCommon.START_GET_LIST_ACCOUNT_NEARLY);
	try {
	    listResult = accountUserService.getListAccountLoginNearly();
	    LOGGER.log(Level.INFO, MessageCommon.GET_LIST_ACCOUNT_NEARLY_SUCCESS);
	    LOGGER.log(Level.INFO, MessageCommon.LINE);
	    return new ResponseEntity<List<AccountUserVO>>(listResult, HttpStatus.OK);

	} catch (Exception ex) {
	    LOGGER.log(Level.ERROR, ex.getMessage());
	    LOGGER.log(Level.ERROR, MessageCommon.GET_LIST_ACCOUNT_NEARLY_FAILED);
	    LOGGER.log(Level.INFO, MessageCommon.LINE);
	    return new ResponseEntity<List<AccountUserVO>>(HttpStatus.NOT_FOUND);
	}
    }

    @RequestMapping( value = "/get-account-by-account-id/{accountId}/{accountIdValid}", method = RequestMethod.GET)
    public ResponseEntity<AccountUserVO> getAccountByAccountId(@PathVariable("accountId") String accountId,
	    @PathVariable("accountIdValid") String accountIdValid) {
	LOGGER.log(Level.INFO, MessageCommon.LINE);
	LOGGER.log(Level.INFO, MessageCommon.START_GET_ACCOUNT_BY_ACCOUNT_ID);
	AccountUserVO vo = new AccountUserVO();
	String userName = accountUserService.getUserNameByAccountId(accountIdValid);
	try {
	    boolean isAdmin = accountUserService.isAdminRole(accountIdValid);
	    boolean isManager = accountUserService.isMangerRole(accountIdValid);
	    if (isAdmin == true || isManager == true) {
		vo = accountUserService.getAccountByAccountId(accountId);
		commonMethod.insertSystemLog(userName, ActionCommon.VISIT_ACCOUNT_DETAIL + " " + accountId,
			StatusCommon.SUCCESS);
		LOGGER.log(Level.INFO, MessageCommon.GET_ACCOUNT_BY_ACCOUNT_ID_SUCCESS);
		return new ResponseEntity<AccountUserVO>(vo, HttpStatus.OK);

	    } else {
		commonMethod.insertSystemLog(userName, ActionCommon.VISIT_ACCOUNT_DETAIL + " " + accountId,
			StatusCommon.FAILED);
		return new ResponseEntity<AccountUserVO>(vo, HttpStatus.UNAUTHORIZED);
	    }
	} catch (Exception ex) {
	    commonMethod.insertSystemLog(userName, ActionCommon.VISIT_ACCOUNT_DETAIL + " " + accountId + " " + ex.getMessage(),
			StatusCommon.FAILED);
	    LOGGER.log(Level.ERROR, ex.getMessage());
	    LOGGER.log(Level.ERROR, MessageCommon.GET_ACCOUNT_BY_ACCOUNT_ID_FAILED);
	    LOGGER.log(Level.INFO, MessageCommon.LINE);
	}
	return new ResponseEntity<AccountUserVO>(vo, HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/block-an-account-user-by-account-id/{accountId}", method = RequestMethod.POST)
    public ResponseEntity<String> blockAnAccountUserById(@PathVariable("accountId") String accountId) {
	try {
	    accountUserService.blockAccountUser(accountId);
	    return new ResponseEntity<String>(accountUserService.blockAccountUser(accountId), HttpStatus.OK);
	} catch (Exception ex) {
	    return new ResponseEntity<String>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
    }

    @RequestMapping(value = "/unblock-an-account-user-by-account-id/{accountId}", method = RequestMethod.POST)
    public ResponseEntity<String> ubBlockAnAccountUserById(@PathVariable("accountId") String accountId) {
	try {
	    accountUserService.unBlockAccountUser(accountId);
	    return new ResponseEntity<String>(accountUserService.unBlockAccountUser(accountId), HttpStatus.OK);
	} catch (Exception ex) {
	    LOGGER.log(Level.ERROR, ex.getMessage());
	    LOGGER.log(Level.INFO, MessageCommon.LINE);
	    return new ResponseEntity<String>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
    }

    @RequestMapping(value = "/reset-password-by-account-id/{accountId}/{value}", method = RequestMethod.POST)
    public ResponseEntity<String> resetPassword(@PathVariable("accountId") String accountId,
	    @PathVariable("value") String value) {
	try {
	    accountUserService.resetPasswordUser(value, accountId);
	    return new ResponseEntity<String>(accountUserService.resetPasswordUser(value, accountId), HttpStatus.OK);
	} catch (Exception ex) {
	    LOGGER.log(Level.ERROR, ex.getMessage());
	    LOGGER.log(Level.INFO, MessageCommon.LINE);
	    return new ResponseEntity<String>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
    }
}
