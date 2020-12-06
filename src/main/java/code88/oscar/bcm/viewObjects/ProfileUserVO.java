package code88.oscar.bcm.viewObjects;

import java.util.Date;

/**
 * @FileName:ProfileVO.java
 * @since: 14/10/2020
 */
public class ProfileUserVO {

    private String userId;
    private String firstName;
    private String lastName;
    private String midleName;
    private String idCard;
    private String address;
    private String phoneNumber;
    private String email;
    private Date dateOfBirth;
    private String createBy;
    private String createDate;
    private String accountId;
    private String userName;
    private String role;
    private String accountCreatedBy;
    private String accountCreatedDate;
    private String statusAccount;

    public ProfileUserVO() {
	super();
	// TODO Auto-generated constructor stub
    }

    public ProfileUserVO(String userId, String firstName, String lastName, String midleName, String idCard,
	    String address, String phoneNumber, String email, Date dateOfBirth, String createBy, String createDate,
	    String accountId, String userName, String role, String accountCreatedBy, String accountCreatedDate,
	    String statusAccount) {
	super();
	this.userId = userId;
	this.firstName = firstName;
	this.lastName = lastName;
	this.midleName = midleName;
	this.idCard = idCard;
	this.address = address;
	this.phoneNumber = phoneNumber;
	this.email = email;
	this.dateOfBirth = dateOfBirth;
	this.createBy = createBy;
	this.createDate = createDate;
	this.accountId = accountId;
	this.userName = userName;
	this.role = role;
	this.accountCreatedBy = accountCreatedBy;
	this.accountCreatedDate = accountCreatedDate;
	this.statusAccount = statusAccount;
    }

    public String getUserId() {
	return userId;
    }

    public void setUserId(String userId) {
	this.userId = userId;
    }

    public String getFirstName() {
	return firstName;
    }

    public void setFirstName(String firstName) {
	this.firstName = firstName;
    }

    public String getLastName() {
	return lastName;
    }

    public void setLastName(String lastName) {
	this.lastName = lastName;
    }

    public String getMidleName() {
	return midleName;
    }

    public void setMidleName(String midleName) {
	this.midleName = midleName;
    }

    public String getIdCard() {
	return idCard;
    }

    public void setIdCard(String idCard) {
	this.idCard = idCard;
    }

    public String getAddress() {
	return address;
    }

    public void setAddress(String address) {
	this.address = address;
    }

    public String getPhoneNumber() {
	return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
	this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
	return email;
    }

    public void setEmail(String email) {
	this.email = email;
    }

    public Date getDateOfBirth() {
	return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
	this.dateOfBirth = dateOfBirth;
    }

    public String getCreateBy() {
	return createBy;
    }

    public void setCreateBy(String createBy) {
	this.createBy = createBy;
    }

    public String getCreateDate() {
	return createDate;
    }

    public void setCreateDate(String createDate) {
	this.createDate = createDate;
    }

    public String getAccountId() {
	return accountId;
    }

    public void setAccountId(String accountId) {
	this.accountId = accountId;
    }

    public String getUserName() {
	return userName;
    }

    public void setUserName(String userName) {
	this.userName = userName;
    }

    public String getRole() {
	return role;
    }

    public void setRole(String role) {
	this.role = role;
    }

    public String getAccountCreatedBy() {
	return accountCreatedBy;
    }

    public void setAccountCreatedBy(String accountCreatedBy) {
	this.accountCreatedBy = accountCreatedBy;
    }

    public String getAccountCreatedDate() {
	return accountCreatedDate;
    }

    public void setAccountCreatedDate(String accountCreatedDate) {
	this.accountCreatedDate = accountCreatedDate;
    }

    public String getStatusAccount() {
	return statusAccount;
    }

    public void setStatusAccount(String statusAccount) {
	this.statusAccount = statusAccount;
    }

    @Override
    public String toString() {
	return "ProfileUserVO [userId=" + userId + ", firstName=" + firstName + ", lastName=" + lastName
		+ ", midleName=" + midleName + ", idCard=" + idCard + ", address=" + address + ", phoneNumber="
		+ phoneNumber + ", email=" + email + ", dateOfBirth=" + dateOfBirth + ", createBy=" + createBy
		+ ", createDate=" + createDate + ", accountId=" + accountId + ", userName=" + userName + ", role="
		+ role + ", accountCreatedBy=" + accountCreatedBy + ", accountCreatedDate=" + accountCreatedDate
		+ ", statusAccount=" + statusAccount + "]";
    }
}
