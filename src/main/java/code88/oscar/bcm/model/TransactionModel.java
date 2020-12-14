package code88.oscar.bcm.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @FileName: TransactionModel.java
 * @since:31/10/2020
 */
@Entity
@Table(name = "transaction")
public class TransactionModel {

    @Id
    @Column(name = "transaction_id")
    private String transactionId;

    @Column(name = "table_id")
    private String tableId;

    @Column(name = "order_id")
    private String orderId;

    @Column(name = "total_price")
    private BigDecimal totalPrice;

    @Column(name = "status")
    private String status;

    @Column(name = "payment_type")
    private String paymentType;

    @Column(name = "card_type")
    private String cardType;

    @Column(name = "card_number")
    private String cardNumber;

    @Column(name = "bank_name")
    private String bankName;
    
    @Column(name = "expire_date_card")
    private String expireDate;
    
    @Column(name = "cvv")
    private String cvv;

    @Column(name = "provider_name")
    private String providerName;
    
    @Column(name = "transaction_code")
    private String transactionCode;
    
    @Column(name = "create_by")
    private String createBy;

    @Column(name = "create_date")
    private LocalDateTime createDate;

    public TransactionModel() {
	super();
	// TODO Auto-generated constructor stub
    }

    public TransactionModel(String transactionId, String tableId, String orderId, BigDecimal totalPrice, String status,
	    String paymentType, String cardType, String cardNumber, String bankName, String expireDate, String cvv,
	    String createBy, LocalDateTime createDate, String transactionCode, String providerName) {
	super();
	this.transactionId = transactionId;
	this.tableId = tableId;
	this.orderId = orderId;
	this.totalPrice = totalPrice;
	this.status = status;
	this.paymentType = paymentType;
	this.cardType = cardType;
	this.cardNumber = cardNumber;
	this.bankName = bankName;
	this.expireDate = expireDate;
	this.cvv = cvv;
	this.transactionCode = transactionCode;
	this.createBy = createBy;
	this.createDate = createDate;
	this.providerName = providerName;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getTableId() {
        return tableId;
    }

    public void setTableId(String tableId) {
        this.tableId = tableId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(String expireDate) {
        this.expireDate = expireDate;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
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
    

    public String getTransactionCode() {
        return transactionCode;
    }

    public void setTransactionCode(String transactionCode) {
        this.transactionCode = transactionCode;
    }

    public String getProviderName() {
        return providerName;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }

    @Override
    public String toString() {
	return "TransactionModel [transactionId=" + transactionId + ", tableId=" + tableId + ", orderId=" + orderId
		+ ", totalPrice=" + totalPrice + ", status=" + status + ", paymentType=" + paymentType + ", cardType="
		+ cardType + ", cardNumber=" + cardNumber + ", bankName=" + bankName + ", expireDate=" + expireDate
		+ ", cvv=" + cvv + ", providerName=" + providerName + ", transactionCode=" + transactionCode
		+ ", createBy=" + createBy + ", createDate=" + createDate + "]";
    }
}
