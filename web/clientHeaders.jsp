<html>
<head>
<title> Krishna EC2 - Tomcat HTTP Headers Example</title>
</head>
<body>

<h1> THIS IS A TOMCAT SERVER IN AWS - Amazon Linux EC2 </h1>

<h2> HTTP Headers Example </h2>

Arbitrary Header:
    The user agent is <%= request.getHeader("user-agent") %> <br>

    Implicit Headers:  
    Request method:<%= request.getMethod() %> <br>

    Request URI:<%= request.getRequestURI() %><%= request.getProtocol() %> <br>
	
	Server Host:<%= request.getServerName()%>:<%= request.getServerPort()%> <br>
	
	Remote Host:<%= request.getRemoteHost() %> <br>

	Remote Address:<%= request.getRemoteAddr() %> <br>

</body>
</html>
