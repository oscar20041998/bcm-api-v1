package code88.oscar.bcm.request;

/**
 * @FileName: SaveTransactionRequest.java
 * @since:07/11/2020
 */
public class SaveTransactionRequest {
    private String totalPrice;
    private String tableId;
    private String paymentType;
    private String cardType;
    private String cardNumber;
    private String bankName;
    private String createBy;

    public SaveTransactionRequest() {
	super();
	// TODO Auto-generated constructor stub
    }

    public SaveTransactionRequest(String totalPrice, String tableId, String paymentType, String cardType,
	    String cardNumber, String createBy, String bankName) {
	super();
	this.totalPrice = totalPrice;
	this.tableId = tableId;
	this.paymentType = paymentType;
	this.cardType = cardType;
	this.cardNumber = cardNumber;
	this.createBy = createBy;
	this.bankName = bankName;
    }

    public String getTotalPrice() {
	return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
	this.totalPrice = totalPrice;
    }

    public String getTableId() {
	return tableId;
    }

    public void setTableId(String tableId) {
	this.tableId = tableId;
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

    public String getBankName() {
	return bankName;
    }

    public void setBankName(String bankName) {
	this.bankName = bankName;
    }

    @Override
    public String toString() {
	return "SaveTransactionRequest [totalPrice=" + totalPrice + ", tableId=" + tableId + ", paymentType="
		+ paymentType + ", cardType=" + cardType + ", cardNumber=" + cardNumber + ", bankName=" + bankName
		+ ", createBy=" + createBy + "]";
    }

}
