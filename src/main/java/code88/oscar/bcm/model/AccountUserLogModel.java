package code88.oscar.bcm.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @FileName: AccountUserLogModel.java
 * @since: 18/10/2020
 */
@Entity
@Table(name = "account_user_log")
public class AccountUserLogModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "account_id")
    private String accountId;

    @Column(name = "user_action")
    private String userAction;

    @Column(name = "create_by")
    private String createdBy;

    @Column(name = "create_date")
    private LocalDateTime createdDate;

    public AccountUserLogModel() {
	super();
	// TODO Auto-generated constructor stub
    }

    public AccountUserLogModel(int id, String userName, String accountId, String userAction, String createdBy,
	    LocalDateTime createdDate) {
	super();
	this.id = id;
	this.userName = userName;
	this.accountId = accountId;
	this.userAction = userAction;
	this.createdBy = createdBy;
	this.createdDate = createdDate;
    }

    public int getId() {
	return id;
    }

    public void setId(int id) {
	this.id = id;
    }

    public String getUserName() {
	return userName;
    }

    public void setUserName(String userName) {
	this.userName = userName;
    }

    public String getAccountId() {
	return accountId;
    }

    public void setAccountId(String accountId) {
	this.accountId = accountId;
    }

    public String getUserAction() {
	return userAction;
    }

    public void setUserAction(String userAction) {
	this.userAction = userAction;
    }

    public String getCreatedBy() {
	return createdBy;
    }

    public void setCreatedBy(String createdBy) {
	this.createdBy = createdBy;
    }

    public LocalDateTime getCreatedDate() {
	return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
	this.createdDate = createdDate;
    }

    @Override
    public String toString() {
	return "AccountUserLogModel [id=" + id + ", userName=" + userName + ", accountId=" + accountId + ", userAction="
		+ userAction + ", createdBy=" + createdBy + ", createdDate=" + createdDate + "]";
    }
}
