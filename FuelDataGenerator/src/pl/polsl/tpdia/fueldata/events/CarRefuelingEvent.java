package pl.polsl.tpdia.fueldata.events;

import pl.polsl.tpdia.fueldata.services.Randomizer;
import pl.polsl.tpdia.fueldata.services.ServiceContainer;
import pl.polsl.tpdia.fueldata.services.Timeline;

public class CarRefuelingEvent extends AbstractEvent {

	private double fuelToTank;

	public final static long REFUELING_TIME_INTERVAL = 3;
	public final static double REFUELING_AMOUNT_INTERVAL = 0.1;

	public void setFuelToTank(double amount) {
		fuelToTank = amount;
	}

	@Override
	public void dispatch() {
		ServiceContainer sc = ServiceContainer.getInstance();
		Timeline timeline = (Timeline) sc.get("timeline");
		EventQueue eq = (EventQueue) sc.get("eventqueue");
		Randomizer r = (Randomizer) sc.get("randomizer");

		System.out.println("Refueling car at " + scheduledTime);
		if (fuelToTank > 0) {
			fuelToTank -= REFUELING_AMOUNT_INTERVAL;
			setScheduledTime(timeline.getTime() + REFUELING_TIME_INTERVAL);
			eq.enqueue(this);
		}

		int randomValue = r.getRandom(0, 100);
		if (randomValue < 2) {
			CarRefuelingEvent cre = new CarRefuelingEvent();
			cre.setFuelToTank(r.getRandom(2, 100));
			cre.setScheduledTime(timeline.getTime() + r.getRandom(30, 86400));
			eq.enqueue(cre);
		}
	}
}
