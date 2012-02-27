<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:useBean id="user" scope="session"
	class="paybar.model.DetailAccount" />

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
				<h2>Account Overview</h2>
				
				<c:if test="${not empty error}"><p style="color: red;">${error}</p></c:if>
				
				<div>
					<p>User ID: ${daOverview.id}</p>
					<p>User name: ${daOverview.userName}</p>
					<p>First name: ${daOverview.firstName}</p>
					<p>Last name: ${daOverview.sureName}</p>
					<p>Address: ${daOverview.adress}</p>
					<p>Phone Number: ${daOverview.phoneNumber}</p>
					<p>Credit: ${daOverview.credit/100}</p>
				</div>
				<div>
					<a href="PrepareChangeAccountServlet">Change account data</a>
				</div>
			</div>
		</div>
		<div style="clear: both;"></div>
	</div>
	<jsp:include page="include/footer.jsp" />
</body>
</html>