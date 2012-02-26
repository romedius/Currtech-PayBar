<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="user" scope="session" class="paybar.model.DetailAccount"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Hotel Management - Control Panel</title>
	<link rel="stylesheet" href="./css/main.css" type="text/css" />
</head>
<body>
	<div id="wrapperlogin">
		<div>
			<h2>Paybar - Partner Login</h2>
		</div>
		<form name="cPanelLogin" method="post" action="PartnerLoginServlet">
			<fieldset >
				<div  style="padding:20px;">
				<p>
					<label for="username">Login:</label> <input class="loginInput" name="username" type="text" value="" />
				</p>
				<p>
					<label for="password">Password:&nbsp;</label> <input class="loginInput" name="password" type="password" value="" />
				</p>
				<p style="text-align: right;">
					<button style="width: 100px;" type="submit" name="Submit">
						Login
					</button>
				</p>
				<c:if test="${not empty error}"><p style="color: red;">${error}</p></c:if>
				</div>
			</fieldset>
		</form>
	</div>
</body>
</html>