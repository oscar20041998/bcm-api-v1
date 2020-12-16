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
 * @FileName: OrderDetailModel.java
 * @since: 14/12/2020
 */
@Entity
@Table(name = "order_detail")
public class OrderDetailModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    
    @Column(name = "order_id")
    private String orderId;
    
    @Column(name = "table_id")
    private String tableId;
    
    @Column(name = "product_id")
    private String productId;
    
    @Column(name = "quantity")
    private int quantity;
    
    @Column(name = "price")
    private BigDecimal price;
    
    @Column(name = "create_by")
    private String createBy;
    
    @Column(name = "create_date")
    private LocalDateTime createDate;

    public OrderDetailModel() {
	// TODO Auto-generated constructor stub
    }

    public OrderDetailModel(int id, String tableId, String productId, int quantity,
	    BigDecimal price, String createBy, LocalDateTime createDate, String orderId) {
	super();
	this.id = id;
	this.tableId = tableId;
	this.orderId = orderId;
	this.productId = productId;
	this.quantity = quantity;
	this.price = price;
	this.createBy = createBy;
	this.createDate = createDate;
    }

    public int getId() {
	return id;
    }

    public void setId(int id) {
	this.id = id;
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

    public LocalDateTime getCreateDate() {
	return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
	this.createDate = createDate;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    @Override
    public String toString() {
	return "OrderDetailModel [id=" + id + ", orderId=" + orderId + ", tableId=" + tableId + ", productId="
		+ productId  + ", quantity=" + quantity + ", price=" + price
		+ ", createBy=" + createBy + ", createDate=" + createDate + "]";
    }

}
