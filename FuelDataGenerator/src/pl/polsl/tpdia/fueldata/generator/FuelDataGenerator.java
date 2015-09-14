package pl.polsl.tpdia.fueldata.generator;

import java.util.Date;

import pl.polsl.tpdia.fueldata.events.Event;
import pl.polsl.tpdia.fueldata.events.EventQueue;
import pl.polsl.tpdia.fueldata.model.FuelStation;
import pl.polsl.tpdia.fueldata.services.ModelManager;
import pl.polsl.tpdia.fueldata.services.Randomizer;
import pl.polsl.tpdia.fueldata.services.ServiceContainer;
import pl.polsl.tpdia.fueldata.services.Timeline;

public class FuelDataGenerator {

	public static void main(String[] args) {
		log("Starting FuelDataGenerator...");

		ServiceContainer sc = ServiceContainer.getInstance();

		log("Initializing services...");
		sc.register("timeline", new Timeline());
		sc.register("eventqueue", new EventQueue());
		sc.register("modelmanager", new ModelManager());
		sc.register("randomizer", new Randomizer());

		Timeline timeline = (Timeline) sc.get("timeline");
		EventQueue eventQueue = (EventQueue) sc.get("eventqueue");
		ModelManager modelManager = (ModelManager) sc.get("modelmanager");

		log("Loading model configuration...");
		modelManager.loadModelConfiguration();
		FuelStation fuelStation = modelManager.getFuelStation();

		log("Checking model integrity...");

		if (fuelStation.checkIntegrity()) {
			log("Model integrity seems OK.");
		} else {
			log("Detected some model integrity issues!");
			System.exit(1);
		}

		log("Adding initial events to event queue...");
		modelManager.enqueueInitialEvents(eventQueue);

		log("Data generator is about to start.");
		while (true) {
			Event event;
			while ((event = eventQueue.dequeue(timeline.getTime())) != null) {
				event.dispatch();
			}
			timeline.increment();
		}
	}

	private static void log(String message) {
		String line = "[" + (new Date()) + "] " + message;
		System.out.println(line);
	}
}
