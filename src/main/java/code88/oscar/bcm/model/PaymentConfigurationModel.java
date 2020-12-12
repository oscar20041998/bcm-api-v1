package code88.oscar.bcm.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @FileName: PaymentConfiguration.java
 * @since: 12/12/2020
 */

@Entity
@Table(name = "payment_configuration")
public class PaymentConfigurationModel {

    @Id
    @Column(name = "config_id")
    private int configId;
    
    @Column(name = "config_type")
    private String configType;
    
    @Column (name = "config_name")
    private String configName;
    
    @Column (name = "config_value")
    private String configValue;
    
    @Column(name ="status")
    private String status;
    
    @Column (name = "create_by")
    private String createBy;
    
    @Column (name = "create_date")
    private LocalDateTime createDate;

    public PaymentConfigurationModel() {
	// TODO Auto-generated constructor stub
    }

    public PaymentConfigurationModel(int configId, String configType, String configName, String configValue, String status,
	    String createBy, LocalDateTime createDate) {
	super();
	this.configId = configId;
	this.configType = configType;
	this.configName = configName;
	this.configValue = configValue;
	this.status = status;
	this.createBy = createBy;
	this.createDate = createDate;
    }

    public int getConfigId() {
	return configId;
    }

    public void setConfigId(int configId) {
	this.configId = configId;
    }

    public String getConfigType() {
	return configType;
    }

    public void setConfigType(String configType) {
	this.configType = configType;
    }

    public String getConfigName() {
	return configName;
    }

    public void setConfigName(String configName) {
	this.configName = configName;
    }

    public String getConfigValue() {
	return configValue;
    }

    public void setConfigValue(String configValue) {
	this.configValue = configValue;
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

    @Override
    public String toString() {
	return "PaymentConfiguration [configId=" + configId + ", configType=" + configType + ", configName="
		+ configName + ", configValue=" + configValue + ", status=" + status + ", createBy=" + createBy
		+ ", createDate=" + createDate + "]";
    }

}
