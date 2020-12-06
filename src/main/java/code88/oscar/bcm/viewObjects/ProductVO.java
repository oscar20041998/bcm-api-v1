package code88.oscar.bcm.viewObjects;

import java.math.BigDecimal;

/**
 * @FileName: ProductVO.java
 * @since:26/10/2020
 */
public class ProductVO {

    private String productId;
    private String categoryId;
    private String productName;
    private BigDecimal price;
    private String priceFormatString;
    private String createBy;
    private String createDate;
    public ProductVO(String productId, String categoryId, String productName, BigDecimal price,
	    String priceFormatString, String createBy, String createDate) {
	super();
	this.productId = productId;
	this.categoryId = categoryId;
	this.productName = productName;
	this.price = price;
	this.priceFormatString = priceFormatString;
	this.createBy = createBy;
	this.createDate = createDate;
    }
    public ProductVO() {
	super();
	// TODO Auto-generated constructor stub
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
    public String getPriceFormatString() {
        return priceFormatString;
    }
    public void setPriceFormatString(String priceFormatString) {
        this.priceFormatString = priceFormatString;
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
	return "ProductVO [productId=" + productId + ", categoryId=" + categoryId + ", productName=" + productName
		+ ", price=" + price + ", priceFormatString=" + priceFormatString + ", createBy=" + createBy
		+ ", createDate=" + createDate + "]";
    }
}
