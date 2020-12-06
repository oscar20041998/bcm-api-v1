package code88.oscar.bcm.request;

/**
 * @FileName: LogInRequest.java
 * @since: 11/10/2020
 * */
public class LogInRequest {

    private String userName;
    private String passWord;

    public LogInRequest(String userName, String passWord) {
	super();
	this.userName = userName;
	this.passWord = passWord;
    }

    public LogInRequest() {
	super();
	// TODO Auto-generated constructor stub
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
}
