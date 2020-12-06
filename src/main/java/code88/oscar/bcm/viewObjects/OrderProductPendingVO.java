package code88.oscar.bcm.viewObjects;

/**
 * @FileName: OrderProductPendingVO.java
 * @since:08/11/2020
 */
public class OrderProductPendingVO {

    private String tableId;
    private String productId;
    private String productName;
    private int quantity;
    private String status;
    private String createBy;
    private String createDate;

    public OrderProductPendingVO() {
	// TODO Auto-generated constructor stub
    }

    public OrderProductPendingVO(String tableId, String productId, String productName, int quantity, String createBy,
	    String createDate, String status) {
	super();
	this.tableId = tableId;
	this.productId = productId;
	this.productName = productName;
	this.quantity = quantity;
	this.createBy = createBy;
	this.createDate = createDate;
	this.status = status;
    }

    public String getTableId() {
	return tableId;
    }

    public void setTableId(String tableId) {
	this.tableId = tableId;
    }

    public String getProductId() {
	return productId;
    }

    public void setProductId(String productId) {
	this.productId = productId;
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

    public String getStatus() {
	return status;
    }

    public void setStatus(String status) {
	this.status = status;
    }

    @Override
    public String toString() {
	return "OrderProductPendingVO [tableId=" + tableId + ", productId=" + productId + ", productName=" + productName
		+ ", quantity=" + quantity + ", status=" + status + ", createBy=" + createBy + ", createDate="
		+ createDate + "]";
    }

}
