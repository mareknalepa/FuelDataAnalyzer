package pl.polsl.tpdia.fueldata.model;

import pl.polsl.tpdia.fueldata.events.EventQueue;

public class ModelManager {
	
	private ModelManager() {}
	
	private static class ModelManagerHolder {
		private final static ModelManager instance = new ModelManager();
	}
	
	public static ModelManager getInstance() {
		return ModelManagerHolder.instance;
	}
	
	private FuelStation fuelStation;
	
	public FuelStation getFuelStation() {
		return fuelStation;
	}
	
	public void loadModelConfiguration() {
		fuelStation = new FuelStation();
	}
	
	public void enqueueInitialEvents(EventQueue eq) {
		
	}
}
