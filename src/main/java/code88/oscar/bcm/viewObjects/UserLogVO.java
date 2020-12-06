package code88.oscar.bcm.viewObjects;

/**
 * @FileName: UserLogVO.java
 * @since: 18/10/2020
 */
public class UserLogVO {
    private int id;

    private String fullName;

    private String userId;

    private String idCard;

    private String dateOfBirth;

    private String address;

    private String phoneNumber;

    private String email;

    private String userAction;

    private String createdBy;

    private String createdDate;

    public UserLogVO() {
	super();
	// TODO Auto-generated constructor stub
    }

    public UserLogVO(int id, String fullName, String userId, String idCard, String dateOfBirth, String address,
	    String phoneNumber, String email, String userAction, String createdBy, String createdDate) {
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

    public String getDateOfBirth() {
	return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
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

    public String getCreatedDate() {
	return createdDate;
    }

    public void setCreatedDate(String createdDate) {
	this.createdDate = createdDate;
    }

    @Override
    public String toString() {
	return "UserLogVO [id=" + id + ", fullName=" + fullName + ", userId=" + userId + ", idCard=" + idCard
		+ ", dateOfBirth=" + dateOfBirth + ", address=" + address + ", phoneNumber=" + phoneNumber + ", email="
		+ email + ", userAction=" + userAction + ", createdBy=" + createdBy + ", createdDate=" + createdDate
		+ "]";
    }

    
}
