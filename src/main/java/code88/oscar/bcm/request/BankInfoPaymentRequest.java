package code88.oscar.bcm.request;

/**
 * @FileName: BankInfoPaymentRequest.java
 * @since:14/12/2020
 * */
public class BankInfoPaymentRequest {

    private String bankName;
    private String cardNumber;
    private String cardType;
    private String cardOwnerName;
    private String expireDate;
    private String cvv;
    
    public BankInfoPaymentRequest() {
	// TODO Auto-generated constructor stub
    }

    public BankInfoPaymentRequest(String bankName, String cardNumber, String cardType, String cardOwnerName,
	    String expireDate, String cvv) {
	super();
	this.bankName = bankName;
	this.cardNumber = cardNumber;
	this.cardType = cardType;
	this.cardOwnerName = cardOwnerName;
	this.expireDate = expireDate;
	this.cvv = cvv;
    }


    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getCardOwnerName() {
        return cardOwnerName;
    }

    public void setCardOwnerName(String cardOwnerName) {
        this.cardOwnerName = cardOwnerName;
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

    
}
