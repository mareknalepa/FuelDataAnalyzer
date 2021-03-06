package pl.polsl.tpdia.fueldata.model;

import java.util.Date;

public class TankMeasure extends Entity {

	private Date timestamp;
	private String locationId;
	private String meterId;
	private Long id;
	private Double fuelLevel;
	private Double fuelVolume;
	private Double fuelTemp;
	private Double waterLevel;
	private Double waterVolume;

	@Override
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

	public String getMeterId() {
		return meterId;
	}

	public void setMeterId(String meterId) {
		this.meterId = meterId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getFuelLevel() {
		return fuelLevel;
	}

	public void setFuelLevel(Double fuelLevel) {
		this.fuelLevel = fuelLevel;
	}

	public Double getFuelVolume() {
		return fuelVolume;
	}

	public void setFuelVolume(Double fuelVolume) {
		this.fuelVolume = fuelVolume;
	}

	public Double getFuelTemp() {
		return fuelTemp;
	}

	public void setFuelTemp(Double fuelTemp) {
		this.fuelTemp = fuelTemp;
	}

	public Double getWaterLevel() {
		return waterLevel;
	}

	public void setWaterLevel(Double waterLevel) {
		this.waterLevel = waterLevel;
	}

	public Double getWaterVolume() {
		return waterVolume;
	}

	public void setWaterVolume(Double waterVolume) {
		this.waterVolume = waterVolume;
	}
}
