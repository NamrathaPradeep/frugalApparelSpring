
<%@page import="java.util.Map.Entry"%>
<%@page import="java.util.Map.Entry"%>
<%@page import="com.npu.dao.UserDAO"%>
<%@page import="com.npu.domain.Product"%>
<%@page import="com.npu.domain.UserOrder"%>
<%@page import="com.npu.domain.CardInfo"%>
<%@page import="com.npu.domain.User"%>
<%@page import="java.util.ArrayList"%>
<%@page import="javax.sql.DataSource"%>
<%@page import="java.sql.SQLException"%>
<%@page import="com.npu.dao.CategoryProductDAO"%>
<%@page import="com.npu.dao.OrderAndOrderMappingDAO"%>
<%@page import="com.npu.dao.CardDAO"%>
<%@page import="com.npu.domain.Category"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.ArrayList"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User Profile</title>

<style type="text/css" media="Screen">
h2 {
	color: #8a0829;
	text-decoration: underline;
}

ul,li {
	
	list-style-type: none;
	color: #8a0829;
	margin: 10px 30px 10px 0;
}

.centerText {
	text-align: center;
}

.btn,.btn:hover {
	background: #FFE4D4;
	background-image: -webkit-linear-gradient(top, #FFE4E1, pink);
	background-image: -moz-linear-gradient(top, #FFE4E1, pink);
	background-image: -ms-linear-gradient(top, #FFE4E1, pink);
	background-image: -o-linear-gradient(top, #FFE4E1, pink);
	background-image: linear-gradient(to bottom, #FFE4E1, pink);
	-webkit-border-radius: 28;
	-moz-border-radius: 28;
	border-radius: 48px;
	text-shadow: 0px 1px 0px #666666;
	font-family: Courier New, monospace;
	color: #8a0829;
	font-size: 12px;
	padding: 10px 20px 10px 20px;
	text-decoration: none;
	width: 170px;
	margin-top: 8px;
	border: 4px solid #ddd;
}
</style>
<script>
	function getOrderDetails(orderid) {
		var xmlhttp;
		if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
			xmlhttp = new XMLHttpRequest();
		} else {// code for IE6, IE5
			xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
		}

		xmlhttp.onreadystatechange = function() {
			if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
				var data = JSON.parse(xmlhttp.responseText);
				//document.getElementById("myDiv").innerHTML=data.products[1].proudctName;
				var products = "The products for this order are : \n";
				for (i = 0; i < data.products.length; i++) {
					products += data.products[i].proudctName + ",";
				}
				alert(products);
			}
		}
		xmlhttp.open("GET", "getOrderDetails?orderid=" + orderid, true);
		xmlhttp.send();
	}
</script>

<%

	//User
	User user = (User) request.getAttribute("user");


%>


</head>
<body style="background-color: #FFE4E1; font-family: helvetica;">
	<div>
		<h1 align="center"
			style="background-color: #F6CECE; color: #8a0829; text-decoration: none;">Frugal
			Apparel</h1>
				<a class="btn"
				style="width: 50px; float: right; margin: 45px 2px 20px 0; background: #FFF0F5; font-size: 16px; border-radius: 28px;"
				href="logout">Logout</a> 
				
				<a class="btn"
				style="width: 70px; float: right; margin: 45px 2px 20px 0; border-radius: 28px; background: #FFF0F5; font-size: 16px;"
				href="homepage">HomePage</a>
		
			<h2>User Details</h2>
		<ul>
	
			<li>User Id: <%=user.getUser_id()%></li>
			<li><u>First Name: </u><%=user.getFirstName()%></li>
			<li><u>Last Name: </u> <%=user.getLastName()%></li>
			<li><u>Address: </u> 
				<%
	 			out.println(user.getStreet() + ", " + user.getAptNo() + ",  "
	 			+ user.getCity() + ",  " + user.getState() + ",  "
	 			+ user.getZip());
	 			%>
	 		</li>
			<li><u>Email id: </u><%=user.getEmailId()%></li>
		</ul>

	</div>
</body>
</html>