package code88.oscar.bcm.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.sun.istack.NotNull;

/**
 * @FileName: AccountUserModel.java
 * @since: 22/10/2020
 */
@Entity
@Table(name = "account_user")
public class AccountUserModel {

    @Id
    @Column(name = "account_id")
    private String accountId;

    @NotNull
    @Column(name = "user_id")
    private String userId;

    @NotNull
    @Column(name = "role_code")
    private String roleCode;

    @NotNull
    @Column(name = "user_name")
    private String userName;

    @NotNull
    @Column(name = "pass_word")
    private String passWord;

    @NotNull
    @Column(name = "status")
    private String status;

    @Transient
    @Column(name = "number_login_failed")
    private int numberLoginFailed;
    
    @Column(name = "create_by", nullable = false)
    private String createBy;

    @Column(name = "create_date", nullable = false)
    private LocalDateTime createDate;

    @Column(name = "is_login")
    private String isLogin;

    @Column(name = "last_login_date")
    private LocalDateTime lastLoginDate;

    public AccountUserModel() {
	super();
	// TODO Auto-generated constructor stub
    }

    public AccountUserModel(String accountId, String userId, String roleCode, String userName, String passWord,
	    String status, String createBy, LocalDateTime createDate, String isLogin, LocalDateTime lastLoginDate) {
	super();
	this.accountId = accountId;
	this.userId = userId;
	this.roleCode = roleCode;
	this.userName = userName;
	this.passWord = passWord;
	this.status = status;
	this.createBy = createBy;
	this.createDate = createDate;
	this.isLogin = isLogin;
	this.lastLoginDate = lastLoginDate;
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

    public String getRoleCode() {
	return roleCode;
    }

    public void setRoleCode(String roleCode) {
	this.roleCode = roleCode;
    }

    public String getUserName() {
	return userName;
    }

    public void setUserName(String userName) {
	this.userName = userName;
    }

    public String getPassWord() {
	return passWord;
    }

    public void setPassWord(String passWord) {
	this.passWord = passWord;
    }

    public String getStatus() {
	return status;
    }

    public void setStatus(String status) {
	this.status = status;
    }

    public String getCreateBy() {
	return createBy;
    }

    public void setCreateBy(String createBy) {
	this.createBy = createBy;
    }

    public LocalDateTime getCreateDate() {
	return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
	this.createDate = createDate;
    }

    public String getIsLogin() {
	return isLogin;
    }

    public void setIsLogin(String isLogin) {
	this.isLogin = isLogin;
    }
    
    
    public LocalDateTime getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(LocalDateTime lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    @Override
    public String toString() {
	return "AccountUserModel [accountId=" + accountId + ", userId=" + userId + ", roleCode=" + roleCode
		+ ", userName=" + userName + ", passWord=" + passWord + ", status=" + status + ", createBy=" + createBy
		+ ", createDate=" + createDate + ", isLogin=" + isLogin + ", lastLoginDate=" + lastLoginDate + "]";
    }

}
