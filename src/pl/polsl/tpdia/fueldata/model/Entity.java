package pl.polsl.tpdia.fueldata.model;

import java.util.Date;

public abstract class Entity implements Comparable<Entity> {

	public abstract Date getTimestamp();

	@Override
	public int compareTo(Entity e) {
		return getTimestamp().compareTo(e.getTimestamp());
	}
}
