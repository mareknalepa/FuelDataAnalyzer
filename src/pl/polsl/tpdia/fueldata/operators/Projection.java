package pl.polsl.tpdia.fueldata.operators;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pl.polsl.tpdia.fueldata.model.Entity;

public class Projection {
	
	private List<String> fields;
	
	public Projection(List<String> fields) {
		this.fields = fields;
	}
	
	public Entity go(Entity data) {
		if (data == null) {
			return null;
		}
		try {
			Method[] methods = data.getClass().getDeclaredMethods();
			Map<String, Method> settersToInvokeWithNull = new HashMap<>();
			for (Method method : methods) {
				if (!method.getName().startsWith("set")) {
					continue;
				}
				settersToInvokeWithNull.put(method.getName(), method);
				String name = method.getName();
				for (String fieldName : fields) {
					if (name.equalsIgnoreCase("set" + fieldName)) {
						settersToInvokeWithNull.remove(method.getName());
					}
				}
			}

			for (Method setter : settersToInvokeWithNull.values()) {
				// set unwanted field to null
				setter.invoke(data, new Object[] { null });
			}

		} catch (SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}
		return data;
	}
}
