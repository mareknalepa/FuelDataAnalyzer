package pl.polsl.tpdia.fueldata.events;

import java.util.ArrayList;
import java.util.List;

import pl.polsl.tpdia.fueldata.services.ServiceContainer;
import pl.polsl.tpdia.fueldata.services.Timeline;

public class EventQueue {

	private List<Event> events;

	private Timeline timeline;

	public EventQueue() {
		events = new ArrayList<>();
		timeline = (Timeline) ServiceContainer.getInstance().get("timeline");
	}

	public void enqueue(Event e) {
		if (timeline.getTime() >= e.getScheduledTime()) {
			throw new IllegalArgumentException(
					"Cannot enqueue event scheduled in the past!");
		}
		events.add(e);
	}

	public Event dequeue(long time) {
		for (Event e : events) {
			if (e.getScheduledTime() == time) {
				events.remove(e);
				return e;
			}
		}
		return null;
	}
}
