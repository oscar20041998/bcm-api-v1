package code88.oscar.bcm.model;

import java.sql.Date;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.sun.istack.NotNull;

/**
 * @FileName: UserModel.java
 * @since : 10/10/2020
 */
@Entity
@Table(name = "user")
public class UserModel {

    @Id
    @Column(name = "user_id")
    private String userId;

    @NotNull
    @Column(name = "first_name")
    private String fristName;

    @NotNull
    @Column(name = "midle_name")
    private String midleName;

    @NotNull
    @Column(name = "last_name")
    private String lastName;

    @NotNull
    @Column(name = "date_of_birth")
    private Date dateOfBirth;

    @NotNull
    @Column(name = "id_card")
    private String idCard;

    @NotNull
    @Column(name = "address")
    private String address;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "email")
    private String email;

    @Column(name = "create_by")
    private String createBy;

    @Column(name = "create_date")
    private LocalDateTime createDate;

    public UserModel() {
	super();
	// TODO Auto-generated constructor stub
    }

    public UserModel(String userId, String fristName, String midleName, String lastName, Date dateOfBirth,
	    String idCard, String address, String phoneNumber, String email, String createBy,
	    LocalDateTime createDate) {
	super();
	this.userId = userId;
	this.fristName = fristName;
	this.midleName = midleName;
	this.lastName = lastName;
	this.dateOfBirth = dateOfBirth;
	this.idCard = idCard;
	this.address = address;
	this.phoneNumber = phoneNumber;
	this.email = email;
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

    @Override
    public String toString() {
	return "UserModel [userId=" + userId + ", fristName=" + fristName + ", midleName=" + midleName + ", lastName="
		+ lastName + ", dateOfBirth=" + dateOfBirth + ", idCard=" + idCard + ", address=" + address
		+ ", phoneNumber=" + phoneNumber + ", email=" + email + ", createBy=" + createBy + ", createDate="
		+ createDate + "]";
    }

}