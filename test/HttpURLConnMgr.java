package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.util.Date;
import java.util.Calendar;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class HttpURLConnMgr {

	private static final String USER_AGENT = "Mozilla/5.0";
	private static final String GET_TEMP_LIST = "GET_LIST";
	private static final String GET_TEMP = "GET_TEMP";
	private static final String PUT_ROWS = "PUT_ROWS";
	
	private static final String POST_QUERY_PARAMS = "'{'\"MeasuredTime\": \"{0}\"'}'";
	private static final String POST_PUT_PARAMS = "'{'\"Temparature\": \"{0}\",\"MeasuredTime\": \"{1}\", \"MeasuredLocation\": \"{2}\"'}'";
	private static DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy hh:mm");
	
	private int sleeptime = 60;
	private int maxLimit = 20;
	private float tempBaseReading = 72.0f;	
	
	//get/set methods
/*	
	public String getApiGateGetListURL() {
		return this.apiGateGetListURL;
	}
	
	public void setApiGateGetListURL(String apiGateGetListURL) {
		this.apiGateGetListURL = apiGateGetListURL;
	}
	
	public String getApiGateTempRecordURL() {
		return this.apiGateTempRecordURL;
	}
	
	public void setApiGateTempRecordURL(String apiGateTempRecordURL) {
		this.apiGateTempRecordURL = apiGateTempRecordURL;
	}

	public String getApiGatePutURL() {
		return this.apiGatePutURL;
	}
	
	public void setApiGatePutURL(String apiGatePutURL) {
		this.apiGatePutURL = apiGatePutURL;
	}
*/	
	public int getSleeptime() {
		return this.sleeptime;
	}
	
	public void setSleeptime(int sleeptime) {
		this.sleeptime = sleeptime;
	}

	public int getMaxLimit() {
		return this.maxLimit;
	}
	
	public void setMaxLimit(int maxLimit) {
		this.maxLimit = maxLimit;
	}	
	
	public float getTempBaseReading() {
		return this.tempBaseReading;
	}
	
	public void setTempBaseReading(float tempBaseReading) {
		this.tempBaseReading = tempBaseReading;
	}
	
	//Main methods
	public TempServiceData getTempRecords(int count, String apiGateURL) throws IOException {
		//return convertJSONRecordsToJava();
		return processGET(apiGateURL, GET_TEMP_LIST);
	}
	
	public TempServiceData getTempRecord(String measuredTime, String apiGateURL) throws IOException {
		Object[] paramArgs = {measuredTime};
		return processPOST(apiGateURL, MessageFormat.format(POST_QUERY_PARAMS, paramArgs), GET_TEMP);
	}
	
	public TempServiceData putTempRecord(float tempValue, 
		String tempMeasurementLoc, String apiGateURL) throws IOException {
		Object[] paramArgs = {String.format("%.2f", tempValue), convertDatetoString(), tempMeasurementLoc};
		return processPOST(apiGateURL, MessageFormat.format(POST_PUT_PARAMS, paramArgs), PUT_ROWS);
	}	
	
	//Multiple Records
	public TempServiceData putTempRecords(String apiGateURL) throws IOException {
		TempServiceData tempServiceData = null;
		float tempValue = tempBaseReading;
		int counter = 0;
		while(counter <= maxLimit) {
			try {
				tempValue += 0.02;
				Object[] paramArgs = {String.format("%.2f", tempValue), convertDatetoString()};
				tempServiceData = processPOST(apiGateURL, MessageFormat.format(POST_PUT_PARAMS, paramArgs), PUT_ROWS);
				Thread.sleep(sleeptime*1000);
				counter++;
			} catch( InterruptedException ie) {}
		}
		return tempServiceData;
	}

	//Local Methods
	private TempServiceData processGET(String url, String oper) throws IOException {
		long lStartTime = System.currentTimeMillis();		
		TempServiceData tempServiceData = null;	
		try {
			URL obj = new URL(url);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("User-Agent", USER_AGENT);
			int responseCode = con.getResponseCode();
			System.out.println("GET Response Code :: " + responseCode);
			if (responseCode == HttpURLConnection.HTTP_OK) { // success
				BufferedReader in = new BufferedReader(new InputStreamReader(
						con.getInputStream()));
				String inputLine;
				StringBuffer response = new StringBuffer();

				while ((inputLine = in.readLine()) != null) {
					response.append(inputLine);
				}
				in.close();

				// print result
				System.out.println(response.toString());	
				tempServiceData = parseResponseMsg(response.toString(), oper);			
			} else {
				System.out.println("GET request not worked");
			}
		} finally {
			System.out.println("\nprocessGET : GET : Total Time TAKEN in Milli Secs to GET Records = "+
				(System.currentTimeMillis() - lStartTime));
		}
		return tempServiceData;
	}
	
	private TempServiceData processPOST(String url, String msgPayload, String oper) throws IOException {
		long lStartTime = System.currentTimeMillis();	
		TempServiceData tempServiceData = null;
		try {
			URL obj = new URL(url);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
			con.setRequestMethod("POST");
			con.setRequestProperty("User-Agent", USER_AGENT);

			// For POST only - START
			con.setDoOutput(true);
			OutputStream os = con.getOutputStream();
			os.write(msgPayload.getBytes());
			os.flush();
			os.close();
			// For POST only - END

			int responseCode = con.getResponseCode();
			System.out.println("POST Response Code :: " + responseCode);

			if (responseCode == HttpURLConnection.HTTP_OK) { //success
				BufferedReader in = new BufferedReader(new InputStreamReader(
						con.getInputStream()));
				String inputLine;
				StringBuffer response = new StringBuffer();

				while ((inputLine = in.readLine()) != null) {
					response.append(inputLine);
				}
				in.close();

				// print result
				System.out.println(response.toString());
				
				tempServiceData = parseResponseMsg(response.toString(), oper);
			} else {
				System.out.println("POST request not worked");
			}
		} finally {
			System.out.println("\nprocessPOST : POST : Total Time TAKEN in Milli Secs to GET Records = "+ 
				(System.currentTimeMillis() - lStartTime));
		}			
		return tempServiceData;
	}

	private String convertDatetoString() {
		Date date = Calendar.getInstance().getTime();
		String strDate = dateFormat.format(date);	
		return strDate;
	}

	private TempServiceData parseResponseMsg(String response, String oper ) throws IOException {

		//create ObjectMapper instance
		ObjectMapper objectMapper = new ObjectMapper();		
		//convert json string to object
		TempServiceData tempServiceData = objectMapper.readValue(response, TempServiceData.class);
		System.out.println("Temarature Service Object = "+tempServiceData);		
	
		if( !oper.equals(PUT_ROWS) && 
			tempServiceData != null && tempServiceData.getBody() != null && tempServiceData.getBody().length() > 0 ) {
		
			if( oper.equals(GET_TEMP_LIST) ) {
				List<RowTempData> listTempData = objectMapper.readValue(tempServiceData.getBody(), new TypeReference<List<RowTempData>>() {});
				tempServiceData.setTempDataList(listTempData);	
				
				//System.out.println("Temarature Data Object = "+listTempData);
			} else if( oper.equals(GET_TEMP) ) {
				TempData tempData = objectMapper.readValue(tempServiceData.getBody(), TempData.class);
				tempServiceData.setTempData(tempData);	
				
				System.out.println("\nTemarature Data Object = "+ tempData);
			}
		}
		return tempServiceData;
	}	
/*	
	private TempServiceData getResponseMsg(String response) throws IOException {
		//create ObjectMapper instance
		ObjectMapper objectMapper = new ObjectMapper();		
		//convert json string to object
		TempServiceData tempServiceData = objectMapper.readValue(response, TempServiceData.class);
		
		System.out.println("Temarature Service Object = "+tempServiceData);		
		
		if( tempServiceData != null && tempServiceData.getBody() != null && tempServiceData.getBody().length() > 0 ) {
			TempData tempData = objectMapper.readValue(tempServiceData.getBody(), TempData.class);	
			tempServiceData.setTempData(tempData);		
			System.out.println("Temarature Data Object = "+tempData);
		}
		return tempServiceData;
	}		
	
	private TempServiceData convertJSONRecordsToJava() throws IOException {
	
		ObjectMapper objectMapper = new ObjectMapper();		
		//convert json string to object
		//String strBody = "[{\"Temparature\":{\"N\":\"78.32\"},\"MeasuredTime\":{\"S\":\"05-04-2019 01:25\"},\"MeasuredLocation\":{\"S\":\"Ashburn\"}},{\"Temparature\":{\"N\":\"74.03\"},\"MeasuredTime\":{\"S\":\"06-21-2020 04:02\"},\"MeasuredLocation\":{\"S\":\"Ashburn\"}}]";
		//String strBody = "[{\"Temparature\":\"78.32\",\"MeasuredTime\":\"05-04-2019 01:25\",\"MeasuredLocation\":\"Ashburn\"},{\"Temparature\":\"74.03\",\"MeasuredTime\":\"06-21-2020 04:02\",\"MeasuredLocation\":\"Ashburn\"}]";
		//Alternative
		//List<TempData> listTempData = objectMapper.readValue(strBody, new TypeReference<List<TempData>>() {});
		//System.out.println("Temarature Data Object = "+listTempData);

		List<RowTempData> listTempData = objectMapper.readValue(tempServiceData.getBody(), new TypeReference<List<RowTempData>>() {});
		System.out.println("Temarature Data Object = "+listTempData);		
	
		TempServiceData tempServiceData = new TempServiceData();
		tempServiceData.setStatusCode("200");
		tempServiceData.setTempDataList(listTempData);
		return tempServiceData;
	}	
*/	
}