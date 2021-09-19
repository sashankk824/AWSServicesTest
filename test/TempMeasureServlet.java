package test;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

public class TempMeasureServlet extends HttpServlet {

    private static final String strClassName_ = TempMeasureServlet.class.getName();
    private static final String TEMP_LIST = "get_list";
	private static final String TEMP_DETAILS = "details";
	private static final String TEMP_PUT = "put";
	private static final String TEMP_PUT_MULTI = "put_multi";
	private static final String MEASURE_TIME = "measure_time";	
	private static final String TEMP_READING = "temp_reading";	
	private static final String MEASUREMENT_LOC = "measurement_loc";

	private static String GET_LIST_URL = "getListURL";
	private static String GET_TEMP_RECORD_URL = "getTempRecordURL";
	private static String PUT_TEMP_RECORD_URL = "putTempRecordURL";
    private static String OPERATION = "operation";
    private static String SLEEPTIME = "sleeptime";
	
    private static String INDEX_PAGE = "/index.jsp";    
	private static String RESPONSE_LIST_PAGE = "/tempList.jsp";
    private static String RESPONSE_DETAILS_PAGE = "/tempDetails.jsp";	
    private static String ERROR_PAGE = "/error.jsp";	

	private String getListURL = "";
	private String getTempRecordURL = "";
	private String putTempRecordURL = "";	
	private int sleeptime = 300;	
	
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		
		getListURL = config.getInitParameter(GET_LIST_URL);
		getTempRecordURL = config.getInitParameter(GET_TEMP_RECORD_URL);
		putTempRecordURL = config.getInitParameter(PUT_TEMP_RECORD_URL);
		String strSleeptime = config.getInitParameter(SLEEPTIME);
		if( strSleeptime != null && strSleeptime.trim().length() > 0 ) {		
			try {
				sleeptime = Integer.parseInt(strSleeptime);	
			} catch(NumberFormatException nfe) {
			}
		}		
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
							throws ServletException, IOException {
		doProcess(request, response);
	}	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
							throws ServletException, IOException {
		doProcess(request, response);
	}	
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
							throws ServletException, IOException {

		String strResPage = RESPONSE_LIST_PAGE;		
		String operation = request.getParameter(OPERATION);
		
		System.out.println("IN doProcess operation ="+operation);
				
		try {
			HttpURLConnMgr httpURLConnMgr = new HttpURLConnMgr();
			httpURLConnMgr.setSleeptime(sleeptime);			
			if(operation != null && operation.equals(TEMP_LIST)) {
				TempServiceData tempServiceData = httpURLConnMgr.getTempRecords(5, getListURL);
				if( tempServiceData != null ) {
					request.setAttribute("tempList", tempServiceData.getTempDataList());
				}
				System.out.println("GET DONE");
				
			} else if(operation != null && operation.equals(TEMP_DETAILS)) {
				strResPage = RESPONSE_DETAILS_PAGE;	
				String measureTime = request.getParameter(MEASURE_TIME);				
				TempServiceData tempServiceData = httpURLConnMgr.getTempRecord(measureTime, getTempRecordURL);
				if( tempServiceData != null ) {
					request.setAttribute("tempDetails", tempServiceData.getTempData());
				}
				System.out.println("POST DONE tempServiceData ="+tempServiceData);
				
			} else if(operation != null && operation.equals(TEMP_PUT)) {	
				//strResPage = INDEX_PAGE;
				String tempReading = request.getParameter(TEMP_READING);
				String tempMeasurementLoc = request.getParameter(MEASUREMENT_LOC);
				if( tempReading != null && tempReading.trim().length() > 0 ) {
					try {
						TempServiceData tempServiceData = 
							httpURLConnMgr.putTempRecord(Float.valueOf(tempReading), tempMeasurementLoc, putTempRecordURL);
					} catch( NumberFormatException ne ){
						strResPage=ERROR_PAGE;
					}
					//TODO
					TempServiceData tempServiceRefreshData = httpURLConnMgr.getTempRecords(5, getListURL);
					if( tempServiceRefreshData != null ) {
						request.setAttribute("tempList", tempServiceRefreshData.getTempDataList());
					}
				}
				System.out.println("PUT ROWS DONE");
			} else if(operation != null && operation.equals(TEMP_PUT_MULTI)) {
		
			}
		}
		catch(Exception ex) {
			strResPage=ERROR_PAGE;
			Logger.err(ex, strClassName_, " : doProcess : Exception : Failed to process "+ 
				operation + "request.  Because :::: "+ ex );			
		}
		
		RequestDispatcher rd = getServletContext().getRequestDispatcher(strResPage);
		rd.forward(request, response);
	}	
}
