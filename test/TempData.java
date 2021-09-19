package test;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TempData {
	private String KEY_VALE_SEP = "=";
	@JsonProperty("Temparature")
	private String temperature = "";
	@JsonProperty("MeasuredTime")	
	private String measuredTime = "";
	@JsonProperty("MeasuredLocation")	
	private String measuredLocation = "";	
	
	public String tempMeasuredTime = "";	
	
	public TempData(){
	}
/*	
	public TempData(String temperature, String measuredTime, String measuredLocation) {
		this.temperature = temperature;
		this.measuredTime = measuredTime;		
		this.measuredLocation = measuredLocation;
	}	
*/	

	public String getTempMeasuredTime() {
		return this.measuredTime;
	}
	
	public void setTempMeasuredTime(String tempMeasuredTime) {	
		this.tempMeasuredTime = tempMeasuredTime;
	}
	
	public String getMeasuredTime() {
		return this.measuredTime;
	}

	public void setMeasuredTime(String measuredTime) {
		String measuredTimeValue = measuredTime;
		if(temperature != null) {
			int nInx = measuredTime.indexOf(KEY_VALE_SEP);
			if( nInx != -1) {
				measuredTimeValue = measuredTime.substring(nInx+1, measuredTime.length()-1);
			}
		}		
		this.measuredTime = measuredTimeValue;
	}	
	
	public String getTemperature() {
		return this.temperature;
	}
	
	public void setTemperature(String temperature) {
	
		String tempValue = temperature;
		if(temperature != null) {
			int nInx = temperature.indexOf(KEY_VALE_SEP);
			if( nInx != -1) {
				tempValue = temperature.substring(nInx+1, temperature.length()-1);
			}
		}	
		this.temperature = tempValue;
	}	
	
	public String getMeasuredLocation() {	
		return this.measuredLocation;
	}

	public void setMeasuredLocation(String measuredLocation) {
		String measuredLocValue = measuredLocation;
		if(measuredLocation != null) {
			int nInx = measuredLocation.indexOf(KEY_VALE_SEP);
			if( nInx != -1) {
				measuredLocValue = measuredLocation.substring(nInx+1, measuredLocation.length()-1);
			}
		}	
		this.measuredLocation = measuredLocValue;
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
