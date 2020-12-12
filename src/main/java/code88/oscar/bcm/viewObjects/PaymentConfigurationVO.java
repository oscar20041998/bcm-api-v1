package code88.oscar.bcm.viewObjects;

/**
 * @FileName: PaymentConfigurationVO.java
 * @since: 12/12/2020
 */
public class PaymentConfigurationVO {

    private int configId;
    private String configType;
    private String configName;
    private String configValue;
    private String createBy;
    private String createDate;

    public PaymentConfigurationVO() {
	// TODO Auto-generated constructor stub
    }

    public PaymentConfigurationVO(int configId, String configType, String configName, String configValue,
	    String createBy, String createDate) {
	super();
	this.configId = configId;
	this.configType = configType;
	this.configName = configName;
	this.configValue = configValue;
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

    @Override
    public String toString() {
	return "PaymentConfigurationVO [configId=" + configId + ", configType=" + configType + ", configName="
		+ configName + ", configValue=" + configValue + ", createBy=" + createBy + ", createDate=" + createDate
		+ "]";
    }

}
