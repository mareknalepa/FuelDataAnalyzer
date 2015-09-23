package pl.polsl.tpdia.fueldata.analyzer;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		DataHolder dh = loadData();

		String[] fields = { "timestamp", "id", "fuelVolume" };
		dh = Projection.apply(dh, Arrays.asList(fields),
				new ArrayList<String>(), new ArrayList<String>());

		Map<String, Object> map = new HashMap<>();
		map.put("id", new Long(1));
		dh = Selection.apply(dh, map, new HashMap<>(), new HashMap<>());

		List<AggregateHolder> ah = Aggregate.applyAvg(TankMeasure.class,
				dh.getTankMeasures(), 15L, "getFuelVolume");

		CsvGenerator.generate("nozzleResults.log", "tankResults.log",
				"refuelResults.log", dh);
		CsvGenerator.generateFromAggregate("avgFuelVolume.log", ah);
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
