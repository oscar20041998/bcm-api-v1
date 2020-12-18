package code88.oscar.bcm.viewObjects;

/**
 * @FileName: OrderDetailVO.java
 * @since: 14/12/2020
 */
public class OrderDetailVO {

    private String orderId;
    private String productName;
    private int quantity;
    private String price;
    private String createBy;
    private String createDate;

    public OrderDetailVO() {
	// TODO Auto-generated constructor stub
    }

    public OrderDetailVO(String orderId, String productName, int quantity, String price, String createBy,
	    String createDate) {
	super();
	this.orderId = orderId;
	this.productName = productName;
	this.quantity = quantity;
	this.price = price;
	this.createBy = createBy;
	this.createDate = createDate;
    }

    public String getOrderId() {
	return orderId;
    }

    public void setOrderId(String orderId) {
	this.orderId = orderId;
    }

    public String getProductName() {
	return productName;
    }

    public void setProductName(String productName) {
	this.productName = productName;
    }

    public int getQuantity() {
	return quantity;
    }

    public void setQuantity(int quantity) {
	this.quantity = quantity;
    }

    public String getPrice() {
	return price;
    }

    public void setPrice(String price) {
	this.price = price;
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
	return "OrderDetailVO [orderId=" + orderId + ", productName=" + productName + ", quantity=" + quantity
		+ ", price=" + price + ", createBy=" + createBy + ", createDate=" + createDate + "]";
    }

}
