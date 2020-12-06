package code88.oscar.bcm.viewObjects;

/**
 * @FileName: ProductLogVO.java
 * @since: 28/10/020
 */
public class ProductLogVO {

    private int id;
    private String productName;
    private String price;
    private String userAction;
    private String createBy;
    private String createDate;

    public ProductLogVO() {
	super();
	// TODO Auto-generated constructor stub
    }

    public ProductLogVO(int id, String productName, String price, String userAction, String createBy,
	    String createDate) {
	super();
	this.id = id;
	this.productName = productName;
	this.price = price;
	this.userAction = userAction;
	this.createBy = createBy;
	this.createDate = createDate;
    }

    public int getId() {
	return id;
    }

    public void setId(int id) {
	this.id = id;
    }

    public String getProductName() {
	return productName;
    }

    public void setProductName(String productName) {
	this.productName = productName;
    }

    public String getPrice() {
	return price;
    }

    public void setPrice(String price) {
	this.price = price;
    }

    public String getUserAction() {
	return userAction;
    }

    public void setUserAction(String userAction) {
	this.userAction = userAction;
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

    @Override
    public String toString() {
	return "ProductLogVO [id=" + id + ", productName=" + productName + ", price=" + price + ", userAction="
		+ userAction + ", createBy=" + createBy + ", createDate=" + createDate + "]";
    }
}
