package pl.polsl.tpdia.fueldata.model;

import java.util.Date;

public class NozzleMeasure {

	private Date timestamp;
	private String locationId;
	private Long id;
	private Long tankId;
	private Double currentCounter;
	private Double totalCounter;
	private Boolean nozzleIdle;
	
	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	public String getLocationId() {
		return locationId;
	}
	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getTankId() {
		return tankId;
	}
	public void setTankId(Long tankId) {
		this.tankId = tankId;
	}
	public Double getCurrentCounter() {
		return currentCounter;
	}
	public void setCurrentCounter(Double currentCounter) {
		this.currentCounter = currentCounter;
	}
	public Double getTotalCounter() {
		return totalCounter;
	}
	public void setTotalCounter(Double totalCounter) {
		this.totalCounter = totalCounter;
	}
	public Boolean getNozzleIdle() {
		return nozzleIdle;
	}
	public void setNozzleIdle(Boolean nozzleIdle) {
		this.nozzleIdle = nozzleIdle;
	}
}
