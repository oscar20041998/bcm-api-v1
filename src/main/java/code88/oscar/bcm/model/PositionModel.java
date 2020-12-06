package code88.oscar.bcm.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @FileName: TableModel.java
 * @since: 22/10/2020
 */
@Entity
@Table(name = "position")
public class PositionModel {

    @Id
    @Column(name = "position_id")
    private String positionId;

    @Column(name = "name")
    private String name;

    @Column(name = "position_status")
    private String positionStatus;

    @Column(name = "last_update_by")
    private String lastUpdateBy;

    @Column(name = "last_update_time")
    private LocalDateTime lastUpdateTime;

    public PositionModel() {
	super();
	// TODO Auto-generated constructor stub
    }

    public PositionModel(String positionId, String name, String positionStatus, String lastUpdateBy,
	    LocalDateTime lastUpdateTime) {
	super();
	this.positionId = positionId;
	this.name = name;
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

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
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

    public LocalDateTime getLastUpdateTime() {
	return lastUpdateTime;
    }

    public void setLastUpdateTime(LocalDateTime lastUpdateTime) {
	this.lastUpdateTime = lastUpdateTime;
    }

    @Override
    public String toString() {
	return "PositionModel [positionId=" + positionId + ", name=" + name + ", positionStatus=" + positionStatus
		+ ", lastUpdateBy=" + lastUpdateBy + ", lastUpdateTime=" + lastUpdateTime + "]";
    }

}
