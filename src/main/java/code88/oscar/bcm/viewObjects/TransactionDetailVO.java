package code88.oscar.bcm.viewObjects;

/**
 * @FileName: TransactionDetailVO.java
 * @since: 18/12/2020
 */
public class TransactionDetailVO {

    private String transactionId;
    private String orderId;
    private String tableId;
    private String totalPrice;
    private String status;
    private String paymentType;
    private String bankName;
    private String cardType;
    private String cardNumber;
    private String expireDate;
    private String cvv;
    private String providerName;
    private String transactionCode;
    private String emailCustomer;
    private String createBy;
    private String createDate;

    public TransactionDetailVO() {
	// TODO Auto-generated constructor stub
    }

    public TransactionDetailVO(String transactionId, String orderId, String tableId, String totalPrice, String status,
	    String paymentType, String bankName, String cardType, String cardNumber, String expireDate, String cvv,
	    String providerName, String transactionCode, String createBy, String createDate, String emailCustomer) {
	super();
	this.transactionId = transactionId;
	this.emailCustomer = emailCustomer;
	this.orderId = orderId;
	this.tableId = tableId;
	this.totalPrice = totalPrice;
	this.status = status;
	this.paymentType = paymentType;
	this.bankName = bankName;
	this.cardType = cardType;
	this.cardNumber = cardNumber;
	this.expireDate = expireDate;
	this.cvv = cvv;
	this.providerName = providerName;
	this.transactionCode = transactionCode;
	this.createBy = createBy;
	this.createDate = createDate;
    }

    public String getTransactionId() {
	return transactionId;
    }

    public void setTransactionId(String transactionId) {
	this.transactionId = transactionId;
    }

    public String getOrderId() {
	return orderId;
    }

    public void setOrderId(String orderId) {
	this.orderId = orderId;
    }

    public String getTableId() {
	return tableId;
    }

    public void setTableId(String tableId) {
	this.tableId = tableId;
    }

    public String getTotalPrice() {
	return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
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

    public String getBankName() {
	return bankName;
    }

    public void setBankName(String bankName) {
	this.bankName = bankName;
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

    public String getProviderName() {
	return providerName;
    }

    public void setProviderName(String providerName) {
	this.providerName = providerName;
    }

    public String getTransactionCode() {
	return transactionCode;
    }

    public void setTransactionCode(String transactionCode) {
	this.transactionCode = transactionCode;
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

    public String getEmailCustomer() {
	return emailCustomer;
    }

    public void setEmailCustomer(String emailCustomer) {
	this.emailCustomer = emailCustomer;
    }

    @Override
    public String toString() {
	return "TransactionDetailVO [transactionId=" + transactionId + ", orderId=" + orderId + ", tableId=" + tableId
		+ ", totalPrice=" + totalPrice + ", status=" + status + ", paymentType=" + paymentType + ", bankName="
		+ bankName + ", cardType=" + cardType + ", cardNumber=" + cardNumber + ", expireDate=" + expireDate
		+ ", cvv=" + cvv + ", providerName=" + providerName + ", transactionCode=" + transactionCode
		+ ", createBy=" + createBy + ", createDate=" + createDate + "]";
    }

}
