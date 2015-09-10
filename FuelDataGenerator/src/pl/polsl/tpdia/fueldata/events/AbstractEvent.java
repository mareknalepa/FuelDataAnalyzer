package pl.polsl.tpdia.fueldata.events;

public abstract class AbstractEvent implements Event {
	
	protected long scheduledTime;

	@Override
	public void setScheduledTime(long time) {
		scheduledTime = time;
	}

	@Override
	public long getScheduledTime() {
		return scheduledTime;
	}
}
