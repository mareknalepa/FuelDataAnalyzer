package pl.polsl.tpdia.fueldata.operators;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pl.polsl.tpdia.fueldata.model.DataHolder;
import pl.polsl.tpdia.fueldata.model.NozzleMeasure;
import pl.polsl.tpdia.fueldata.model.Refuel;
import pl.polsl.tpdia.fueldata.model.TankMeasure;

public class Projection {
	public static DataHolder apply(DataHolder data, List<String> nozzles, List<String> tanks, List<String> refuels) {
		
		purgeNozzles(data, nozzles);
		purgeTanks(data, tanks);
		purgeRefuels(data, refuels);
		
		return data;
	}
	
	private static DataHolder purgeNozzles(DataHolder data, List<String> nozzles) {
		for(int i = 0; i < data.getNozzleMeasures().size(); i++) {
			NozzleMeasure m = data.getNozzleMeasures().get(i);
			try {
				Method[] setters = NozzleMeasure.class.getDeclaredMethods();
				Map<String, Method> settersToInvokeWithNull = new HashMap<>();
				for(Method setter : setters) {
					if (!setter.getName().startsWith("set")) {
						continue;
					}
					settersToInvokeWithNull.put(setter.getName(), setter);
					String name = setter.getName();
					for(String n : nozzles) {
						if (name.endsWith(n)) {
							settersToInvokeWithNull.remove(setter.getName());
						}
					}
				}
				
				for(Method setter : settersToInvokeWithNull.values()) {
					//set unwanted field to null
					setter.invoke(m, new Object[]{null});
				}
				
			} catch (SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				e.printStackTrace();
				data.getNozzleMeasures().remove(m);
			}
		}
		return data;
	}
	
	private static DataHolder purgeTanks(DataHolder data, List<String> tanks) {
		for(int i = 0; i < data.getTankMeasures().size(); i++) {
			TankMeasure m = data.getTankMeasures().get(i);
			try {
				Method[] setters = NozzleMeasure.class.getDeclaredMethods();
				Map<String, Method> settersToInvokeWithNull = new HashMap<>();
				for(Method setter : setters) {
					if (!setter.getName().startsWith("set")) {
						continue;
					}
					settersToInvokeWithNull.put(setter.getName(), setter);
					String name = setter.getName();
					for(String n : tanks) {
						if (name.endsWith(n)) {
							settersToInvokeWithNull.remove(setter.getName());
						}
					}
				}
				
				for(Method setter : settersToInvokeWithNull.values()) {
					//set unwanted field to null
					setter.invoke(m, new Object[]{null});
				}
				
			} catch (SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				e.printStackTrace();
				data.getNozzleMeasures().remove(m);
			}
		}
		return data;
	}
	
	private static DataHolder purgeRefuels(DataHolder data, List<String> refuels) {
		for(int i = 0; i < data.getRefuels().size(); i++) {
			Refuel m = data.getRefuels().get(i);
			try {
				Method[] setters = NozzleMeasure.class.getDeclaredMethods();
				Map<String, Method> settersToInvokeWithNull = new HashMap<>();
				for(Method setter : setters) {
					if (!setter.getName().startsWith("set")) {
						continue;
					}
					settersToInvokeWithNull.put(setter.getName(), setter);
					String name = setter.getName();
					for(String n : refuels) {
						if (name.endsWith(n)) {
							settersToInvokeWithNull.remove(setter.getName());
						}
					}
				}
				
				for(Method setter : settersToInvokeWithNull.values()) {
					//set unwanted field to null
					setter.invoke(m, new Object[]{null});
				}
				
			} catch (SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				e.printStackTrace();
				data.getNozzleMeasures().remove(m);
			}
		}
		return data;
	}
}
