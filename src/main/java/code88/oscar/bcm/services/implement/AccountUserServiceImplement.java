package code88.oscar.bcm.services.implement;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import code88.oscar.bcm.common.CommonMethod;
import code88.oscar.bcm.common.Role;
import code88.oscar.bcm.common.StatusCommon;
import code88.oscar.bcm.model.AccountUserModel;
import code88.oscar.bcm.repository.AccountUserRepository;
import code88.oscar.bcm.request.ChangePasswordRequest;
import code88.oscar.bcm.request.LogInRequest;
import code88.oscar.bcm.request.SaveUserRequest;
import code88.oscar.bcm.services.AccountUserService;
import code88.oscar.bcm.viewObjects.AccountUserVO;

/**
 * @FileName: AccountUserServiceImplement.java
 * @since:15/11/2020
 */
@Service
public class AccountUserServiceImplement implements AccountUserService {

    @Autowired
    private AccountUserRepository accountUserRepository;

    @Autowired
    private CommonMethod commonMethod;

    @Override
    public List<AccountUserVO> getListAccountUser() {
	List<AccountUserModel> listModel = accountUserRepository.getListAccountUser();
	List<AccountUserVO> listVO = mappingListModelAndListVO(listModel);
	return listVO;
    }

    @Override
    public AccountUserModel saveAccountUser(SaveUserRequest userRequest) {
	AccountUserModel accountUserModel = mappingAccountUser(userRequest);
	return accountUserRepository.save(accountUserModel);
    }

    @Override
    public void deleteUserAccount() {
	// TODO Auto-generated method stub

    }

    @Override
    public AccountUserModel changePasswordAccountUser(ChangePasswordRequest request) {
	String currentPass = commonMethod.encryptString(request.getCurrentPass().getBytes());
	String newPass = request.getNewPass();
	String confirmPass = request.getConfirmNewPass();
	AccountUserModel userModel = accountUserRepository.getAccountById(request.getAccountId());
	try {
	    if (newPass.equals(confirmPass) && currentPass.equals(userModel.getPassWord())) {

		userModel.setPassWord(commonMethod.encryptString(newPass.getBytes()));
		accountUserRepository.save(userModel);
		commonMethod.insertSystemLog(userModel.getUserName(), "Change password", StatusCommon.SUCCESS);
	    } else {
		commonMethod.insertSystemLog(userModel.getUserName(), "Change password", StatusCommon.FAILED);
		return null;

	    }
	} catch (Exception ex) {
	    commonMethod.insertSystemLog(userModel.getUserName(), "Change password", StatusCommon.FAILED);
	}

	return userModel;

    }

    @Override
    public boolean isValidAccountUser(String userName, String passWord) {
	// TODO Auto-generated method stub
	return false;
    }

    @Override
    public boolean isAdminRole(String accountId) {
	return accountUserRepository.countAdminRoleWithID(accountId, Role.ROLE_ADMINSTRATOR, StatusCommon.ACTIVE) == 1;
    }

    @Override
    public boolean isMangerRole(String accountId) {
	return accountUserRepository.countAdminRoleWithID(accountId, Role.ROLE_MANAGER, StatusCommon.ACTIVE) == 1;

    }

    @Override
    public boolean isStaffRole(String accountId) {
	return accountUserRepository.countAdminRoleWithID(accountId, Role.ROLE_STAFF, StatusCommon.ACTIVE) == 1;
    }

    /**
     * @Function: mappingAccountUser(...)
     * @param: SaveAccountUserRequest - Object
     */
    public AccountUserModel mappingAccountUser(SaveUserRequest request) {
	AccountUserModel model = new AccountUserModel();
	model.setAccountId("AC_" + commonMethod.convertDateTimeNowToString());
	model.setUserId(request.getUserId());
	model.setUserName(request.getLastName() + request.getFristName());
	String password = commonMethod.autoCreatePassword(request.getDateOfBirth());
	model.setPassWord(commonMethod.encryptString(password.getBytes()));
	model.setStatus(StatusCommon.ACTIVE);
	model.setRoleCode(request.getRole());
	model.setCreateBy(request.getCreateBy() != null ? request.getCreateBy() : "SYSTEM");
	model.setCreateDate(commonMethod.getDateTimeNow());
	return model;
    }

    @Override
    public AccountUserModel getAccountLogin(LogInRequest request) {
	AccountUserModel responseLogIn = new AccountUserModel();
	String userName = request.getUserName();
	String passWord = commonMethod.encryptString(request.getPassWord().getBytes());
	int count = accountUserRepository.countAccountByUserNameAndPassword(userName, passWord);
	if (count == 1) {
	    responseLogIn = accountUserRepository.getAccountLogin(userName, passWord);
	} else {
	    responseLogIn = null;
	}
	return responseLogIn;
    }

    @Override
    public AccountUserModel getAccountProfile(String accountId) {
	return accountUserRepository.getOne(accountId);
    }

    /**
     * @Function: mappingListModelAndListVO
     * @since: 17/10/2020
     */
    public List<AccountUserVO> mappingListModelAndListVO(List<AccountUserModel> listModel) {
	List<AccountUserVO> listVO = new ArrayList<>();
	for (AccountUserModel model : listModel) {
	    AccountUserVO vo = new AccountUserVO();
	    vo.setAccountId(model.getAccountId());
	    vo.setUsedBy(model.getUserId());
	    vo.setRole(model.getRoleCode());
	    vo.setUserName(model.getUserName());
	    vo.setPassword(model.getPassWord());
	    vo.setStatus(model.getStatus());
	    vo.setIsLogin(model.getIsLogin());
	    vo.setCreatedBy(model.getCreateBy());
	    vo.setCreatedDate(commonMethod.convertDateTimeToString(model.getCreateDate()));
	    vo.setLasLoginDate(commonMethod.convertDateTimeToString(model.getLastLoginDate()));
	    listVO.add(vo);
	}
	return listVO;
    }

    @Override
    public AccountUserVO getAccountByAccountId(String accountId) {
	AccountUserVO vo = new AccountUserVO();
	AccountUserModel model = accountUserRepository.getAccountById(accountId);
	vo = mappingObjectAccountUser(model);
	return vo;
    }

    public AccountUserVO mappingObjectAccountUser(AccountUserModel model) {
	AccountUserVO vo = new AccountUserVO();
	vo.setAccountId(model.getAccountId());
	vo.setUsedBy(model.getUserId());
	vo.setRole(model.getRoleCode());
	vo.setUserName(model.getUserName());
	vo.setPassword(model.getPassWord());
	vo.setStatus(model.getStatus());
	vo.setIsLogin(model.getIsLogin());
	vo.setCreatedBy(model.getCreateBy());
	vo.setCreatedDate(commonMethod.convertDateTimeToString(model.getCreateDate()));
	vo.setLasLoginDate(commonMethod.convertDateTimeToString(model.getLastLoginDate()));
	return vo;
    }

    @Override
    public String blockAccountUser(String accountId) {
	AccountUserModel model = accountUserRepository.getAccountById(accountId);
	if (model != null) {
	    model.setStatus(StatusCommon.BLOCKED);
	    accountUserRepository.save(model);
	    return StatusCommon.SUCCESS;
	}
	return StatusCommon.FAILED;
    }

    @Override
    public String unBlockAccountUser(String accountId) {
	AccountUserModel model = accountUserRepository.getAccountById(accountId);
	if (model != null) {
	    accountUserRepository.unblockAccountUser(accountId);
	    return StatusCommon.SUCCESS;
	}
	return StatusCommon.FAILED;
    }

    @Override
    public String resetPasswordUser(String value, String accountId) {
	AccountUserModel model = accountUserRepository.getAccountById(accountId);
	if (model != null) {
	    accountUserRepository.resetPassword(commonMethod.encryptString(value.getBytes()), accountId);
	    return StatusCommon.SUCCESS;
	}
	return StatusCommon.FAILED;
    }

    @Override
    public List<AccountUserVO> searchAccountByUserName(String username) {
	List<AccountUserModel> listModel = accountUserRepository.searchUserByName(username);
	List<AccountUserVO> listVO = mappingListModelAndListVO(listModel);
	return listVO;
    }

    @Override
    public void updateAccountIsLogin(String userName, String password) {
	accountUserRepository.updateIsLogin(userName, commonMethod.encryptString(password.getBytes()));
    }

    @Override
    public void updateAccountIsLogout(String accountId, String userName) {
	accountUserRepository.updateIsLogout(accountId, userName);
    }

    @Override
    public void increaseNumberLoginFailed(String userName) {
	accountUserRepository.increaseNumberLoginFailed(userName);
    }

    @Override
    public void resetNumberLoginFailed(String userName) {
	accountUserRepository.resetNumberLoginFailed(userName);
    }

    @Override
    public String getUserNameByAccountId(String accountId) {
	return accountUserRepository.getUserNameByAccountId(accountId);
    }
}
