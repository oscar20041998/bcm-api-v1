package code88.oscar.bcm.request;

/**
 * @FileName: EWalletPaymentRequest.java
 * @since:14/12/2020
 */
public class EWalletPaymentRequest {

    private String providerName;
    private String transactionCode;

    public EWalletPaymentRequest() {
	// TODO Auto-generated constructor stub
    }

    public EWalletPaymentRequest(String transactionCode, String providerName) {
	super();
	this.transactionCode = transactionCode;
	this.providerName = providerName;
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
	return "EWalletPaymentRequest [providerName=" + providerName + ", transactionCode=" + transactionCode + "]";
    }

}
