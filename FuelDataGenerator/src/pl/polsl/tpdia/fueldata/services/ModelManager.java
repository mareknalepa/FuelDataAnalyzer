package pl.polsl.tpdia.fueldata.services;

import pl.polsl.tpdia.fueldata.events.CarRefuelingEvent;
import pl.polsl.tpdia.fueldata.events.DataGenerationEvent;
import pl.polsl.tpdia.fueldata.events.EventQueue;
import pl.polsl.tpdia.fueldata.events.TankFillingEvent;
import pl.polsl.tpdia.fueldata.model.FuelNozzle;
import pl.polsl.tpdia.fueldata.model.FuelStation;
import pl.polsl.tpdia.fueldata.model.FuelTank;

public class ModelManager {

	private FuelStation fuelStation;

	public FuelStation getFuelStation() {
		return fuelStation;
	}

	public void loadModelConfiguration() {
		fuelStation = new FuelStation();

		FuelTank ft = new FuelTank();
		ft.setTankId(9602);
		ft.setCapacity(10000.0);
		ft.setFuelVolume(0.0);
		ft.setWaterVolume(0.0);
		fuelStation.getTanks().add(ft);

		FuelNozzle fn = new FuelNozzle();
		fn.setNozzleId(9737);
		fn.setTankId(9602);
		fn.setCounterState(0.0);
		fn.setCalibrationCoefficient(1.0);
		fuelStation.getNozzles().add(fn);
	}

	public void enqueueInitialEvents(EventQueue eq) {
		Randomizer r = (Randomizer) ServiceContainer.getInstance().get(
				"randomizer");
		DataGenerationEvent dge = new DataGenerationEvent();
		dge.setScheduledTime(r.getRandom(0, 3600));
		eq.enqueue(dge);

		TankFillingEvent tfe = new TankFillingEvent();
		tfe.setScheduledTime(r.getRandom(0, 3600));
		eq.enqueue(tfe);

		CarRefuelingEvent cre = new CarRefuelingEvent();
		cre.setFuelToTank(r.getRandom(2, 100));
		cre.setScheduledTime(r.getRandom(0, 3600));
		eq.enqueue(cre);
	}
}
