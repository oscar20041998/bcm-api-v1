package code88.oscar.bcm.request;

/**
 * @FileName: TransactionDetailRequest.java
 * @since: 18/12/2020
 */
public class TransactionDetailRequest {

    private String transactionId;
    private String orderId;
    private String tableId;

    public TransactionDetailRequest() {
	// TODO Auto-generated constructor stub
    }

    public TransactionDetailRequest(String transactionId, String orderId, String tableId) {
	super();
	this.transactionId = transactionId;
	this.orderId = orderId;
	this.tableId = tableId;
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

    @Override
    public String toString() {
	return "TransactionDetailRequest [transactionId=" + transactionId + ", orderId=" + orderId + ", tableId="
		+ tableId + "]";
    }

}
