package code88.oscar.bcm.model;

/**
 * @FileName: ResponseLogInModel.java
 * @since: 11/10/2020
 */
public class ResponseLogInModel {
    private String accountId;

    private String userId;

    private String userName;

    private String roleCode;

    private String status;

    private String key;

    private String isLogin;

    public ResponseLogInModel() {
	super();
	// TODO Auto-generated constructor stub
    }

    public ResponseLogInModel(String accountId, String userId, String roleCode, String userName, String key,
	    String islogin) {
	super();
	this.accountId = accountId;
	this.userId = userId;
	this.roleCode = roleCode;
	this.userName = userName;
	this.isLogin = islogin;
    }

    public String getAccountId() {
	return accountId;
    }

    public void setAccountId(String accountId) {
	this.accountId = accountId;
    }

    public String getUserId() {
	return userId;
    }

    public void setUserId(String userId) {
	this.userId = userId;
    }

    public String getUserName() {
	return userName;
    }

    public void setUserName(String userName) {
	this.userName = userName;
    }

    public String getRoleCode() {
	return roleCode;
    }

    public void setRoleCode(String roleCode) {
	this.roleCode = roleCode;
    }

    public String getStatus() {
	return status;
    }

    public void setStatus(String status) {
	this.status = status;
    }

    public String getKey() {
	return key;
    }

    public void setKey(String key) {
	this.key = key;
    }

    public String getIsLogin() {
	return isLogin;
    }

    public void setIsLogin(String isLogin) {
	this.isLogin = isLogin;
    }

    @Override
    public String toString() {
	return "ResponseLogInModel [accountId=" + accountId + ", userId=" + userId + ", userName=" + userName
		+ ", roleCode=" + roleCode + ", status=" + status + ", key=" + key + ", isLogin=" + isLogin + "]";
    }

}
