package code88.oscar.bcm.viewObjects;

/**
 * @FileName: TransactionVO.java
 * @since: 07/11/2020
 */
public class TransactionVO {
    private String transactionId;

    private String tableId;

    private String orderId;

    private String totalPrice;

    private String status;

    private String paymentType;

    private String cardType;

    private String cardNumber;

    private String bankName;

    private String createBy;

    private String createDate;

    public TransactionVO(String transactionId, String tableId, String orderId, String totalPrice, String status,
	    String paymentType, String cardType, String cardNumber, String createBy, String createDate,
	    String bankName) {
	super();
	this.transactionId = transactionId;
	this.tableId = tableId;
	this.orderId = orderId;
	this.totalPrice = totalPrice;
	this.status = status;
	this.paymentType = paymentType;
	this.cardType = cardType;
	this.cardNumber = cardNumber;
	this.createBy = createBy;
	this.createDate = createDate;
	this.bankName = bankName;
    }

    public TransactionVO() {
	super();
	// TODO Auto-generated constructor stub
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

    public String getBankName() {
	return bankName;
    }

    public void setBankName(String bankName) {
	this.bankName = bankName;
    }

    @Override
    public String toString() {
	return "TransactionVO [transactionId=" + transactionId + ", tableId=" + tableId + ", orderId=" + orderId
		+ ", totalPrice=" + totalPrice + ", status=" + status + ", paymentType=" + paymentType + ", cardType="
		+ cardType + ", cardNumber=" + cardNumber + ", bankName=" + bankName + ", createBy=" + createBy
		+ ", createDate=" + createDate + "]";
    }

}
