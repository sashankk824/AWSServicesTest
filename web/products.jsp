<%@ page language="java" contentType="text/html; charset=US-ASCII" pageEncoding="US-ASCII"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="/WEB-INF/includes/commonInclude.jsp"/>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Products Home Page</title>

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

			<h1> Please click on <a href="${contPath}/index.jsp">Home</a> to back main page <h1>


			<h1> AWS N-Tier Access Demo </h1>
			<br><br>
			<h2> Available Products from AWS EC2 MySQL DB</h2>
			<%-- Using JSTL forEach and out to loop a list and display items in table --%>
			<table border="1" width="800">
			<tbody>
			<tr>
				<th>Product ID</th>
				<th>Product Code</th>
				<th>Name</th>
				<th>Prod Description</th>
				<th>quantity</th>
				<th>price</th>	
			</tr>


			<c:forEach items="${requestScope.productsList}" var="prod">
				<tr>
					<td><c:out value="${prod.productID}"></c:out></td>
					<td><c:out value="${prod.productCode}"></c:out></td>	
					<td><c:out value="${prod.name}"></c:out></td>
					<td><c:out value="${prod.prodDesc}"></c:out></td>
					<td><c:out value="${prod.quantity}"></c:out></td>
					<td><c:out value="${prod.price}"></c:out></td>	
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