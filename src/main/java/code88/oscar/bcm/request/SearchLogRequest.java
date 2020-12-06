package code88.oscar.bcm.request;

import java.sql.Date;

/**
 * @FileName: SearchLogRequest.java
 * @since: 20/11/2020
 */
public class SearchLogRequest {

    private String userName;
    private Date date;

    public SearchLogRequest() {
	// TODO Auto-generated constructor stub
    }

    public SearchLogRequest(String userName, Date date) {
	super();
	this.userName = userName;
	this.date = date;
    }

    public String getUserName() {
	return userName;
    }

    public void setUserName(String userName) {
	this.userName = userName;
    }

    public Date getDate() {
	return date;
    }

    public void setDate(Date date) {
	this.date = date;
    }

    @Override
    public String toString() {
	return "SearchLogRequest [userName=" + userName + ", date=" + date + "]";
    }

}
