package code88.oscar.bcm.viewObjects;

/**
 * @FileName: PositionVO.java
 * @since: 06/11/2020
 */
public class PositionVO {

    private String positionId;
    private String positionName;
    private String positionStatus;
    private String lastUpdateBy;
    private String lastUpdateTime;

    public PositionVO() {
	super();
	// TODO Auto-generated constructor stub
    }

    public PositionVO(String positionId, String positionName, String positionStatus, String lastUpdateBy,
	    String lastUpdateTime) {
	super();
	this.positionId = positionId;
	this.positionName = positionName;
	this.positionStatus = positionStatus;
	this.lastUpdateBy = lastUpdateBy;
	this.lastUpdateTime = lastUpdateTime;
    }

    public String getPositionId() {
	return positionId;
    }

    public void setPositionId(String positionId) {
	this.positionId = positionId;
    }

    public String getPositionName() {
	return positionName;
    }

    public void setPositionName(String positionName) {
	this.positionName = positionName;
    }

    public String getPositionStatus() {
	return positionStatus;
    }

    public void setPositionStatus(String positionStatus) {
	this.positionStatus = positionStatus;
    }

    public String getLastUpdateBy() {
	return lastUpdateBy;
    }

    public void setLastUpdateBy(String lastUpdateBy) {
	this.lastUpdateBy = lastUpdateBy;
    }

    public String getLastUpdateTime() {
	return lastUpdateTime;
    }

    public void setLastUpdateTime(String lastUpdateTime) {
	this.lastUpdateTime = lastUpdateTime;
    }

    @Override
    public String toString() {
	return "PositionVO [positionId=" + positionId + ", positionName=" + positionName + ", positionStatus="
		+ positionStatus + ", lastUpdateBy=" + lastUpdateBy + ", lastUpdateTime=" + lastUpdateTime + "]";
    }

}
