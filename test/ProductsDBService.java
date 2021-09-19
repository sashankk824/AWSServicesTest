package test;

import java.sql.*;
import javax.sql.DataSource;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import java.util.Hashtable;
import java.util.Enumeration;
import java.util.Date;
import java.util.BitSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Calendar;


public class ProductsDBService {

    private static String strClassName_ = ProductsDBService.class.getName();
    private static final String GEN_SQL_PRE="SELECT * from ";
    private static final String EMPTY="";
    private static final String BLANK=" ";
    private static final char EMPTY_CHAR=' ';
    private static final char DASH_CHAR='-';
    private static final String COMMA_SEP=",";
    private static final char DOT_CHAR='.';
    private static final char SINGLE_QUOTE_CHAR='\'';
    private static final String NEW_LINE_SEP="\n";


	private static String strSQLQuery_ =
		"select productID, productCode, name, prod_desc, quantity, price  from products";
	private static String DEFAULT_JNDI_NAME="jdbc/AWSMyTestDB";

	//Jdbc Dirver Info to log failed records
	private static String strJNDIName_ = "";
	private static String strDriver_ = "com.mysql.jdbc.Driver";	
	private static String strJdbcURL_ = "";
	private static String strJdbcUID_   = "";
	private static String strJdbcPwd_ = "";
	private static boolean bIsUseDS_ = true;
	private Connection connDBSrc_ = null;
	private DataSource ds_ = null;

	public static void init( String strJNDIName, String strDriver, String strJdbcURL, 
				String strJdbcUID, String strJdbcPwd, boolean bIsUseDS ) {

		strJNDIName_ = strJNDIName;
		strDriver_ = strDriver;
		strJdbcURL_ = strJdbcURL;
		strJdbcUID_ = strJdbcUID;
		strJdbcPwd_ = strJdbcPwd;
		bIsUseDS_ = bIsUseDS;
			
		//strQueryBindValue_ = System.getProperty("QueryBindValue");
	}
	
	//Conneciton APIs
	private Connection getDirectConnection() throws SQLException {
		
		long lStartTime = System.currentTimeMillis();
		
		Connection conn = null;
		try {
			Class.forName(strDriver_).newInstance();
		}
		catch (Exception e) {
			Logger.err(e, strClassName_, " : getDirectConnection : SQLException : Failed "+
			"to load Driver because :::: "+ e );
		}
		String strDB = " DB Connecting -> "+strJdbcURL_;

		try {

			conn = DriverManager.getConnection( strJdbcURL_,
								strJdbcUID_, strJdbcPwd_ );
		}
		catch (SQLException e) {
			Logger.err(e, strClassName_, " : getDirectConnection : Exception : Failed "+
			" to connect to DB ="+strDB +" with Error Code ="+
            e.getErrorCode() +" And  getSQLState() ="+ e.getSQLState() +
            " Because :::: "+ e );
		} finally {
			Logger.log( strClassName_, " getDirectConnection : Total time taken to create connection = "+ 
							(System.currentTimeMillis()-lStartTime));
		}
		return conn; 		
	}
	
	private Connection getDSConnection() throws NamingException, SQLException {
		long lStartTime = System.currentTimeMillis();		
		if( ds_ == null) {
			synchronized(this) {
				Context initCtx = new InitialContext();
				Context envCtx = (Context) initCtx.lookup("java:comp/env");
				ds_ = (DataSource)envCtx.lookup(strJNDIName_);
			}
		}
		Logger.log( strClassName_, " getDSConnection : Total time taken to create connection = "+ 
						(System.currentTimeMillis()-lStartTime));
		
		return ds_.getConnection();
	}	
	
	private Connection getConnection() throws NamingException, SQLException {
		Connection conn = null; 
		if( bIsUseDS_ ) {
			conn = getDSConnection();
		} 
		else {
			conn = getDirectConnection();
		}
		return conn;
	}
	
	//Actual Business methods
	public List<ProductsData> getProducts() throws ProductsException {

		List<ProductsData> listProducts = new ArrayList<ProductsData>();

		Connection conn = null;
	
		PreparedStatement prepQuery = null;

		String strDB = " DB Connecting -> "+strJdbcURL_;

		try {
			conn = getConnection();
			prepQuery = conn.prepareStatement( strSQLQuery_,
                                ResultSet.TYPE_SCROLL_INSENSITIVE,
                                ResultSet.CONCUR_READ_ONLY );
								
			ResultSet rs = prepQuery.executeQuery();
			
			while( rs.next()) {
				
				ProductsData prodData = new ProductsData();
				prodData.setProductID( rs.getInt(1) );
				prodData.setProductCode( rs.getString(2) );
				prodData.setName( rs.getString(3) );
				prodData.setProdDesc( rs.getString(4) );	
				prodData.setQuantity( rs.getInt(5) );
				prodData.setPrice( rs.getFloat(6) );	

				listProducts.add(prodData);
			}

			if( rs != null ) {
				rs.close();
				rs = null;
			}
		}
		catch (Exception e) {
			Logger.err(e, strClassName_, " : processQuery : "+
				"Exception : Failed to connect to DB ="+strDB +
				" Because :::: "+ e );
			throw new ProductsException("Failed while fetching products data. Please try after sometime.");
		}
		finally {
			try {
				if( prepQuery != null ) {
					prepQuery.close();
					prepQuery = null;
				}
				if( connDBSrc_ != null ) {
					connDBSrc_.close();
					connDBSrc_ = null;
				}
			}
			catch(SQLException sqle ) {
				Logger.err(sqle, strClassName_, " : processQuery : "+
					"SQLException : Failed Because :::: "+ sqle );
			}
		}
		return listProducts;
	}

	//main starts here
	public static void main(String[] args) throws Exception {

		ProductsDBService prodDBService = new ProductsDBService();
		ProductsDBService.init(DEFAULT_JNDI_NAME, "com.mysql.jdbc.Driver", 
			"jdbc:mysql://ec2-52-38-25-50.us-west-2.compute.amazonaws.com:3306/KrishAWSTest?profileSQL=false",
			"kriaws", "swakri72", false);
		System.out.println( prodDBService.getProducts());
	}
}