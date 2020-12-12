package code88.oscar.bcm.viewObjects;

/**
 * @FileName: CardTypeVO.java
 * @since: 11/12/2020
 */
public class CardTypeVO {

    private int id;
    private String cardType;
    private String cardName;
    private String status;
    private String createBy;
    private String createDate;

    public CardTypeVO() {
	// TODO Auto-generated constructor stub
    }

    public CardTypeVO(int id, String cardType, String cardName, String createBy, String createDate, String status) {
	super();
	this.id = id;
	this.cardType = cardType;
	this.cardName = cardName;
	this.createBy = createBy;
	this.createDate = createDate;
	this.status = status;
    }

    public int getId() {
	return id;
    }

    public void setId(int id) {
	this.id = id;
    }

    public String getCardType() {
	return cardType;
    }

    public void setCardType(String cardType) {
	this.cardType = cardType;
    }

    public String getCardName() {
	return cardName;
    }

    public void setCardName(String cardName) {
	this.cardName = cardName;
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

    public String getStatus() {
	return status;
    }

    public void setStatus(String status) {
	this.status = status;
    }

    @Override
    public String toString() {
	return "CardTypeVO [id=" + id + ", cardType=" + cardType + ", cardName=" + cardName + ", status=" + status
		+ ", createBy=" + createBy + ", createDate=" + createDate + "]";
    }

}
