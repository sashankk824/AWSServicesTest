package test;

import java.util.List;
import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TempServiceData {
	private String statusCode = "";
	@JsonProperty("body")
	private String body = null;	
	private List<RowTempData> listTempData = null;
	private TempData tempData = null;
	
	public String getStatusCode() {
		return this.statusCode;
	}
	
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
	
	public String getBody() {
		return this.body;
	}
	
	public void setBody(String body) {
		this.body = body;
	}
	
	public List<RowTempData> getTempDataList() {
		return this.listTempData;
	}

	public void setTempDataList(List<RowTempData> listTempData) {
		this.listTempData = listTempData;
	}	
	
	public TempData getTempData() {
		return this.tempData;
	}

	public void setTempData(TempData tempData) {
		this.tempData = tempData;
	}		

	@Override
	public String toString(){
		StringBuilder builder = new StringBuilder();
		builder.append(" statusCode ="+ statusCode)
			.append(", Body ="+ listTempData)	
			.append(", listTempData ="+ body)				
			.append(", TempData ="+ listTempData);
		return builder.toString();
	}	
}