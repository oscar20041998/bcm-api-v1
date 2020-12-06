package code88.oscar.bcm.request;

/**
 * @FileName: SaveAccountUserRequest.java
 * @since: 10/10/2020
 */
public class SaveAccountUserRequest {

	private String accountIdValid;
	private String accountId;
	private String userId;
	private String userName;
	private String roleCode;
	private String passWord;
	private String status;
	private String createBy;
	private String createDate;

	public SaveAccountUserRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SaveAccountUserRequest(String accountIdValid, String accountId, String userId, String userName, String passWord,
			String status, String createBy, String createDate, String roleCode) {
		super();
		this.accountIdValid = accountIdValid;
		this.accountId = accountId;
		this.userId = userId;
		this.userName = userName;
		this.passWord = passWord;
		this.roleCode = roleCode;
		this.status = status;
		this.createBy = createBy;
		this.createDate = createDate;
	}

	public String getAccountIdValid() {
		return accountIdValid;
	}

	public void setAccountIdValid(String userIdValid) {
		this.accountIdValid = userIdValid;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	@Override
	public String toString() {
		return "SaveAccountUserRequest [accountIdValid=" + accountIdValid + ", accountId=" + accountId + ", userId=" + userId
				+ ", userName=" + userName + ", roleCode=" + roleCode + ", passWord=" + passWord + ", status=" + status
				+ ", createBy=" + createBy + ", createDate=" + createDate + "]";
	}

}
