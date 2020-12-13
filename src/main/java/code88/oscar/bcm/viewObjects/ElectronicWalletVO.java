package code88.oscar.bcm.viewObjects;

/**
 * @FileName: ElectronicWallet.java
 * @since: 11/12/2020
 */
public class ElectronicWalletVO {

    private int id;
    private String walletName;
    private String status;
    private String imageContent;
    private String createBy;
    private String createDate;

    public ElectronicWalletVO() {
	// TODO Auto-generated constructor stub
    }

    public ElectronicWalletVO(int id, String walletName, String status, String createBy, String imageContent,String createDate) {
	super();
	this.id = id;
	this.walletName = walletName;
	this.status = status;
	this.createBy = createBy;
	this.createDate = createDate;
	this.imageContent = imageContent;
    }

    public int getId() {
	return id;
    }

    public void setId(int id) {
	this.id = id;
    }

    public String getWalletName() {
	return walletName;
    }

    public void setWalletName(String walletName) {
	this.walletName = walletName;
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
	return "ElectronicWalletVO [id=" + id + ", walletName=" + walletName + ", status=" + status + ", createBy="
		+ createBy + ", createDate=" + createDate + "]";
    }

}
