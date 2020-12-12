package code88.oscar.bcm.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @FileName: CardTypeModel.java
 * @since: 11/12/2020
 */
@Entity
@Table(name = "card_type")
public class CardTypeModel {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "card_type")
    private String cardType;

    @Column(name = "card_name")
    private String cardName;

    @Column(name = "status")
    private String status;

    @Column(name = "create_by")
    private String createBy;

    @Column(name = "create_date")
    private LocalDateTime createDate;

    public CardTypeModel() {
	// TODO Auto-generated constructor stub
    }

    public CardTypeModel(int id, String cardType, String cardName, String createBy, LocalDateTime createDate,
	    String status) {
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

    public LocalDateTime getCreateDate() {
	return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
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
	return "CardTypeModel [id=" + id + ", cardType=" + cardType + ", cardName=" + cardName + ", status=" + status
		+ ", createBy=" + createBy + ", createDate=" + createDate + "]";
    }

}
