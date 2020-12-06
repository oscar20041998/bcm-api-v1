package code88.oscar.bcm.request;

import java.math.BigDecimal;

/**
 * @FileName: OrderProductRequest.java
 * @since: 29/10/2020
 */
public class OrderProductRequest {

    private String tableId;
    private String productId;
    private int quantity;
    private BigDecimal price;
    private String createBy;
    public OrderProductRequest() {
	super();
	// TODO Auto-generated constructor stub
    }
    public OrderProductRequest(String tableId, String productId, int quantity, BigDecimal price, String createBy) {
	super();
	this.tableId = tableId;
	this.productId = productId;
	this.quantity = quantity;
	this.price = price;
	this.createBy = createBy;
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
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public BigDecimal getPrice() {
        return price;
    }
    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    public String getCreateBy() {
        return createBy;
    }
    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }
    @Override
    public String toString() {
	return "OrderProductRequest [tableId=" + tableId + ", productId=" + productId + ", quantity=" + quantity
		+ ", price=" + price + ", createBy=" + createBy + "]";
    }
}
