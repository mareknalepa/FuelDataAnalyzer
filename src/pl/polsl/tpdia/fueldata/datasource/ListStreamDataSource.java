package pl.polsl.tpdia.fueldata.datasource;

import java.util.Collections;
import java.util.LinkedList;

import pl.polsl.tpdia.fueldata.model.DataHolder;
import pl.polsl.tpdia.fueldata.model.Entity;
import pl.polsl.tpdia.fueldata.model.NozzleMeasure;
import pl.polsl.tpdia.fueldata.model.Refuel;
import pl.polsl.tpdia.fueldata.model.TankMeasure;

public class ListStreamDataSource implements StreamDataSource {

	private LinkedList<Entity> entities;

	public ListStreamDataSource(DataHolder dh) {
		entities = new LinkedList<>();
		for (NozzleMeasure nm : dh.getNozzleMeasures()) {
			entities.add(nm);
		}
		for (Refuel r : dh.getRefuels()) {
			entities.add(r);
		}
		for (TankMeasure tm : dh.getTankMeasures()) {
			entities.add(tm);
		}
		Collections.sort(entities);
	}

	@Override
	public Entity waitForNext() {
		if (entities.size() == 0) {
			return null;
		}
		return entities.removeFirst();
	}
}
