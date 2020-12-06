package code88.oscar.bcm.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @FileName: OrderProducModel.java
 * @since: 22/10/2020
 */
@Entity
@Table(name = "order_product")
public class OrderProductModel {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "position_id")
    private String positionId;

    @Column(name = "product_id")
    private String productId;

    @Column(name = "product_name", nullable = true)
    private String productName;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "status_product")
    private String statusProduct;

    @Column(name = "create_by")
    private String createBy;

    @Column(name = "create_date")
    private LocalDateTime createDate;

    public OrderProductModel() {
	super();
	// TODO Auto-generated constructor stub
    }

    public OrderProductModel(int id, String positionId, String productId, int quantity, BigDecimal price,
	    String createBy, LocalDateTime createDate, String statusProduct) {
	super();
	this.id = id;
	this.positionId = positionId;
	this.productId = productId;
	this.quantity = quantity;
	this.price = price;
	this.createBy = createBy;
	this.createDate = createDate;
	this.statusProduct = statusProduct;
    }

    public int getId() {
	return id;
    }

    public void setId(int id) {
	this.id = id;
    }

    public String getPositionId() {
	return positionId;
    }

    public void setPositionId(String positionId) {
	this.positionId = positionId;
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

    public LocalDateTime getCreateDate() {
	return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
	this.createDate = createDate;
    }

    public String getStatusProduct() {
	return statusProduct;
    }

    public void setStatusProduct(String statusProduct) {
	this.statusProduct = statusProduct;
    }

    public String getProductName() {
	return productName;
    }

    public void setProductName(String productName) {
	this.productName = productName;
    }

    @Override
    public String toString() {
	return "OrderProductModel [id=" + id + ", positionId=" + positionId + ", productId=" + productId
		+ ", productName=" + productName + ", quantity=" + quantity + ", price=" + price + ", statusProduct="
		+ statusProduct + ", createBy=" + createBy + ", createDate=" + createDate + "]";
    }

}
