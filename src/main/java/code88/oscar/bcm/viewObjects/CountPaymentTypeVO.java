package code88.oscar.bcm.viewObjects;

/**
 * @FileName: CountPaymentTypeVO.java
 * @since: 08/11/2020
 */
public class CountPaymentTypeVO {

    private int countCardOption;
    private int countCashOption;

    public CountPaymentTypeVO() {
	// TODO Auto-generated constructor stub
    }

    public CountPaymentTypeVO(int countCardOption, int countCashOption) {
	super();
	this.countCardOption = countCardOption;
	this.countCashOption = countCashOption;
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

    @Override
    public String toString() {
	return "CountPaymentTypeVO [countCardOption=" + countCardOption + ", countCashOption=" + countCashOption + "]";
    }
}
