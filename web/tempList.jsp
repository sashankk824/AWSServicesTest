<%@ page language="java" contentType="text/html; charset=US-ASCII" pageEncoding="US-ASCII"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="/WEB-INF/includes/commonInclude.jsp"/>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Temperature List Page</title>

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
<tbody>
	<tr>
		<td class="grayCell" width="1"><img src="${imagePath}/divider.gif" alt="" width="1" height="1" hspace="0" vspace="0" border="0"></td>
		<td class="itemHdr" colspan="3">Temperature Measurement List and AWS Serverless Lambda </td>
		<td class="grayCell" width="1"><img src="${imagePath}/divider.gif" alt="" width="1" height="1" hspace="0" vspace="0" border="0"></td>
	</tr>

	<tr>
		<td class="grayCell" width="1" height="2"><img src="${imagePath}/divider.gif" alt="" width="1" height="1" hspace="0" vspace="0" border="0"></td>
		<td class="clear" colspan="3"> <img src="${imagePath}/divider.gif" alt="" width="1" height="1" hspace="0" vspace="0" border="0"></td>
		<td class="grayCell" width="1" height="2"><img src="${imagePath}/divider.gif" alt="" height="1" hspace="0" vspace="0" border="0"></td>
	</tr>
	
	<tr>
		<td class="grayCell" width="1"><img src="${imagePath}/divider.gif" alt="" width="1" height="1" hspace="0" vspace="0" border="0"></td>
		<td class="text" colspan="3">	
			<br>
			<p>
			<h1> Please click on <a href="${contPath}/index.jsp">Home</a> to back main page <h1>

			<br>
			<h1> Available Data from AWD Dynamo DB </h1>
			<br><br>	
			<h1> Please click on <a href="${contPath}/tempPut.jsp">Add Temperature Record</a> <h1>
			</p>
		</td>
		<td class="grayCell" width="1"><img src="${imagePath}/divider.gif" alt="" width="1" height="1" hspace="0" vspace="0" border="0"></td>
	</tr>
	
	<tr>
		<td class="grayCell" width="1"><img src="${imagePath}/divider.gif" alt="" width="1" height="1" hspace="0" vspace="0" border="0"></td>
		<td class="text" colspan="3">	
			
			<h2> Temperature List: </h2>
			<br>
			<table border="1" width="800">
			<tbody>	
				<tr>
					<th>Mesured Time </th>
					<th>Temperature</th>
					<th>Measured Location</th>
				</tr>


				<c:forEach items="${requestScope.tempList}" var="tempData">
					<tr>
						<td>
						<a href="${contPath}/servlet/TempMeasureServlet?operation=details&measure_time=${tempData.measuredTime.stringValue}"><c:out value="${tempData.measuredTime.stringValue}"></c:out></a>
						</td>
						<td><c:out value="${tempData.temperature.numberValue}"></c:out></td>	
						<td><c:out value="${tempData.measuredLocation.stringValue}"></c:out></td>
					</tr>

				</c:forEach>
			</tbody>
			</table>
			<br><br>

		</td>
		<td class="grayCell" width="1"><img src="${imagePath}/divider.gif" alt="" width="1" height="1" hspace="0" vspace="0" border="0"></td>
	</tr>
	<tr>
		<td class="grayCell" width="1" height="1"><img src="${imagePath}/divider.gif" alt="" width="1" height="1" hspace="0" vspace="0" border="0"></td>
		<td class="grayCell" colspan="3"> <img src="${imagePath}/divider.gif" alt="" width="1" height="1" hspace="0" vspace="0" border="0"></td>
		<td class="grayCell" height="1"><img src="${imagePath}/divider.gif" alt="" height="1" hspace="0" vspace="0" border="0"></td>
	</tr>	
</table>	
</div>
</body>
</html>