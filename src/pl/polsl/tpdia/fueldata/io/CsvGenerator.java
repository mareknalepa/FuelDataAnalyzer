package pl.polsl.tpdia.fueldata.io;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import pl.polsl.tpdia.fueldata.model.AggregateHolder;
import pl.polsl.tpdia.fueldata.model.DataHolder;
import pl.polsl.tpdia.fueldata.model.NozzleMeasure;
import pl.polsl.tpdia.fueldata.model.Refuel;
import pl.polsl.tpdia.fueldata.model.TankMeasure;

public class CsvGenerator {

	public static final void generate(String nozzle, String tank,
			String refeul, DataHolder data) {
		try {
			FileWriter nozzleFile = new FileWriter(nozzle);

			for (NozzleMeasure m : data.getNozzleMeasures()) {
				Method[] getters = NozzleMeasure.class.getDeclaredMethods();
				for (Method getter : getters) {
					if (!getter.getName().startsWith("get")) {
						continue;
					}
					Object o = null;
					try {
						o = getter.invoke(m);
					} catch (IllegalAccessException | IllegalArgumentException
							| InvocationTargetException e) {
						e.printStackTrace();
					}
					if (o != null) {
						nozzleFile.append("" + o);
						nozzleFile.append(";");
					}
				}
				nozzleFile.append("\n");
			}

			nozzleFile.flush();
			nozzleFile.close();

			FileWriter tankFile = new FileWriter(tank);
			for (TankMeasure m : data.getTankMeasures()) {
				Method[] getters = TankMeasure.class.getDeclaredMethods();
				for (Method getter : getters) {
					if (!getter.getName().startsWith("get")) {
						continue;
					}
					Object o = null;
					try {
						o = getter.invoke(m);
					} catch (IllegalAccessException | IllegalArgumentException
							| InvocationTargetException e) {
						e.printStackTrace();
					}
					if (o != null) {
						tankFile.append("" + o);
						tankFile.append(";");
					}
				}
				tankFile.append("\n");
			}

			tankFile.flush();
			tankFile.close();

			FileWriter refuelFile = new FileWriter(refeul);
			for (Refuel m : data.getRefuels()) {
				Method[] getters = Refuel.class.getDeclaredMethods();
				for (Method getter : getters) {
					if (!getter.getName().startsWith("get")) {
						continue;
					}
					Object o = null;
					try {
						o = getter.invoke(m);
					} catch (IllegalAccessException | IllegalArgumentException
							| InvocationTargetException e) {
						e.printStackTrace();
					}
					if (o != null) {
						refuelFile.append("" + o);
						refuelFile.append(";");
					}
				}
				refuelFile.append("\n");
			}

			refuelFile.flush();
			refuelFile.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static final void generateFromAggregate(String file,
			List<AggregateHolder> ah) {

	}
}