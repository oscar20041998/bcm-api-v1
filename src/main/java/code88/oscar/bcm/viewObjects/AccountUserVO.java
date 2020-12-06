package code88.oscar.bcm.viewObjects;

/**
 * @FileName: AcccountUserVO.java
 * @since: 17/10/2020
 */
public class AccountUserVO {

    private String accountId;
    private String usedBy;
    private String role;
    private String userName;
    private String password;
    private String status;
    private String isLogin;
    private String createdBy;
    private String createdDate;
    private String lasLoginDate;

    public AccountUserVO() {
	super();
	// TODO Auto-generated constructor stub
    }

    public AccountUserVO(String accountId, String usedBy, String role, String userName, String password, String status,
	    String isLogin, String createdBy, String createdDate, String lasLoginDate) {
	super();
	this.accountId = accountId;
	this.usedBy = usedBy;
	this.role = role;
	this.userName = userName;
	this.password = password;
	this.status = status;
	this.isLogin = isLogin;
	this.createdBy = createdBy;
	this.createdDate = createdDate;
	this.lasLoginDate = lasLoginDate;
    }

    public String getAccountId() {
	return accountId;
    }

    public void setAccountId(String accountId) {
	this.accountId = accountId;
    }

    public String getUsedBy() {
	return usedBy;
    }

    public void setUsedBy(String usedBy) {
	this.usedBy = usedBy;
    }

    public String getRole() {
	return role;
    }

    public void setRole(String role) {
	this.role = role;
    }

    public String getUserName() {
	return userName;
    }

    public void setUserName(String userName) {
	this.userName = userName;
    }

    public String getPassword() {
	return password;
    }

    public void setPassword(String password) {
	this.password = password;
    }

    public String getStatus() {
	return status;
    }

    public void setStatus(String status) {
	this.status = status;
    }

    public String getIsLogin() {
	return isLogin;
    }

    public void setIsLogin(String isLogin) {
	this.isLogin = isLogin;
    }

    public String getCreatedBy() {
	return createdBy;
    }

    public void setCreatedBy(String createdBy) {
	this.createdBy = createdBy;
    }

    public String getCreatedDate() {
	return createdDate;
    }

    public void setCreatedDate(String createdDate) {
	this.createdDate = createdDate;
    }

    public String getLasLoginDate() {
	return lasLoginDate;
    }

    public void setLasLoginDate(String lasLoginDate) {
	this.lasLoginDate = lasLoginDate;
    }

    @Override
    public String toString() {
	return "AccountUserVO [accountId=" + accountId + ", usedBy=" + usedBy + ", role=" + role + ", userName="
		+ userName + ", password=" + password + ", status=" + status + ", isLogin=" + isLogin + ", createdBy="
		+ createdBy + ", createdDate=" + createdDate + ", lasLoginDate=" + lasLoginDate + "]";
    }

}
