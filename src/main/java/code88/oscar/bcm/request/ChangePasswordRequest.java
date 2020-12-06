package code88.oscar.bcm.request;

/**
 * @FileName: ChangePasswordRequest.java
 * @since: 14/10/2020
 * */
public class ChangePasswordRequest {

    private String userId; 
    private String accountId;
    private String currentPass;
    private String newPass;
    private String confirmNewPass;
    public ChangePasswordRequest() {
	super();
	// TODO Auto-generated constructor stub
    }
    public ChangePasswordRequest(String userId, String accountId, String currentPass, String newPass,
	    String confirmNewPass) {
	super();
	this.userId = userId;
	this.accountId = accountId;
	this.currentPass = currentPass;
	this.newPass = newPass;
	this.confirmNewPass = confirmNewPass;
    }
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getAccountId() {
        return accountId;
    }
    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }
    public String getCurrentPass() {
        return currentPass;
    }
    public void setCurrentPass(String currentPass) {
        this.currentPass = currentPass;
    }
    public String getNewPass() {
        return newPass;
    }
    public void setNewPass(String newPass) {
        this.newPass = newPass;
    }
    public String getConfirmNewPass() {
        return confirmNewPass;
    }
    public void setConfirmNewPass(String confirmNewPass) {
        this.confirmNewPass = confirmNewPass;
    }
    
}
