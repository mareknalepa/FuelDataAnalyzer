package pl.polsl.tpdia.fueldata.services;

import java.util.HashMap;
import java.util.Map;

public class ServiceContainer {
	
	private static class ServiceContainerHolder {
		private final static ServiceContainer instance = new ServiceContainer();
	}
	
	public static ServiceContainer getInstance() {
		return ServiceContainerHolder.instance;
	}

	private Map<String, Object> services;

	private ServiceContainer() {
		services = new HashMap<>();
	}

	public void register(String name, Object service) {
		services.put(name, service);
	}

	public Object get(String name) {
		if (!services.containsKey(name)) {
			throw new IllegalArgumentException("Cannot find service named \""
					+ name + "\"");
		}
		return services.get(name);
	}
}
