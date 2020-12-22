package code88.oscar.bcm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import code88.oscar.bcm.model.AccountUserModel;

/**
 * @FileName: AccountUserRepository.java
 * @since:22/10/2020
 * */
@Repository
public interface AccountUserRepository extends JpaRepository<AccountUserModel, String> {

	public final static String sql_countActiveRoleWithID = 
			"SELECT COUNT(*) " + 
			"FROM account_user " + 
			"WHERE "+ 
			"account_id = :pAccountId AND " + 
			"role_code = :pRoleCode AND " + 
			"status = :pStatus ";
	
	public final static String sql_countAccounByUserNameAndPassword = 
		"SELECT COUNT(*) "
		+ " FROM account_user "
		+ " WHERE "
		+ " user_name = :pUserName AND "
		+ " pass_word = :pPassword ";
	
	public final static String sql_getAccountLogIn = ""
		+ "SELECT "
		+ "	account_id, "
		+ "	user_id,"
		+ "	user_name,"
		+ "	pass_word, "
		+ "	role_code,"
		+ "	status, "
		+ "	is_login, "
		+ "	last_login_date, "
		+ "	create_by,"
		+ "	create_date "
		+ " FROM account_user "
		+ " WHERE "
		+ "	user_name = :pUserName AND "
		+ "	pass_word = :pPassword "
		+ "";
		
	public final static String sql_gerAccountById = ""
		+ " SELECT * FROM account_user WHERE account_id = :pAccountId";
	
	public final static String sql_getAllAccountUser = ""
		+ " SELECT "
		+ "	acu.account_id AS account_id, "
		+ "    	concat(u.first_name,' ', u.midle_name,' ', u.last_name) AS user_id, "
		+ "    	ru.role_name AS role_code, "
		+ "    	acu.user_name AS user_name, "
		+ "    	acu.pass_word AS pass_word, "
		+ "    	acu.status AS status, "
		+ "	acu.is_login, "
		+ "	acu.last_login_date, "
		+ "    	acu.create_by AS create_by, "
		+ "    	acu.create_date AS create_date "
		+ " FROM account_user acu"
		+ " LEFT JOIN user u ON acu.user_id = u.user_id "
		+ " LEFT JOIN role_user ru ON acu.role_code = ru.role_code "
		+ "";
	
	public final static String sql_searchAccountByUserName = ""
		+ " SELECT "
		+ "	acu.account_id AS account_id, "
		+ "    	concat(u.first_name,' ', u.midle_name,' ', u.last_name) AS user_id, "
		+ "    	ru.role_name AS role_code, "
		+ "    	acu.user_name AS user_name, "
		+ "    	acu.pass_word AS pass_word, "
		+ "    	acu.status AS status,"
		+ "	acu.is_login AS is_login,"
		+ "	acu.last_login_date AS last_login_date, "
		+ "    	acu.create_by AS create_by, "
		+ "    	acu.create_date AS create_date "
		+ " FROM account_user acu"
		+ " LEFT JOIN user u ON acu.user_id = u.user_id "
		+ " LEFT JOIN role_user ru ON acu.role_code = ru.role_code "
		+ " WHERE acu.user_name LIKE %:pUserName%"
		+ "";

	public final static String sql_unBlockAnAccountUser = "UPDATE account_user SET status = 'ACTIVE', is_login = '1', number_login_failed = 0  WHERE account_id = :pAccountId ";
	
	public final static String sql_resetPasswordAnAccountUser = "UPDATE account_user acu SET acu.pass_word = :pPassword, acu.is_login = '1' WHERE acu.account_id = :pAccountId ";

	public final static String sql_updateAccountIsLogin = ""
		+ "UPDATE account_user SET is_login = 0 WHERE user_name = :pUserName AND pass_word = :pPassword AND is_login = 1";
	
	public final static String sql_updateAccountIsLogout = ""
		+ "UPDATE account_user SET is_login = 1 WHERE account_id = :pAccountId AND user_name = :pUserName AND is_login = 0";
	// Used to check account have suitable permission
	@Query(value = sql_countActiveRoleWithID, nativeQuery = true)
	int countAdminRoleWithID(@Param("pAccountId") String pAccountId, @Param("pRoleCode") String pRoleCode,
			@Param("pStatus") String pStatus);
	
	public static final String sql_getNumberLoginFailed = ""
		+ " SELECT number_login_failde "
		+ " FROM account_user "
		+ " WHERE user_name = :pUserName ";
	
	public static final String sql_increaseNumberLoginFailed = ""
		+ " UPDATE account_user "
		+ " SET number_login_failed = number_login_failed + 1"
		+ " WHERE user_name = :pUserName";
	
	public static final String sql_resetNumberLoginFailed = ""
		+ " UPDATE account_user "
		+ " SET number_login_failed = 0 "
		+ " WHERE user_name = :pUserName";
	
	public static final String sql_getUserNameByAccountId = ""
		+ " SELECT user_name FROM account_user WHERE account_id = :pAccountId";
	
	@Transactional
	@Modifying
	@Query(value = sql_getNumberLoginFailed, nativeQuery = true)
	int getNumberLoginFailed(@Param("pUserName") String userName);
	
	// Used to check account when log in
	@Query(value = sql_countAccounByUserNameAndPassword, nativeQuery = true)
	int countAccountByUserNameAndPassword(@Param("pUserName") String userName, @Param("pPassword") String passWord);
	
	// Get info account when log in success
	@Transactional
	@Query(value = sql_getAccountLogIn, nativeQuery = true)
	AccountUserModel getAccountLogin (@Param("pUserName") String userName, @Param("pPassword") String passWord);
	
	@Query(value = sql_gerAccountById,nativeQuery = true )
	AccountUserModel getAccountById (@Param("pAccountId") String accountId);
	
	@Query(value = sql_getAllAccountUser, nativeQuery = true)
	List<AccountUserModel> getListAccountUser();
	
	@Modifying
	@Transactional
	@Query(value = sql_unBlockAnAccountUser, nativeQuery = true)
	int unblockAccountUser(@Param("pAccountId") String accountId);
	
	@Modifying
	@Transactional
	@Query(value = sql_resetPasswordAnAccountUser, nativeQuery = true)
	int resetPassword(@Param("pPassword") String password, @Param("pAccountId") String accountId);
	
	@Query(value = sql_searchAccountByUserName, nativeQuery = true)
	List<AccountUserModel> searchUserByName(@Param("pUserName") String userName);
	
	@Modifying
	@Transactional
	@Query(value = sql_updateAccountIsLogin, nativeQuery = true)
	void updateIsLogin(@Param("pUserName") String accountId, @Param("pPassword") String userName);
	
	@Modifying
	@Transactional
	@Query(value = sql_updateAccountIsLogout, nativeQuery = true)
	void updateIsLogout(@Param("pAccountId") String accountId, @Param("pUserName") String userName);
	
	@Modifying
	@Transactional
	@Query(value = sql_increaseNumberLoginFailed, nativeQuery = true)
	void increaseNumberLoginFailed(@Param("pUserName") String userName);

	@Modifying
	@Transactional
	@Query(value = sql_resetNumberLoginFailed, nativeQuery = true)
	void resetNumberLoginFailed(@Param("pUserName") String userName);
	
	@Query(value = sql_getUserNameByAccountId, nativeQuery = true)
	String getUserNameByAccountId(@Param("pAccountId") String accountId);
}
