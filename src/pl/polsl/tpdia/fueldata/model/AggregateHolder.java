package pl.polsl.tpdia.fueldata.model;

import java.util.Date;

public class AggregateHolder {

	private Date start;
	private Date end;
	private Double value;

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}
}
