<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="user" scope="session" class="paybar.model.DetailAccount"/>

<c:if test="${ user.id le 1 }">
  <jsp:forward page="index.jsp"></jsp:forward>
</c:if>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
	<title>Paybar</title>
	<link rel="stylesheet" href="./css/main.css" type="text/css" />
</head>
<body>
	<jsp:include page="include/header.jsp" />
	<div id="main">
		<div id="main2">
			<jsp:include page="include/sidebar.jsp" />
			<div id="content">			
				<h2>Paybar Management System</h2>

      			<p>Welcome to the Paybar User management System. Here you can Adapt the Personal User Information, verify your Transactions and lock the access of devices in case of theft.</p>
			</div>
		</div>
		<div style="clear:both;"></div>
	</div>
	<jsp:include page="include/footer.jsp" />
</body>
</html>