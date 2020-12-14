package code88.oscar.bcm.request;

import java.time.LocalDateTime;

/**
 * @FileName: OrderDetailRequest.java
 * @since:14/12/2020
 */
public class OrderDetailRequest {

    private String productId;
    private int quantity;
    private String priceConvert;
    private String createBy;
    private LocalDateTime createDate;

    public OrderDetailRequest() {
	// TODO Auto-generated constructor stub
    }

    public OrderDetailRequest(String productId, int quantity, String priceConvert, String createBy,
	    LocalDateTime createDate) {
	super();
	this.productId = productId;
	this.quantity = quantity;
	this.priceConvert = priceConvert;
	this.createBy = createBy;
	this.createDate = createDate;
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

    public String getPriceConvert() {
	return priceConvert;
    }

    public void setPriceConvert(String priceConvert) {
	this.priceConvert = priceConvert;
    }

    public String getCreateBy() {
	return createBy;
    }

    public void setCreateBy(String createBy) {
	this.createBy = createBy;
    }

    public LocalDateTime getCreateDate() {
	return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
	this.createDate = createDate;
    }

    @Override
    public String toString() {
	return "OrderDetailRequest [productId=" + productId + ", quantity=" + quantity + ", priceConvert="
		+ priceConvert + ", createBy=" + createBy + ", createDate=" + createDate + "]";
    }

}
