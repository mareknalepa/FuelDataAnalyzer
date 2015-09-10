package pl.polsl.tpdia.fueldata.model;

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
		
	}
}
