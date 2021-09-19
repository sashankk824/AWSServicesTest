package test;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NumStringValue {
	@JsonProperty("N")
	private String numberValue="";
	@JsonProperty("S")
	private String stringValue="";
	
	public String getNumberValue() {	
		return this.numberValue;
	}

	public void setNumberValue(String numberValue) {
		this.numberValue = numberValue;
	}

	public String getStringValue() {	
		return this.stringValue;
	}

	public void setStringValue(String stringValue) {
		this.stringValue = stringValue;
	}
	
	@Override
	public String toString(){
		StringBuilder builder = new StringBuilder();
		builder.append(" numberValue ="+ numberValue)		
			.append(", stringValue ="+ stringValue);
		return builder.toString();
	}	
}