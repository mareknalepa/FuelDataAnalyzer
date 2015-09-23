package pl.polsl.tpdia.fueldata.operators;

import java.util.Date;
import java.util.LinkedList;

import pl.polsl.tpdia.fueldata.model.AggregateResult;
import pl.polsl.tpdia.fueldata.model.Entity;

public abstract class AbstractAggregate implements Operator {

	final private static long ONE_MINUTE_IN_MILLIS = 60000;
	protected LinkedList<Entity> entities;
	protected Date currentDate;
	protected long windowInMinutes;
	protected String getter;

	public AbstractAggregate() {
		entities = new LinkedList<>();
		windowInMinutes = 15;
	}

	public void setTimeWindow(long minutes) {
		windowInMinutes = minutes;
	}

	public void setGetter(String value) {
		getter = value;
	}

	@Override
	public Entity go(Entity entity) {
		if (entity == null) {
			return null;
		}
		entities.add(entity);
		currentDate = entity.getTimestamp();
		gc();
		Double result = process();
		AggregateResult ar = new AggregateResult();
		ar.setTimestamp(new Date(currentDate.getTime() - windowInMinutes
				* ONE_MINUTE_IN_MILLIS));
		ar.setDuration(windowInMinutes);
		ar.setValue(result);

		return ar;
	}

	private void gc() {
		while (entities.getFirst().getTimestamp().getTime() < currentDate
				.getTime() - windowInMinutes * ONE_MINUTE_IN_MILLIS) {
			entities.removeFirst();
		}
	}

	protected abstract Double process();
}
