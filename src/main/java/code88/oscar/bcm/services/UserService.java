package code88.oscar.bcm.services;

import java.util.List;

import org.springframework.data.repository.query.Param;

import code88.oscar.bcm.model.UserModel;
import code88.oscar.bcm.request.SaveUserRequest;

public interface UserService {

	UserModel saveUser(SaveUserRequest userRequest);

	List<UserModel> getAllUser();

	void deleteUser(@Param(value = "user_id") String user_id);
	
	UserModel getUserProfile(String userId);
	
	List<UserModel> searchUserByCriteria(String criteria);

}
