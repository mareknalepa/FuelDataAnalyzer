package pl.polsl.tpdia.fueldata.operators;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.Date;

import pl.polsl.tpdia.fueldata.model.DataHolder;
import pl.polsl.tpdia.fueldata.model.NozzleMeasure;
import pl.polsl.tpdia.fueldata.model.Refuel;
import pl.polsl.tpdia.fueldata.model.TankMeasure;

public class Aggregate {

	public enum Entity {
		NozzleMeasure, Refuel, TankMeasure
	}

	public static Double applySum(DataHolder dh, Entity e, Date from, Date to,
			String getter) {
		sort(dh);
		Double sum = 0.0;
		switch (e) {
		case NozzleMeasure:
			for (NozzleMeasure nm : dh.getNozzleMeasures()) {
				if (nm.getTimestamp().before(from)) {
					continue;
				}
				if (nm.getTimestamp().after(to)) {
					continue;
				}
				Class<?> c = NozzleMeasure.class;
				Method method;
				try {
					method = c.getDeclaredMethod(getter);
					sum += (Double) method.invoke(nm);
				} catch (NoSuchMethodException e1) {
					e1.printStackTrace();
				} catch (SecurityException e1) {
					e1.printStackTrace();
				} catch (IllegalAccessException e1) {
					e1.printStackTrace();
				} catch (IllegalArgumentException e1) {
					e1.printStackTrace();
				} catch (InvocationTargetException e1) {
					e1.printStackTrace();
				}
			}
			break;
		case Refuel:
			for (Refuel r : dh.getRefuels()) {
				if (r.getTimestamp().before(from)) {
					continue;
				}
				if (r.getTimestamp().after(to)) {
					continue;
				}
				Class<?> c = Refuel.class;
				Method method;
				try {
					method = c.getDeclaredMethod(getter);
					sum += (Double) method.invoke(r);
				} catch (NoSuchMethodException e1) {
					e1.printStackTrace();
				} catch (SecurityException e1) {
					e1.printStackTrace();
				} catch (IllegalAccessException e1) {
					e1.printStackTrace();
				} catch (IllegalArgumentException e1) {
					e1.printStackTrace();
				} catch (InvocationTargetException e1) {
					e1.printStackTrace();
				}
			}
			break;
		case TankMeasure:
			for (TankMeasure tm : dh.getTankMeasures()) {
				if (tm.getTimestamp().before(from)) {
					continue;
				}
				if (tm.getTimestamp().after(to)) {
					continue;
				}
				Class<?> c = NozzleMeasure.class;
				Method method;
				try {
					method = c.getDeclaredMethod(getter);
					sum += (Double) method.invoke(tm);
				} catch (NoSuchMethodException e1) {
					e1.printStackTrace();
				} catch (SecurityException e1) {
					e1.printStackTrace();
				} catch (IllegalAccessException e1) {
					e1.printStackTrace();
				} catch (IllegalArgumentException e1) {
					e1.printStackTrace();
				} catch (InvocationTargetException e1) {
					e1.printStackTrace();
				}
			}
			break;
		}

		return sum;
	}

	private static void sort(DataHolder dh) {
		Collections.sort(dh.getNozzleMeasures());
		Collections.sort(dh.getRefuels());
		Collections.sort(dh.getTankMeasures());
	}
}
