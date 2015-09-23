package pl.polsl.tpdia.fueldata.operators;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pl.polsl.tpdia.fueldata.model.DataHolder;
import pl.polsl.tpdia.fueldata.model.NozzleMeasure;
import pl.polsl.tpdia.fueldata.model.Refuel;
import pl.polsl.tpdia.fueldata.model.TankMeasure;

public class Selection {

	public static DataHolder apply(DataHolder data, Map<String, Object> nozzles, Map<String, Object> tanks, Map<String, Object> refuels) {

		
		if (nozzles.size() > 0) {
			setNozzles(data, nozzles);
		}
		if (tanks.size() > 0) {
			setTanks(data, tanks);
		}
		
		if (refuels.size() > 0) {
			setRefuels(data, refuels);
		}

		return data;
	}

	private static DataHolder setNozzles(DataHolder data, Map<String, Object> nozzles) {
		List<NozzleMeasure> list = new ArrayList<>();
		for(int i = 0; i < data.getNozzleMeasures().size(); i++) {
			NozzleMeasure m = data.getNozzleMeasures().get(i);
			try {
				Method[] setters = NozzleMeasure.class.getDeclaredMethods();
				Map<String, Method> gettersToCompareWithValue = new HashMap<>();
				Map<Method, Object> getterValues = new HashMap<>();
				for (Method setter : setters) {
					if (!setter.getName().startsWith("get")) {
						continue;
					}
					String name = setter.getName();
					for (String n : nozzles.keySet().toArray(new String[0])) {
						if (name.equalsIgnoreCase("get" + n)) {
							gettersToCompareWithValue.put(setter.getName(), setter);
							getterValues.put(setter, nozzles.get(n));
						}
					}
				}

				for (Method setter : gettersToCompareWithValue.values()) {
					Object result = setter.invoke(m);
					if ((result != null) && (result.equals(getterValues.get(setter)))) {
//						System.out.println("Removing value: " + result + ", because it differs from " + getterValues.get(setter) + ".");
						//remove object that didn't match criteria
						list.add(m);
					}
				}

			} catch (SecurityException | IllegalAccessException | IllegalArgumentException
					| InvocationTargetException e) {
				e.printStackTrace();
				data.getNozzleMeasures().remove(m);
			}
		}
		data.setNozzleMeasures(list);
		return data;
	}

	private static DataHolder setTanks(DataHolder data, Map<String, Object> nozzles) {
		List<TankMeasure> list = new ArrayList<>();
		for(int i = 0; i < data.getTankMeasures().size(); i++) {
			TankMeasure m = data.getTankMeasures().get(i);
			try {
				Method[] setters = TankMeasure.class.getDeclaredMethods();
				Map<String, Method> gettersToCompareWithValue = new HashMap<>();
				Map<Method, Object> getterValues = new HashMap<>();
				for (Method setter : setters) {
					if (!setter.getName().startsWith("get")) {
						continue;
					}
					String name = setter.getName();
					for (String n : nozzles.keySet().toArray(new String[0])) {
						if (name.equalsIgnoreCase("get" + n)) {
							gettersToCompareWithValue.put(setter.getName(), setter);
							getterValues.put(setter, nozzles.get(n));
						}
					}
				}

				for (Method setter : gettersToCompareWithValue.values()) {
					Object result = setter.invoke(m);
					if ((result != null) && (!result.equals(getterValues.get(setter)))) {
						//remove object that didn't match criteria
						data.getTankMeasures().remove(m);
					}
				}

			} catch (SecurityException | IllegalAccessException | IllegalArgumentException
					| InvocationTargetException e) {
				e.printStackTrace();
				data.getTankMeasures().remove(m);
			}
		}
		data.setTankMeasures(list);
		return data;
	}

	private static DataHolder setRefuels(DataHolder data, Map<String, Object> nozzles) {
		List<Refuel> list = new ArrayList<>();
		for(int i = 0; i < data.getRefuels().size(); i++) {
			Refuel m = data.getRefuels().get(i);
			try {
				Method[] setters = Refuel.class.getDeclaredMethods();
				Map<String, Method> gettersToCompareWithValue = new HashMap<>();
				Map<Method, Object> getterValues = new HashMap<>();
				for (Method setter : setters) {
					if (!setter.getName().startsWith("get")) {
						continue;
					}
					String name = setter.getName();
					for (String n : nozzles.keySet().toArray(new String[0])) {
						if (name.equalsIgnoreCase("get" + n)) {
							gettersToCompareWithValue.put(setter.getName(), setter);
							getterValues.put(setter, nozzles.get(n));
						}
					}
				}

				for (Method setter : gettersToCompareWithValue.values()) {
					Object result = setter.invoke(m);
					if ((result != null) && (!result.equals(getterValues.get(setter)))) {
						//remove object that didn't match criteria
						data.getRefuels().remove(m);
					}
				}

			} catch (SecurityException | IllegalAccessException | IllegalArgumentException
					| InvocationTargetException e) {
				e.printStackTrace();
				data.getRefuels().remove(m);
			}
		}
		data.setRefuels(list);
		return data;
	}
}
