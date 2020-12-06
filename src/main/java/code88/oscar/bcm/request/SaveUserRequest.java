package code88.oscar.bcm.request;

import java.sql.Date;

/**
 * @FileName: CreateUserRequest.java
 * @since: 10/10/2020
 */

public class SaveUserRequest {

    private String accountIdValid;

    private String userId;

    private String fristName;

    private String midleName;

    private String lastName;

    private String role;
    
    private Date dateOfBirth;

    private String idCard;

    private String address;

    private String phoneNumber;

    private String email;

    private String createBy;

    private Date createDate;

    public SaveUserRequest() {
	super();
	// TODO Auto-generated constructor stub
    }

    public SaveUserRequest(String userId, String fristName, String midleName, String lastName, Date dateOfBirth,
	    String idCard, String address, String phoneNumber, String email, String userIdValid, String createBy,
	    Date createDate, String role) {
	super();
	this.userId = userId;
	this.fristName = fristName;
	this.midleName = midleName;
	this.lastName = lastName;
	this.dateOfBirth = dateOfBirth;
	this.idCard = idCard;
	this.address = address;
	this.role = role;
	this.phoneNumber = phoneNumber;
	this.email = email;
	this.accountIdValid = userIdValid;
	this.createBy = createBy;
	this.createDate = createDate;
    }

    public String getUserId() {
	return userId;
    }

    public void setUserId(String userId) {
	this.userId = userId;
    }

    public String getFristName() {
	return fristName;
    }

    public void setFristName(String fristName) {
	this.fristName = fristName;
    }

    public String getMidleName() {
	return midleName;
    }

    public void setMidleName(String midleName) {
	this.midleName = midleName;
    }

    public String getLastName() {
	return lastName;
    }

    public void setLastName(String lastName) {
	this.lastName = lastName;
    }

    public Date getDateOfBirth() {
	return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
	this.dateOfBirth = dateOfBirth;
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

    public String getAccountIdValid() {
	return accountIdValid;
    }

    public void setAccountIdValid(String accountIdValid) {
	this.accountIdValid = accountIdValid;
    }

    public Date getCreateDate() {
	return createDate;
    }

    public void setCreateDate(Date createDate) {
	this.createDate = createDate;
    }

    public String getCreateBy() {
	return createBy;
    }

    public void setCreateBy(String createBy) {
	this.createBy = createBy;
    }

    @Override
    public String toString() {
	return "SaveUserRequest [accountIdValid=" + accountIdValid + ", userId=" + userId + ", fristName=" + fristName
		+ ", midleName=" + midleName + ", lastName=" + lastName + ", dateOfBirth=" + dateOfBirth + ", idCard="
		+ idCard + ", address=" + address + ", phoneNumber=" + phoneNumber + ", email=" + email + ", createBy="
		+ createBy + ", createDate=" + createDate + "]";
    }

    public String getRole() {
	return role;
    }

    public void setRole(String role) {
	this.role = role;
    }

}
