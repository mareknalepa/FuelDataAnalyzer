package pl.polsl.tpdia.fueldata.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FuelStation {
	
	private List<FuelTank> tanks;
	
	private List<FuelNozzle> nozzles;
	
	public FuelStation() {
		tanks = new ArrayList<>();
		nozzles = new ArrayList<>();
	}

	public List<FuelTank> getTanks() {
		return tanks;
	}

	public List<FuelNozzle> getNozzles() {
		return nozzles;
	}
	
	public FuelTank getTank(long id) {
		for (FuelTank ft : tanks) {
			if (ft.getTankId() == id) {
				return ft;
			}
		}
		return null;
	}
	
	public FuelNozzle getNozzle(long id) {
		for (FuelNozzle fn : nozzles) {
			if (fn.getNozzleId() == id) {
				return fn;
			}
		}
		return null;
	}
	
	public boolean checkIntegrity() {
		Set<Long> tankIds = new HashSet<>();
		Set<Long> nozzleIds = new HashSet<>();
		for (FuelTank ft : tanks) {
			if (!tankIds.add(ft.getTankId())) {
				return false;
			}
		}
		for (FuelNozzle fn : nozzles) {
			if (!nozzleIds.add(fn.getNozzleId())) {
				return false;
			}
			if (getTank(fn.getTankId()) == null) {
				return false;
			}
		}
		return true;
	}
}
