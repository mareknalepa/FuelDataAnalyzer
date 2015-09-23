package pl.polsl.tpdia.fueldata.analyzer;

import static java.lang.System.out;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pl.polsl.tpdia.fueldata.datasource.ListStreamDataSource;
import pl.polsl.tpdia.fueldata.datasource.StreamDataSource;
import pl.polsl.tpdia.fueldata.io.CsvGenerator;
import pl.polsl.tpdia.fueldata.io.CsvLoader;
import pl.polsl.tpdia.fueldata.model.AggregateHolder;
import pl.polsl.tpdia.fueldata.model.DataHolder;
import pl.polsl.tpdia.fueldata.model.TankMeasure;
import pl.polsl.tpdia.fueldata.operators.Aggregate;
import pl.polsl.tpdia.fueldata.operators.Projection;
import pl.polsl.tpdia.fueldata.operators.Selection;

public class Analyzer {

	public static void main(String[] args) {
		out.println("Starting analyzer...");

		out.println("Loading data...");
		DataHolder dh = loadData();

		out.println("Creating stream data source...");
		StreamDataSource sds = new ListStreamDataSource(dh);

		out.println("Performing selection...");
		Map<String, Object> tankMap = new HashMap<>();
		tankMap.put("id", new Long(1));
		Map<String, Object> refuelMap = new HashMap<>();
		refuelMap.put("tankId", new Long(1));
		dh = Selection.apply(dh, new HashMap<>(), tankMap, refuelMap);

		out.println("Performing projection...");
		String[] tankFields = { "timestamp", "id", "fuelVolume" };
		String[] refuelFields = { "timestamp", "tankId", "fuelVolume" };
		dh = Projection.apply(dh, new ArrayList<String>(),
				Arrays.asList(tankFields), Arrays.asList(refuelFields));

		out.println("Saving results...");
		CsvGenerator.generate("nozzleResults.csv", "tankResults.csv",
				"refuelResults.csv", dh);

		out.println("Generating aggregates...");
		List<AggregateHolder> ah = Aggregate.applyAvg(TankMeasure.class,
				dh.getTankMeasures(), 30L, "getFuelVolume");

		out.println("Saving aggregates...");
		CsvGenerator.generateFromAggregate("avgFuelVolume.csv", ah);

		out.println("Finished");
	}

	private static DataHolder loadData() {
		DataHolder dh = new DataHolder();

		String nozzleMeasuresPath;
		try {
			nozzleMeasuresPath = Analyzer.class.getClassLoader()
					.getResource("nozzleMeasures.log").toURI().getPath();
		} catch (URISyntaxException e) {
			nozzleMeasuresPath = null;
		}
		String refuelsPath;
		try {
			refuelsPath = Analyzer.class.getClassLoader()
					.getResource("refuel.log").toURI().getPath();
		} catch (URISyntaxException e) {
			refuelsPath = null;
		}
		String tankMeasuresPath;
		try {
			tankMeasuresPath = Analyzer.class.getClassLoader()
					.getResource("tankMeasures.log").toURI().getPath();
		} catch (URISyntaxException e) {
			tankMeasuresPath = null;
		}

		CsvLoader.load(dh, nozzleMeasuresPath, refuelsPath, tankMeasuresPath);
		return dh;
	}
}
