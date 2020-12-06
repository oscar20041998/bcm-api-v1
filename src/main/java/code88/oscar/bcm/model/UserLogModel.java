package code88.oscar.bcm.model;

import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @FileName: UserLogModel.java
 * @since: 18/10/2020
 */

@Entity
@Table(name = "user_log")
public class UserLogModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "id_card")
    private String idCard;

    @Column(name = "date_of_birth")
    private Date dateOfBirth;

    @Column(name = "address")
    private String address;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "email")
    private String email;

    @Column(name = "user_action")
    private String userAction;

    @Column(name = "create_by")
    private String createdBy;

    @Column(name = "create_date")
    private LocalDateTime createdDate;

    public UserLogModel() {
	super();
	// TODO Auto-generated constructor stub
    }

    public UserLogModel(int id, String fullName, String userId, String idCard, Date dateOfBirth, String address,
	    String phoneNumber, String email, String userAction, String createdBy, LocalDateTime createdDate) {
	super();
	this.id = id;
	this.fullName = fullName;
	this.userId = userId;
	this.idCard = idCard;
	this.dateOfBirth = dateOfBirth;
	this.address = address;
	this.phoneNumber = phoneNumber;
	this.email = email;
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

    public String getFullName() {
	return fullName;
    }

    public void setFullName(String fullName) {
	this.fullName = fullName;
    }

    public String getUserId() {
	return userId;
    }

    public void setUserId(String userId) {
	this.userId = userId;
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

    public void setDateOfBirth(Date dateOfBirth) {
	this.dateOfBirth = dateOfBirth;
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

}
