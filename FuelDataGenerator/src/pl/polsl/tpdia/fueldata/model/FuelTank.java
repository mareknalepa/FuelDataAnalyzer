package pl.polsl.tpdia.fueldata.model;

public class FuelTank {
	
	private long tankId;
	
	private double capacity;
	
	private double fuelVolume;
	
	private double waterVolume;
	
	private double temperature;

	public long getTankId() {
		return tankId;
	}

	public void setTankId(long tankId) {
		this.tankId = tankId;
	}

	public double getCapacity() {
		return capacity;
	}

	public void setCapacity(double capacity) {
		this.capacity = capacity;
	}

	public double getFuelVolume() {
		return fuelVolume;
	}

	public void setFuelVolume(double fuelVolume) {
		this.fuelVolume = fuelVolume;
	}

	public double getWaterVolume() {
		return waterVolume;
	}

	public void setWaterVolume(double waterVolume) {
		this.waterVolume = waterVolume;
	}

	public double getTemperature() {
		return temperature;
	}

	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}
}
