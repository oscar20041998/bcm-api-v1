package code88.oscar.bcm.viewObjects;

import java.math.BigDecimal;

/**
 * @FileName: OrderedVO.java
 * @since:1/11/2020
 */
public class OrderedVO {

    private String productId;
    private String productName;
    private String priceConvert;
    private int quantity;
    private BigDecimal price;
    private String statusProduct;

    public OrderedVO() {
	super();
	// TODO Auto-generated constructor stub
    }

    public OrderedVO(String productId, String productName, String priceConvert, int quantity, BigDecimal price,
	    String statusProduct) {
	super();
	this.productId = productId;
	this.productName = productName;
	this.priceConvert = priceConvert;
	this.quantity = quantity;
	this.price = price;
	this.statusProduct = statusProduct;
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

    public String getPriceConvert() {
	return priceConvert;
    }

    public void setPriceConvert(String priceConvert) {
	this.priceConvert = priceConvert;
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

    public String getStatusProduct() {
	return statusProduct;
    }

    public void setStatusProduct(String statusProduct) {
	this.statusProduct = statusProduct;
    }

    @Override
    public String toString() {
	return "OrderedVO [productId=" + productId + ", productName=" + productName + ", priceConvert=" + priceConvert
		+ ", quantity=" + quantity + ", price=" + price + ", statusProduct=" + statusProduct + "]";
    }

}
