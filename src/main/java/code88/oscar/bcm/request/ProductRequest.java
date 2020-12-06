package code88.oscar.bcm.request;

import java.math.BigDecimal;

/**
 * @FileName: ProductRequest.java
 * @since: 29/10/2020
 */
public class ProductRequest {

    private String productId;
    private int quantity;
    private BigDecimal price;

    public ProductRequest() {
	super();
	// TODO Auto-generated constructor stub
    }

    public ProductRequest(String productId, int quantity, BigDecimal price) {
	super();
	this.productId = productId;
	this.quantity = quantity;
	this.price = price;
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
}
