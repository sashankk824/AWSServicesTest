<%@ page language="java" contentType="text/html; charset=US-ASCII" pageEncoding="US-ASCII"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="/WEB-INF/includes/commonInclude.jsp"/>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Trie Operation Page</title>

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
<form action="${contPath}/service/trie" type="POST>	
	<tbody>
		<tr>
			<td class="grayCell" width="1"><img src="${imagePath}/divider.gif" alt="" width="1" height="1" hspace="0" vspace="0" border="0"></td>
			<td class="itemHdr" colspan="3">Error Page </td>
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
				<p>
					<h2> Some error occured while processing this request. Please try after sometime. <h2>
				</p>				

			</td>
			<td class="grayCell" width="1"><img src="${imagePath}/divider.gif" alt="" width="1" height="1" hspace="0" vspace="0" border="0"></td>
		</tr>
		

		<tr>
			<td class="grayCell" width="1"><img src="${imagePath}/divider.gif" alt="" width="1" height="1" hspace="0" vspace="0" border="0"></td>
			<td class="text" colspan="3" height="50">	
				  <br><br>
			</td>
			<td class="grayCell" width="1"><img src="${imagePath}/divider.gif" alt="" width="1" height="1" hspace="0" vspace="0" border="0"></td>
		</tr>	

		
		<tr>
			<td class="grayCell" width="1" height="1"><img src="${imagePath}/divider.gif" alt="" width="1" height="1" hspace="0" vspace="0" border="0"></td>
			<td class="grayCell" colspan="3"> <img src="${imagePath}/divider.gif" alt="" width="1" height="1" hspace="0" vspace="0" border="0"></td>
			<td class="grayCell" height="1"><img src="${imagePath}/divider.gif" alt="" height="1" hspace="0" vspace="0" border="0"></td>
		</tr>
	</form>			
</table>	

</div>
</body>
</html>