package pl.polsl.tpdia.fueldata.events;

import pl.polsl.tpdia.fueldata.services.Randomizer;
import pl.polsl.tpdia.fueldata.services.ServiceContainer;
import pl.polsl.tpdia.fueldata.services.Timeline;

public class TankFillingEvent extends AbstractEvent {

	@Override
	public void dispatch() {
		ServiceContainer sc = ServiceContainer.getInstance();
		Timeline timeline = (Timeline) sc.get("timeline");
		EventQueue eq = (EventQueue) sc.get("eventqueue");
		Randomizer r = (Randomizer) sc.get("randomizer");

		System.out.println("Filling tank at " + scheduledTime);

		TankFillingEvent tfe = new TankFillingEvent();
		long scheduledTime = timeline.getTime() + r.getRandom(36000, 86400);
		tfe.setScheduledTime(scheduledTime);
		eq.enqueue(tfe);
	}
}
