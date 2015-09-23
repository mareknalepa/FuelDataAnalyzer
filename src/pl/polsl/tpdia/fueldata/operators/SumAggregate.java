package pl.polsl.tpdia.fueldata.operators;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import pl.polsl.tpdia.fueldata.model.Entity;

public class SumAggregate extends AbstractAggregate {

	@Override
	protected Double process() {
		Double sum = 0.0;
		for (Entity e : entities) {
			Method m;
			try {
				m = e.getClass().getDeclaredMethod(getter);
				sum += (Double) m.invoke(e);
			} catch (NoSuchMethodException | SecurityException
					| IllegalAccessException | IllegalArgumentException
					| InvocationTargetException e1) {
				e1.printStackTrace();
			}
		}
		return sum;
	}
}
