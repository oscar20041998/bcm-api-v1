package code88.oscar.bcm.request;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @FileName: SaveProductRequest.java
 * @since: 27/10/2020
 * */
public class SaveProductRequest {

    private String productId;
    private String categoryId;
    private String productName;
    private BigDecimal price;
    private String createBy;
    private LocalDateTime createDate;
    public SaveProductRequest() {
	super();
	// TODO Auto-generated constructor stub
    }
    public SaveProductRequest(String productId, String categoryId, String productName, BigDecimal price,
	    String createBy, LocalDateTime createDate) {
	super();
	this.productId = productId;
	this.categoryId = categoryId;
	this.productName = productName;
	this.price = price;
	this.createBy = createBy;
	this.createDate = createDate;
    }
    public String getProductId() {
        return productId;
    }
    public void setProductId(String productId) {
        this.productId = productId;
    }
    public String getCategoryId() {
        return categoryId;
    }
    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }
    public String getProductName() {
        return productName;
    }
    public void setProductName(String productName) {
        this.productName = productName;
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
    public LocalDateTime getCreateDate() {
        return createDate;
    }
    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }
    
    
}
