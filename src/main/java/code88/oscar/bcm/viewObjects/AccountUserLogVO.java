package code88.oscar.bcm.viewObjects;

/**
 * @FileName: AccountUserLogVO.java
 * @since: 31/10/2020
 * */
public class AccountUserLogVO {
    private int id;

    private String userName;

    private String accountId;

    private String userAction;

    private String createdBy;

    private String createdDate;

    public AccountUserLogVO() {
	super();
	// TODO Auto-generated constructor stub
    }

    public AccountUserLogVO(int id, String userName, String accountId, String userAction, String createdBy,
	    String createdDate) {
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

    public String getCreatedDate() {
	return createdDate;
    }

    public void setCreatedDate(String createdDate) {
	this.createdDate = createdDate;
    }

    @Override
    public String toString() {
	return "AccountUserLogVO [id=" + id + ", userName=" + userName + ", accountId=" + accountId + ", userAction="
		+ userAction + ", createdBy=" + createdBy + ", createdDate=" + createdDate + "]";
    }
}
