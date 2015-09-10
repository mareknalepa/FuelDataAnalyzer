package pl.polsl.tpdia.fueldata.events;

import java.util.ArrayList;
import java.util.List;

public class EventQueue {
	
	private List<Event> events;
	
	public EventQueue() {
		events = new ArrayList<>();
	}
	
	public void enqueue(Event e) {
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
