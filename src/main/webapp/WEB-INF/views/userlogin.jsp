<%@ include file="./include.jsp"%>
<%@ page session="false"%>
<c:set var="context" scope="request"
	value="<%=request.getContextPath()%>" />
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>

<style>
div {
	display: block;
	color: #8A0829;
	font-family: helvetica;
}
</style>
</head>
<body style="background-color: #FFE4E1;">

	<%
		//String userid = (String) session.getAttribute("userid");

		//User
		String message = (String) request.getAttribute("message");
	%>

	<div align="center">
		<h1 style="background-color: #F6CECE;">Frugal Apparel</h1>
		<p style="color: #B40431;">
			If you are not a registered user please <a href="register.html"
				style="color: #585858;">Register here</a>
		</p>
		<form action="login" method="post">
			User Id: <input type="text" size="35" name="user_id"
				style="margin-left: 10px;" /><br /> <br /> Password: <input
				type="password" size="35" name="passcode" /><br /> <br /> <input
				type="submit" value="login" />
				
				<%if(message != null) { %> <p align=center> Please 
						enter a valid username and password.</p>  
						<% } %>

		</form>

	</div>
</body>
</html>
