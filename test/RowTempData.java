package test;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RowTempData {
	private static final String KEY_VALE_SEP = "=";
	@JsonProperty("Temparature")
	private NumStringValue temperature = null;
	@JsonProperty("MeasuredTime")	
	private NumStringValue measuredTime = null;
	@JsonProperty("MeasuredLocation")	
	private NumStringValue measuredLocation = null;	
	
	public RowTempData(){
	}
	
	public NumStringValue getTemperature() {
		return this.temperature;
	}
	
	public void setTemperature(NumStringValue temperature) {		
		this.temperature = temperature;
	}
	
	public NumStringValue getMeasuredTime() {
		return this.measuredTime;
	}

	public void setMeasuredTime(NumStringValue measuredTime) {	
		this.measuredTime = measuredTime;
	}	
	
	public NumStringValue getMeasuredLocation() {	
		return this.measuredLocation;
	}

	public void setMeasuredLocation(NumStringValue measuredLocation) {	
		this.measuredLocation = measuredLocation;
	}	

	@Override
	public String toString(){
		StringBuilder builder = new StringBuilder();
		builder.append(" ###Temperature ="+ temperature)		
			.append(", ###MeasuredTime ="+ measuredTime)
			.append(", ###MeasuredLocation ="+ measuredLocation);
		return builder.toString();
	}	
}
