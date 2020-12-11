package code88.oscar.bcm.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @FileName: ElectronicModel.java
 * @since: 11/12/2020
 */
@Entity
@Table(name = "electronic_wallet")
public class ElectronicWalletModel {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "wallet_name")
    private String wallet_name;

    @Column(name = "status")
    private String status;

    @Column(name = "create_by")
    private String create_by;

    @Column(name = "create_date")
    private LocalDateTime createDate;

    public ElectronicWalletModel() {
	// TODO Auto-generated constructor stub
    }

    public ElectronicWalletModel(int id, String wallet_name, String status, String create_by,
	    LocalDateTime createDate) {
	super();
	this.id = id;
	this.wallet_name = wallet_name;
	this.status = status;
	this.create_by = create_by;
	this.createDate = createDate;
    }

    public int getId() {
	return id;
    }

    public void setId(int id) {
	this.id = id;
    }

    public String getWallet_name() {
	return wallet_name;
    }

    public void setWallet_name(String wallet_name) {
	this.wallet_name = wallet_name;
    }

    public String getStatus() {
	return status;
    }

    public void setStatus(String status) {
	this.status = status;
    }

    public String getCreate_by() {
	return create_by;
    }

    public void setCreate_by(String create_by) {
	this.create_by = create_by;
    }

    public LocalDateTime getCreateDate() {
	return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
	this.createDate = createDate;
    }

    @Override
    public String toString() {
	return "ElectronicWalletModel [id=" + id + ", wallet_name=" + wallet_name + ", status=" + status
		+ ", create_by=" + create_by + ", createDate=" + createDate + "]";
    }

}
