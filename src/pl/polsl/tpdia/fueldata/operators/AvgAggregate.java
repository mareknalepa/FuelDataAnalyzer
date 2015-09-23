package pl.polsl.tpdia.fueldata.operators;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import pl.polsl.tpdia.fueldata.model.Entity;

public class AvgAggregate extends AbstractAggregate {

	@Override
	protected Double process() {
		Double sum = 0.0;
		int counter = 0;
		for (Entity e : entities) {
			Method m;
			try {
				m = e.getClass().getDeclaredMethod(getter);
				sum += (Double) m.invoke(e);
				++counter;
			} catch (NoSuchMethodException | SecurityException
					| IllegalAccessException | IllegalArgumentException
					| InvocationTargetException e1) {
				e1.printStackTrace();
			}
		}
		if (counter != 0) {
			return sum / counter;
		}
		return 0.0;
	}
}
