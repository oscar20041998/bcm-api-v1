package code88.oscar.bcm.services.implement;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import code88.oscar.bcm.common.CommonMethod;
import code88.oscar.bcm.common.StatusCommon;
import code88.oscar.bcm.model.AccountUserModel;
import code88.oscar.bcm.model.UserModel;
import code88.oscar.bcm.repository.AccountUserRepository;
import code88.oscar.bcm.repository.UserRepository;
import code88.oscar.bcm.request.SaveUserRequest;
import code88.oscar.bcm.services.UserService;

/**
 * @FileName: UserServiceImplement.java
 * @since: 10/10/2020
 */
@Service
public class UserServiceImplement implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AccountUserRepository accountUserRepository;
    
    @Autowired
    private CommonMethod commonMethod;

    @Override
    public UserModel saveUser(SaveUserRequest userRequest) {
	UserModel userModel = userRepository.getUserById(userRequest.getUserId());
	userModel = mappingUserModel(userRequest);
	if (userRequest.getUserId() == null || userRequest.getUserId().isEmpty()) {
	    AccountUserModel accountModel = mappingAccountUser(userRequest, userModel.getUserId());
	    accountUserRepository.save(accountModel);
	}
	return userRepository.save(userModel);
    }

    @Override
    public List<UserModel> getAllUser() {
	List<UserModel> listUser = userRepository.findAll();
	return listUser;
    }

    @Override
    public void deleteUser(String user_id) {
	UserModel userModel = userRepository.getOne(user_id);
	if (userModel != null) {
	    userRepository.deleteById(user_id);
	}
    }

    /**
     * @Function: mappingUserModel(...)
     * @param: SaveUserRequest userRequest - Object
     */
    public UserModel mappingUserModel(SaveUserRequest userRequest) {
	UserModel userModel = new UserModel();
	userModel.setUserId(
		userRequest.getUserId().isEmpty() || userRequest.getUserId() == null  ? commonMethod.convertDateTimeNowToString() : userRequest.getUserId());
	userModel.setFristName(userRequest.getFristName());
	userModel.setMidleName(userRequest.getMidleName());
	userModel.setLastName(userRequest.getLastName());
	userModel.setIdCard(userRequest.getIdCard());
	userModel.setDateOfBirth(userRequest.getDateOfBirth());
	userModel.setAddress(userRequest.getAddress());
	userModel.setPhoneNumber(userRequest.getPhoneNumber());
	userModel.setEmail(userRequest.getEmail());
	userModel.setCreateBy(userRequest.getCreateBy() != null ? userRequest.getCreateBy() : "SYSTEM");
	userModel.setCreateDate(commonMethod.getDateTimeNow());
	return userModel;
    }

    /**
     * @Function: mappingAccountUser(...)
     * @param: SaveAccountUserRequest - Object
     */
    public AccountUserModel mappingAccountUser(SaveUserRequest request, String userId) {
	AccountUserModel model = new AccountUserModel();
	model.setAccountId("AC_" + commonMethod.convertDateTimeNowToString());
	model.setUserId(userId);
	String password = commonMethod.autoCreatePassword(request.getDateOfBirth());
	model.setUserName(request.getLastName().toLowerCase().trim() + request.getFristName().toLowerCase().trim()
		+ password.trim());
	model.setPassWord(commonMethod.encryptString(password.getBytes()));
	model.setStatus(StatusCommon.ACTIVE);
	model.setRoleCode(request.getRole());
	model.setIsLogin("1");
	model.setCreateBy(request.getCreateBy() != null ? request.getCreateBy() : "SYSTEM");
	model.setCreateDate(commonMethod.getDateTimeNow());
	return model;
    }

    @Override
    public UserModel getUserProfile(String userId) {
	return userRepository.getUserById(userId);
    }

    @Override
    public List<UserModel> searchUserByCriteria(String criteria) {
	return userRepository.searchListUserByCriteria(criteria);
    }
}
