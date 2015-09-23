package pl.polsl.tpdia.fueldata.datasource;

import pl.polsl.tpdia.fueldata.model.Entity;

public interface StreamDataSource {

	public Entity waitForNext();
}
