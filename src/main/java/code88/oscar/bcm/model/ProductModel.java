package code88.oscar.bcm.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.sun.istack.NotNull;

/**
 * @FileName: ProductModel.java
 * @since: 22/10/2020
 */
@Entity
@Table(name = "product")
public class ProductModel {

    @Id
    @Column(name = "product_id")
    private String productId;

    @Column(name = "category_id")
    private String categoryId;

    @NotNull
    @Column(name = "product_name")
    private String productName;

    @Lob
    @Column(name = "image", columnDefinition ="LONGBLOB")
    private byte[] image;

    @Transient
    @Column(name = "image_name")
    private String imageName;

    @NotNull
    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "create_by")
    private String createBy;

    @Column(name = "create_date")
    private LocalDateTime createDate;

    public ProductModel(String productId, String categoryId, String productName, BigDecimal price, String createBy,
	    LocalDateTime createDate, byte[] image, String imageName) {
	super();
	this.productId = productId;
	this.categoryId = categoryId;
	this.productName = productName;
	this.price = price;
	this.createBy = createBy;
	this.createDate = createDate;
	this.image = image;
	this.imageName = imageName;
    }

    public ProductModel() {
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

    public byte[] getImage() {
	return image;
    }

    public void setImage(byte[] image) {
	this.image = image;
    }

    public String getImageName() {
	return imageName;
    }

    public void setImageName(String imageName) {
	this.imageName = imageName;
    }

    @Override
    public String toString() {
	return "ProductModel [productId=" + productId + ", categoryId=" + categoryId + ", productName=" + productName
		+ ", price=" + price + ", createBy=" + createBy + ", createDate=" + createDate + "]";
    }

}
