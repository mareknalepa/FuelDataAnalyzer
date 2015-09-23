package pl.polsl.tpdia.fueldata.operators;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import pl.polsl.tpdia.fueldata.model.Entity;

public class Selection implements Operator{
	
	private Map<String, Object> fieldValues;
	
	public Selection(Map<String, Object> fieldValues) {
		this.fieldValues = fieldValues;
	}

	public Entity go(Entity data) {
		if (data == null) {
			return null;
		}

		try {
			Method[] methods = data.getClass().getDeclaredMethods();
			Map<String, Method> gettersToCompareWithValues = new HashMap<>();
			Map<Method, Object> getterExpectedValues = new HashMap<>();
			for (Method method : methods) {
				if (!method.getName().startsWith("get")) {
					continue;
				}
				String name = method.getName();
				for (String n : fieldValues.keySet().toArray(new String[0])) {
					if (name.equalsIgnoreCase("get" + n)) {
						gettersToCompareWithValues.put(method.getName(), method);
						getterExpectedValues.put(method, fieldValues.get(n));
					}
				}
			}

			for (Method setter : gettersToCompareWithValues.values()) {
				Object result = setter.invoke(data);
				if ((result != null) && (result.equals(getterExpectedValues.get(setter)))) {
					return null;
				}
			}

		} catch (SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}

		return data;
	}
}
