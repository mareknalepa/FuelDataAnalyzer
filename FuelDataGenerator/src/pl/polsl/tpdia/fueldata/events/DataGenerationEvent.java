package pl.polsl.tpdia.fueldata.events;

import pl.polsl.tpdia.fueldata.services.ServiceContainer;
import pl.polsl.tpdia.fueldata.services.Timeline;

public class DataGenerationEvent extends AbstractEvent {

	public final static long INTERVAL = 240;

	@Override
	public void dispatch() {
		ServiceContainer sc = ServiceContainer.getInstance();
		Timeline timeline = (Timeline) sc.get("timeline");
		EventQueue eq = (EventQueue) sc.get("eventqueue");

		System.out.println("Generating data at " + scheduledTime);

		DataGenerationEvent dge = new DataGenerationEvent();
		dge.setScheduledTime(timeline.getTime() + INTERVAL);
		eq.enqueue(dge);
	}
}
