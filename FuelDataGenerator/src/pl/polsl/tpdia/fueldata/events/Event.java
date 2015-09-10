package pl.polsl.tpdia.fueldata.events;

public interface Event {
	
	public long getScheduledTime();
	
	public void dispatch();
}
