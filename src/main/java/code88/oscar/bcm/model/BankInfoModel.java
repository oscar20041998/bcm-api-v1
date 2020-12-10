package code88.oscar.bcm.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @FileName: BankInfo.java
 * @since: 10/12/2020
 * */
@Entity
@Table(name = "bank_info")
public class BankInfoModel {

    @Id
    @Column(name = "bank_code")
    private String bankCode;
    
    @Column(name = "bank_name")
    private String bankName;
    
    @Column(name = "status")
    private String status;
    
    @Column(name = "create_by")
    private String createBy;
    
    @Column(name = "create_date")
    private LocalDateTime createDate;
    
    public BankInfoModel() {
	// TODO Auto-generated constructor stub
    }

    public BankInfoModel(String bankCode, String bankName, String status, String createBy, LocalDateTime createDate) {
	super();
	this.bankCode = bankCode;
	this.bankName = bankName;
	this.status = status;
	this.createBy = createBy;
	this.createDate = createDate;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
    
    

}
