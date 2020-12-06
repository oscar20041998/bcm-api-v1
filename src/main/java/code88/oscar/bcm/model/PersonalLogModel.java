package code88.oscar.bcm.model;

import javax.persistence.Table;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @FileName: PersonalLogModel.java
 * @since: 13/10/2020
 */
@Entity
@Table(name = "personal_log")
public class PersonalLogModel {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "user_name")
    private String accountId;

    @Column(name = "action")
    private String action;

    @Column(name = "action_status")
    private String actionStatus;

    @Column(name = "action_date")
    private LocalDateTime actionDate;

    public PersonalLogModel() {
	super();
	// TODO Auto-generated constructor stub
    }

    public PersonalLogModel(String id, String accountId, String action, String actionStatus, LocalDateTime actionDate) {
	super();
	this.id = id;
	this.accountId = accountId;
	this.action = action;
	this.actionStatus = actionStatus;
	this.actionDate = actionDate;
    }

    public String getId() {
	return id;
    }

    public void setId(String id) {
	this.id = id;
    }

    public String getAccountId() {
	return accountId;
    }

    public void setAccountId(String accountId) {
	this.accountId = accountId;
    }

    public String getAction() {
	return action;
    }

    public void setAction(String action) {
	this.action = action;
    }

    public String getActionStatus() {
	return actionStatus;
    }

    public void setActionStatus(String actionStatus) {
	this.actionStatus = actionStatus;
    }

    public LocalDateTime getActionDate() {
	return actionDate;
    }

    public void setActionDate(LocalDateTime actionDate) {
	this.actionDate = actionDate;
    }

    @Override
    public String toString() {
	return "PersonalLogModel [id=" + id + ", accountId=" + accountId + ", action=" + action + ", actionStatus="
		+ actionStatus + ", actionDate=" + actionDate + "]";
    }

}
