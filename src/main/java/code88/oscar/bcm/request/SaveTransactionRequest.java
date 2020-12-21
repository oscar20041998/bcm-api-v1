package code88.oscar.bcm.request;

import java.util.List;

/**
 * @FileName: SaveTransactionRequest.java
 * @since:07/11/2020
 */
public class SaveTransactionRequest {

    private String userId;
    private String totalPrice;
    private String tableId;
    private String paymentType;
    private BankInfoPaymentRequest bankInfoRequest;
    private EWalletPaymentRequest eWalletRequest;
    private List<OrderDetailRequest> listOrder;
    private String customerEmail;
    private String createBy;

    public SaveTransactionRequest() {
	super();
	// TODO Auto-generated constructor stub
    }

    public SaveTransactionRequest(String totalPrice, String tableId, String paymentType,
	    BankInfoPaymentRequest bankInfoRequest, EWalletPaymentRequest eWalletRequest,
	    List<OrderDetailRequest> listOrder, String createBy, String customerEmail, String userId) {
	super();
	this.totalPrice = totalPrice;
	this.tableId = tableId;
	this.paymentType = paymentType;
	this.bankInfoRequest = bankInfoRequest;
	this.eWalletRequest = eWalletRequest;
	this.listOrder = listOrder;
	this.createBy = createBy;
	this.customerEmail = customerEmail;
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

    public BankInfoPaymentRequest getBankInfoRequest() {
	return bankInfoRequest;
    }

    public void setBankInfoRequest(BankInfoPaymentRequest bankInfoRequest) {
	this.bankInfoRequest = bankInfoRequest;
    }

    public EWalletPaymentRequest geteWalletRequest() {
	return eWalletRequest;
    }

    public void seteWalletRequest(EWalletPaymentRequest eWalletRequest) {
	this.eWalletRequest = eWalletRequest;
    }

    public List<OrderDetailRequest> getListOrder() {
	return listOrder;
    }

    public void setListOrder(List<OrderDetailRequest> listOrder) {
	this.listOrder = listOrder;
    }

    public String getCreateBy() {
	return createBy;
    }

    public void setCreateBy(String createBy) {
	this.createBy = createBy;
    }

    public String getCustomerEmail() {
	return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
	this.customerEmail = customerEmail;
    }
    
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
	return "SaveTransactionRequest [totalPrice=" + totalPrice + ", tableId=" + tableId + ", paymentType="
		+ paymentType + ", bankInfoRequest=" + bankInfoRequest + ", eWalletRequest=" + eWalletRequest
		+ ", listOrder=" + listOrder + ", createBy=" + createBy + "]";
    }

}
