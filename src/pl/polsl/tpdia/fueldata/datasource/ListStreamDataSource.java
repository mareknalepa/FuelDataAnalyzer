package pl.polsl.tpdia.fueldata.datasource;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import pl.polsl.tpdia.fueldata.model.DataHolder;
import pl.polsl.tpdia.fueldata.model.Entity;
import pl.polsl.tpdia.fueldata.model.NozzleMeasure;
import pl.polsl.tpdia.fueldata.model.Refuel;
import pl.polsl.tpdia.fueldata.model.TankMeasure;

public class ListStreamDataSource implements StreamDataSource {

	private List<Entity> entities;
	private int index;

	public ListStreamDataSource(DataHolder dh) {
		entities = new ArrayList<>();
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
		index = 0;
	}

	@Override
	public Entity waitForNext() {
		Entity e;
		if (index < entities.size()) {
			e = entities.get(index);
			++index;
		} else {
			e = null;
		}
		return e;
	}
}
