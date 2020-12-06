package code88.oscar.bcm.viewObjects;

/**
 * @FileName: SystemLogByAccount.java
 * @since: 13/10/2020
 * */
public class SystemLogByAccountVO {

    private String userName;
    private String action;
    private String status;
    private String actionDate;

    public SystemLogByAccountVO(String userName, String action, String status, String actionDate) {
	super();
	this.userName = userName;
	this.action = action;
	this.status = status;
	this.actionDate = actionDate;
    }

    public SystemLogByAccountVO() {
	super();
	// TODO Auto-generated constructor stub
    }

    public String getUserName() {
	return userName;
    }

    public void setUserName(String userName) {
	this.userName = userName;
    }

    public String getAction() {
	return action;
    }

    public void setAction(String action) {
	this.action = action;
    }

    public String getStatus() {
	return status;
    }

    public void setStatus(String status) {
	this.status = status;
    }

    public String getActionDate() {
	return actionDate;
    }

    public void setActionDate(String actionDate) {
	this.actionDate = actionDate;
    }

    @Override
    public String toString() {
	return "SystemLogByAccountVO [userName=" + userName + ", action=" + action + ", status=" + status
		+ ", actionDate=" + actionDate + "]";
    }
}
