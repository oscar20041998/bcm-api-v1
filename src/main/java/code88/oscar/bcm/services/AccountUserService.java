package code88.oscar.bcm.services;

import java.util.List;

import code88.oscar.bcm.model.AccountUserModel;
import code88.oscar.bcm.request.ChangePasswordRequest;
import code88.oscar.bcm.request.LogInRequest;
import code88.oscar.bcm.request.SaveUserRequest;
import code88.oscar.bcm.viewObjects.AccountUserVO;

public interface AccountUserService {

	List<AccountUserVO> getListAccountUser();
	
	AccountUserModel saveAccountUser(SaveUserRequest accountRequest);
	
	void deleteUserAccount();
	
	AccountUserModel changePasswordAccountUser(ChangePasswordRequest request);
	
	boolean isValidAccountUser(String userName, String passWord);
	
	boolean isAdminRole(String accountId);
	
	boolean isMangerRole(String accountId);
	
	boolean isStaffRole(String accountId);
	
	AccountUserModel getAccountLogin(LogInRequest request);
	
	AccountUserModel getAccountProfile(String accountId);
	
	AccountUserVO getAccountByAccountId (String accountId);
	
	String blockAccountUser(String accountId);
	
	String unBlockAccountUser(String accountId);
	
	String resetPasswordUser(String value, String accountId);

	List<AccountUserVO> searchAccountByUserName(String username);
	
	void updateAccountIsLogin(String accountId, String userName);
	
	void updateAccountIsLogout(String accountId, String userName);
		
	void increaseNumberLoginFailed(String userName);

	void resetNumberLoginFailed(String userName);

	String getUserNameByAccountId(String accountId);

}
