package pl.polsl.tpdia.fueldata.events;

public interface Event {
	
	public void setScheduledTime(long time);
	
	public long getScheduledTime();
	
	public void dispatch();
}
