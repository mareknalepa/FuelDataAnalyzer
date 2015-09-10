package pl.polsl.tpdia.fueldata.model;

public class FuelNozzle {
	
	private long nozzleId;
	
	private long tankId;
	
	private double counterState;
	
	private double calibrationCoefficient;

	public long getNozzleId() {
		return nozzleId;
	}

	public void setNozzleId(long nozzleId) {
		this.nozzleId = nozzleId;
	}

	public long getTankId() {
		return tankId;
	}

	public void setTankId(long tankId) {
		this.tankId = tankId;
	}

	public double getCounterState() {
		return counterState;
	}

	public void setCounterState(double counterState) {
		this.counterState = counterState;
	}

	public double getCalibrationCoefficient() {
		return calibrationCoefficient;
	}

	public void setCalibrationCoefficient(double calibrationCoefficient) {
		this.calibrationCoefficient = calibrationCoefficient;
	}
}
