package pl.polsl.tpdia.fueldata.analyzer;

import static java.lang.System.out;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pl.polsl.tpdia.fueldata.datasource.ListStreamDataSource;
import pl.polsl.tpdia.fueldata.datasource.StreamDataSource;
import pl.polsl.tpdia.fueldata.io.CsvLoader;
import pl.polsl.tpdia.fueldata.model.DataHolder;
import pl.polsl.tpdia.fueldata.model.Entity;
import pl.polsl.tpdia.fueldata.model.NozzleMeasure;
import pl.polsl.tpdia.fueldata.model.Refuel;
import pl.polsl.tpdia.fueldata.model.TankMeasure;
import pl.polsl.tpdia.fueldata.operators.AbstractAggregate;
import pl.polsl.tpdia.fueldata.operators.AvgAggregate;
import pl.polsl.tpdia.fueldata.operators.Operator;
import pl.polsl.tpdia.fueldata.operators.Selection;
import pl.polsl.tpdia.fueldata.operators.WriterOperator;

public class Analyzer {

	public static void main(String[] args) {
		out.println("Starting analyzer...");

		out.println("Loading data...");
		DataHolder dh = loadData();

		out.println("Creating stream data source...");
		StreamDataSource sds = new ListStreamDataSource(dh);

		out.println("Configuring stream operators...");
		List<Operator> nozzleOperators = new ArrayList<>();
		List<Operator> refuelOperators = new ArrayList<>();
		List<Operator> tankOperators = new ArrayList<>();

		Map<String, Object> tankMap = new HashMap<>();
		tankMap.put("id", new Long(1));
		Selection s = new Selection(tankMap);
		tankOperators.add(s);

		out.println("Configuring output writers...");
		try {
			WriterOperator nozzleWriter = new WriterOperator(new FileWriter("nozzleResults.csv"));
			WriterOperator refuelWriter = new WriterOperator(new FileWriter("refuelResults.csv"));
			WriterOperator tankWriter = new WriterOperator(new FileWriter("tankResults.csv"));
			nozzleOperators.add(nozzleWriter);
			refuelOperators.add(refuelWriter);
			tankOperators.add(tankWriter);
		} catch (IOException e) {
			e.printStackTrace();
		}

		out.println("Configuring aggregate operators...");
		AbstractAggregate avgAggregate = new AvgAggregate();
		avgAggregate.setGetter("getFuelVolume");
		avgAggregate.setTimeWindow(30);
		tankOperators.add(avgAggregate);
		try {
			tankOperators.add(new WriterOperator(new FileWriter("avgFuelVolume.csv")));
		} catch (IOException e) {
			e.printStackTrace();
		}

		out.println("Starting processing data stream...");
		Entity entity;
		while ((entity = sds.waitForNext()) != null) {
			if (entity instanceof NozzleMeasure) {
				for (Operator o : nozzleOperators) {
					entity = o.go(entity);
				}
			} else if (entity instanceof Refuel) {
				for (Operator o : refuelOperators) {
					entity = o.go(entity);
				}
			} else if (entity instanceof TankMeasure) {
				for (Operator o : tankOperators) {
					entity = o.go(entity);
				}
			}
		}

		out.println("Finished");
	}

	private static DataHolder loadData() {
		DataHolder dh = new DataHolder();

		String nozzleMeasuresPath = "nozzleMeasures.log";
		String refuelsPath = "refuel.log";
		String tankMeasuresPath = "tankMeasures.log";

		CsvLoader.load(dh, nozzleMeasuresPath, refuelsPath, tankMeasuresPath);
		return dh;
	}
}
