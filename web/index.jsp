<%@ page language="java" contentType="text/html; charset=US-ASCII" pageEncoding="US-ASCII"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="/WEB-INF/includes/commonInclude.jsp"/>

<html><head><meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
<title>AWS Cloud Examples</title>
<meta content="no-cache" http-equiv="Pragma">

<link rel="stylesheet" type="text/css" href="${cssPath}/skscore.css">

</head>


<body>


<!-- START DIV header -->

<div id="header" style="top:0px;">
<h1> <p style="color:white"> Static and Dynamic Web Projects </p> </h1>
</div>

<!-- END DIV header -->


<!-- START DIV topInfoBar -->

<div id="topInfoBar">
</div>

<!-- END DIV topInfoBar -->


<!-- START DIV centerZones -->
<div id="centerZones" style="top:110px;">

<table cellpadding="0" cellspacing="0" border="0" width="900">

	<tbody><tr>
		<td class="grayCell" width="1"><img src="${imagePath}/divider.gif" alt="" width="1" height="1" hspace="0" vspace="0" border="0"></td>
		<td class="itemHdr" colspan="3">AWS Services and Validation Project </td>
		<td class="grayCell" width="1"><img src="${imagePath}/divider.gif" alt="" width="1" height="1" hspace="0" vspace="0" border="0"></td>
	</tr>

	<tr>
		<td class="grayCell" width="1" height="2"><img src="${imagePath}/divider.gif" alt="" width="1" height="1" hspace="0" vspace="0" border="0"></td>
		<td class="clear" colspan="3"> <img src="${imagePath}/divider.gif" alt="" width="1" height="1" hspace="0" vspace="0" border="0"></td>
		<td class="grayCell" height="2"><img src="${imagePath}/divider.gif" alt="" height="1" hspace="0" vspace="0" border="0"></td>
	</tr>


	<tr>
		<td class="grayCell" width="1"><img src="${imagePath}/divider.gif" alt="" width="1" height="1" hspace="0" vspace="0" border="0"></td>
		<td class="text" colspan="3">

		<p>
		Sample Static and Dynamic Web Appliaation Projects

		<br><br>
		<h1> AWS Stack - Apache Web Server Page <h1>
		<a href="${contPath}/html5test.html">HTML 5 Graphics Example</a>
		
		<br><br>
		<h1> AWS Stack - Tomcat Dynamic Page <h1>
		<a href="${contPath}/servlet/AWSTestServlet">Available Product List Example</a>		

		<br><br>
		<h1> AWS Temparature Page Using Serverless API - Lambda <h1>
		<a href="${contPath}/servlet/TempMeasureServlet?operation=get_list">Temparature List Example</a>				
		
		<br>		
		</p>
		</td>
		<td class="grayCell" width="1"><img src="${imagePath}/divider.gif" alt="" width="1" height="1" hspace="0" vspace="0" border="0"></td>

	</tr>

	<tr>
		<td class="grayCell" width="1" height="16"><img src="${imagePath}/divider.gif" alt="" width="1" height="1" hspace="0" vspace="0" border="0"></td>
		<td class="clear" colspan="3"> <img src="${imagePath}/divider.gif" alt="" width="1" height="1" hspace="0" vspace="0" border="0"></td>
		<td class="grayCell" height="16"><img src="${imagePath}/divider.gif" alt="" height="1" hspace="0" vspace="0" border="0"></td>
	</tr>

	<tr>
		<td class="grayCell" width="1" height="1"><img src="${imagePath}/divider.gif" alt="" width="1" height="1" hspace="0" vspace="0" border="0"></td>
		<td class="grayCell" colspan="3"> <img src="${imagePath}/divider.gif" alt="" width="1" height="1" hspace="0" vspace="0" border="0"></td>
		<td class="grayCell" height="1"><img src="${imagePath}/divider.gif" alt="" height="1" hspace="0" vspace="0" border="0"></td>
	</tr>

</tbody></table>

<br>
</div>
</body></html>