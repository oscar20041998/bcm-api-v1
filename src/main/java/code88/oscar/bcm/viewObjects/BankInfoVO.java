package code88.oscar.bcm.viewObjects;

/**
 * @FileName: BankinfoVO.java
 * @since: 11/12/2020
 */
public class BankInfoVO {

    private String bankCode;
    private String bankName;
    private String status;
    private String imageContent;
    private String createBy;
    private String createDate;

    public BankInfoVO() {
	// TODO Auto-generated constructor stub
    }

    public BankInfoVO(String bankCode, String bankName, String status, String createBy, String createDate, String imageContent) {
	super();
	this.bankCode = bankCode;
	this.bankName = bankName;
	this.status = status;
	this.createBy = createBy;
	this.createDate = createDate;
	this.imageContent = imageContent;
    }

    public String getBankCode() {
	return bankCode;
    }

    public void setBankCode(String bankCode) {
	this.bankCode = bankCode;
    }

    public String getBankName() {
	return bankName;
    }

    public void setBankName(String bankName) {
	this.bankName = bankName;
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

    
    public String getImageContent() {
        return imageContent;
    }

    public void setImageContent(String imageContent) {
        this.imageContent = imageContent;
    }

    @Override
    public String toString() {
	return "BankInfoVO [bankCode=" + bankCode + ", bankName=" + bankName + ", status=" + status + ", createBy="
		+ createBy + ", createDate=" + createDate + "]";
    }

}
