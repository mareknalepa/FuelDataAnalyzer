package pl.polsl.tpdia.fueldata.operators;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;

import pl.polsl.tpdia.fueldata.model.Entity;

public class WriterOperator implements Operator {

	private FileWriter writer;

	public WriterOperator(FileWriter writer) {
		this.writer = writer;
	}

	@Override
	public Entity go(Entity data) {
		if (data == null) {
			return null;
		}
		try {
			Method[] getters = data.getClass().getDeclaredMethods();
			for (Method getter : getters) {
				if (!getter.getName().startsWith("get")) {
					continue;
				}
				Object o = null;
				try {
					o = getter.invoke(data);
				} catch (IllegalAccessException | IllegalArgumentException
						| InvocationTargetException e) {
					e.printStackTrace();
				}
				if (o != null) {
					if (o.getClass().isAssignableFrom(Date.class)) {
						writer.append("" + ((Date) o).getTime());
					} else {
						writer.append("" + o);
					}
					writer.append(";");
				}
			}
			writer.append("\n");
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return data;
	}
}
