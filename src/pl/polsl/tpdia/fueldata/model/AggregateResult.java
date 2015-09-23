package pl.polsl.tpdia.fueldata.model;

import java.util.Date;

public class AggregateResult extends Entity {

	private Date timestamp;
	private long duration;
	private Double value;

	@Override
	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public long getDuration() {
		return duration;
	}

	public void setDuration(long duration) {
		this.duration = duration;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}
}
