package pl.polsl.tpdia.fueldata.events;

public class Timeline {
	
	private long currentTime = 0;
	
	public void increment() {
		++currentTime;
	}
	
	public long getTime() {
		return currentTime;
	}
}
