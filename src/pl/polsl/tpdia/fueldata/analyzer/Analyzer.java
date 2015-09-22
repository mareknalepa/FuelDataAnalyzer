package pl.polsl.tpdia.fueldata.analyzer;

import java.net.URISyntaxException;

import pl.polsl.tpdia.fueldata.io.CsvLoader;
import pl.polsl.tpdia.fueldata.model.DataHolder;

public class Analyzer {

	public static void main(String[] args) {
		DataHolder dh = loadData();

		// TODO: apply operators

		// TODO: save generated data
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
