package pl.polsl.tpdia.fueldata.model;

import java.util.Date;

public class Refuel implements Comparable<Refuel> {

	Date timestamp;
	private Long tankId;
	private Double fuelVolume;
	private Double refuelSpeed;

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public Long getTankId() {
		return tankId;
	}

	public void setTankId(Long tankId) {
		this.tankId = tankId;
	}

	public Double getFuelVolume() {
		return fuelVolume;
	}

	public void setFuelVolume(Double fuelVolume) {
		this.fuelVolume = fuelVolume;
	}

	public Double getRefuelSpeed() {
		return refuelSpeed;
	}

	public void setRefuelSpeed(Double refuelSpeed) {
		this.refuelSpeed = refuelSpeed;
	}

	@Override
	public int compareTo(Refuel r) {
		return getTimestamp().compareTo(r.getTimestamp());
	}
}
