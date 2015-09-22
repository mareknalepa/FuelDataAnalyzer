package pl.polsl.tpdia.fueldata.model;

import java.util.List;

public class DataHolder {

	private List<NozzleMeasure> nozzleMeasures;
	private List<TankMeasure> tankMeasures;
	private List<Refuel> refuel;
	
	public List<NozzleMeasure> getNozzleMeasures() {
		return nozzleMeasures;
	}
	public void setNozzleMeasures(List<NozzleMeasure> nozzleMeasures) {
		this.nozzleMeasures = nozzleMeasures;
	}
	public List<TankMeasure> getTankMeasures() {
		return tankMeasures;
	}
	public void setTankMeasures(List<TankMeasure> tankMeasures) {
		this.tankMeasures = tankMeasures;
	}
	public List<Refuel> getRefuel() {
		return refuel;
	}
	public void setRefuel(List<Refuel> refuel) {
		this.refuel = refuel;
	}
}
