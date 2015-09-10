package pl.polsl.tpdia.fueldata.services;

public class Timeline {
	
	private long currentTime = 0;
	
	public void increment() {
		++currentTime;
	}
	
	public long getTime() {
		return currentTime;
	}
}
