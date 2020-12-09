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
 * @FileName: ProductLogModel.java
 * @since: 28/10/2020
 */
@Entity
@Table(name = "product_log")
public class ProductLogModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "action_user")
    private String actionUser;

    @Column(name = "create_by")
    private String createBy;

    @Column(name = "create_date")
    private LocalDateTime createDate;

    public ProductLogModel() {
	super();
	// TODO Auto-generated constructor stub
    }

    public ProductLogModel(int id, String productName, BigDecimal price, String actionUser, String createBy,
	    LocalDateTime createDate) {
	super();
	this.id = id;
	this.productName = productName;
	this.price = price;
	this.actionUser = actionUser;
	this.createBy = createBy;
	this.createDate = createDate;
    }

    public int getId() {
	return id;
    }

    public void setId(int id) {
	this.id = id;
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

    public String getActionUser() {
	return actionUser;
    }

    public void setActionUser(String actionUser) {
	this.actionUser = actionUser;
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
	return "ProductLogModel [id=" + id + ", productName=" + productName + ", price=" + price + ", actionUser="
		+ actionUser + ", createBy=" + createBy + ", createDate=" + createDate + "]";
    }
}
