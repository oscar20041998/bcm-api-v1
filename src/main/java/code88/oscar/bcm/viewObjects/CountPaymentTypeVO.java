package code88.oscar.bcm.viewObjects;

/**
 * @FileName: CountPaymentTypeVO.java
 * @since: 08/11/2020
 */
public class CountPaymentTypeVO {

    private int countCardOption;
    private int countCashOption;
    private int electronicWalletOption;

    public CountPaymentTypeVO() {
	// TODO Auto-generated constructor stub
    }

    public CountPaymentTypeVO(int countCardOption, int countCashOption, int electronicWalletOption) {
	super();
	this.countCardOption = countCardOption;
	this.countCashOption = countCashOption;
	this.electronicWalletOption = electronicWalletOption;
    }

    public int getCountCardOption() {
        return countCardOption;
    }

    public void setCountCardOption(int countCardOption) {
        this.countCardOption = countCardOption;
    }

    public int getCountCashOption() {
        return countCashOption;
    }

    public void setCountCashOption(int countCashOption) {
        this.countCashOption = countCashOption;
    }

    public int getElectronicWalletOption() {
        return electronicWalletOption;
    }

    public void setElectronicWalletOption(int electronicWalletOption) {
        this.electronicWalletOption = electronicWalletOption;
    }

    @Override
    public String toString() {
	return "CountPaymentTypeVO [countCardOption=" + countCardOption + ", countCashOption=" + countCashOption
		+ ", electronicWalletOption=" + electronicWalletOption + "]";
    }
}
