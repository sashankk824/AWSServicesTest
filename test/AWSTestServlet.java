package test;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

public class AWSTestServlet extends HttpServlet {

    private static String strClassName_ = AWSTestServlet.class.getName();

	private static String JNDI_NAME = "jndiName";
    private static String DRIVER = "driver";
    private static String JDBC_URL = "jdbcURL";
    private static String JDBC_UID = "jdbcUID";
    private static String JDBC_PWD = "jdbcPwd";
    private static String IS_USE_DATA_SOURCE = "dataSourceFlag";
	
    private static String RESPONSE_PAGE = "/products.jsp";	
    private static String ERROR_PAGE = "/error.jsp";	
	
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		
		//TODO: Error checks and conditions
		String strIsUseDS = config.getInitParameter(IS_USE_DATA_SOURCE);
		boolean bIsUseDS = true;
		if( strIsUseDS != null && strIsUseDS.trim().length() > 0 ) {
			bIsUseDS = new Boolean(strIsUseDS).booleanValue();
		}
		
		ProductsDBService.init(config.getInitParameter(JNDI_NAME), config.getInitParameter(DRIVER), 
			config.getInitParameter(JDBC_URL), config.getInitParameter(JDBC_UID), 
			config.getInitParameter(JDBC_PWD), bIsUseDS);	
		
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
							throws ServletException, IOException {
		
		String strResPage = RESPONSE_PAGE;
		ProductsDBService prodDBService = new ProductsDBService();
		try {
			List<ProductsData> listProds = prodDBService.getProducts();
			request.setAttribute("productsList", listProds);
		}
		catch(ProductsException pe) {
			strResPage=ERROR_PAGE;
			Logger.err(pe, strClassName_, " : doGet : "+
				"ProductsException : Failed to get products "+
				" Because :::: "+ pe );			
		}
		
		RequestDispatcher rd = getServletContext().getRequestDispatcher(strResPage);
		rd.forward(request, response);
	}	
}
