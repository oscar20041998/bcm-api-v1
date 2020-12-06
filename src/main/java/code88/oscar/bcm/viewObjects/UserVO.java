package code88.oscar.bcm.viewObjects;

import java.util.Date;

/**
 * @FileName:UserVO.java
 * @since: 17/10/2020
 * */
public class UserVO {
    private String userId;
    private String firstName;
    private String lastName;
    private String midleName;
    private String fullName;
    private String idCard;
    private Date dateOfBirth;
    private String address;
    private String phoneNumber;
    private String email;
    private String createdBy;
    private String createdDate;

    public UserVO() {
	super();
	// TODO Auto-generated constructor stub
    }

    public UserVO(String userId, String firstName, String lastName, String midleName, String idCard, Date dateOfBirth,
	    String address, String phoneNumber, String email, String createdBy, String createdDate, String fullName) {
	super();
	this.userId = userId;
	this.firstName = firstName;
	this.lastName = lastName;
	this.midleName = midleName;
	this.idCard = idCard;
	this.dateOfBirth = dateOfBirth;
	this.address = address;
	this.phoneNumber = phoneNumber;
	this.email = email;
	this.fullName = fullName;
	this.createdBy = createdBy;
	this.createdDate = createdDate;
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

    public Date getDateOfBirth() {
	return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBrith) {
	this.dateOfBirth = dateOfBrith;
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

    public String getFullName() {
	return fullName;
    }

    public void setFullName(String fullName) {
	this.fullName = fullName;
    }

    @Override
    public String toString() {
	return "UserVO [userId=" + userId + ", firstName=" + firstName + ", lastName=" + lastName + ", midleName="
		+ midleName + ", fullName=" + fullName + ", idCard=" + idCard + ", dateOfBirth=" + dateOfBirth
		+ ", address=" + address + ", phoneNumber=" + phoneNumber + ", email=" + email + ", createdBy="
		+ createdBy + ", createdDate=" + createdDate + "]";
    }
}
