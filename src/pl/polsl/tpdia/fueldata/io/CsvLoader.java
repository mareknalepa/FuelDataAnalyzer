package pl.polsl.tpdia.fueldata.io;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import pl.polsl.tpdia.fueldata.model.DataHolder;
import pl.polsl.tpdia.fueldata.model.NozzleMeasure;
import pl.polsl.tpdia.fueldata.model.Refuel;
import pl.polsl.tpdia.fueldata.model.TankMeasure;

public class CsvLoader {

	private final static DateFormat df = new SimpleDateFormat(
			"YYYY-MM-DD HH:mm:ss");

	public static void load(DataHolder dh, String nozzle, String refuel,
			String tank) {
		List<NozzleMeasure> nozzleMeasures = new ArrayList<>();
		List<Refuel> refuels = new ArrayList<>();
		List<TankMeasure> tankMeasures = new ArrayList<>();

		List<String[]> rawNozzleMeasures = readLines(nozzle, ";");
		List<String[]> rawRefuels = readLines(refuel, ";");
		List<String[]> rawTankMeasures = readLines(tank, ";");

		for (String[] rawNozzleMeasure : rawNozzleMeasures) {
			nozzleMeasures.add(parseNozzleMeasure(rawNozzleMeasure));
		}

		for (String[] rawRefuel : rawRefuels) {
			refuels.add(parseRefuel(rawRefuel));
		}

		for (String[] rawTankMeasure : rawTankMeasures) {
			tankMeasures.add(parseTankMeasure(rawTankMeasure));
		}

		dh.setNozzleMeasures(nozzleMeasures);
		dh.setRefuels(refuels);
		dh.setTankMeasures(tankMeasures);
	}

	private static List<String[]> readLines(String file, String separator) {
		List<String[]> lines = new ArrayList<>();
		BufferedReader br = null;
		String line = "";
		try {
			br = new BufferedReader(new FileReader(file));
			while ((line = br.readLine()) != null) {
				String[] parts = line.split(separator);
				lines.add(parts);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return lines;
	}

	private static NozzleMeasure parseNozzleMeasure(String[] parts) {
		NozzleMeasure nm = new NozzleMeasure();

		if (parts.length != 7) {
			int length = parts.length;
			parts = Arrays.copyOf(parts, 7);
			for (int i = length; i < 7; ++i) {
				parts[i] = "";
			}
		}

		try {
			nm.setTimestamp(df.parse(parts[0]));
		} catch (ParseException e) {
			nm.setTimestamp(new Date());
		}
		nm.setLocationId(parts[1]);
		nm.setId(Long.parseLong(parts[2]));
		nm.setTankId(Long.parseLong(parts[3]));
		nm.setCurrentCounter(parseDouble(parts[4]));
		nm.setTotalCounter(parseDouble(parts[5]));
		int idle = Integer.parseInt(parts[6]);
		nm.setNozzleIdle((idle == 1) ? true : false);

		return nm;
	}

	private static Refuel parseRefuel(String[] parts) {
		Refuel r = new Refuel();

		if (parts.length != 4) {
			int length = parts.length;
			parts = Arrays.copyOf(parts, 4);
			for (int i = length; i < 4; ++i) {
				parts[i] = "";
			}
		}

		try {
			r.setTimestamp(df.parse(parts[0]));
		} catch (ParseException e) {
			r.setTimestamp(new Date());
		}
		r.setTankId(Long.parseLong(parts[1]));
		r.setFuelVolume(parseDouble(parts[2]));
		r.setRefuelSpeed(parseDouble(parts[3]));

		return r;
	}

	private static TankMeasure parseTankMeasure(String[] parts) {
		TankMeasure tm = new TankMeasure();

		if (parts.length != 9) {
			int length = parts.length;
			parts = Arrays.copyOf(parts, 9);
			for (int i = length; i < 9; ++i) {
				parts[i] = "";
			}
		}

		try {
			tm.setTimestamp(df.parse(parts[0]));
		} catch (ParseException e) {
			tm.setTimestamp(new Date());
		}
		tm.setLocationId(parts[1]);
		tm.setMeterId(parts[2]);
		tm.setId(Long.parseLong(parts[3]));
		tm.setFuelLevel(parseDouble(parts[4]));
		tm.setFuelVolume(parseDouble(parts[5]));
		tm.setFuelTemp(parseDouble(parts[6]));
		tm.setWaterLevel(parseDouble(parts[7]));
		tm.setWaterVolume(parseDouble(parts[8]));

		return tm;
	}

	private static double parseDouble(String str) {
		double value;
		try {
			value = Double.parseDouble(str.replace(',', '.'));
		} catch (NumberFormatException e) {
			value = 0.0;
		}
		return value;
	}
}
