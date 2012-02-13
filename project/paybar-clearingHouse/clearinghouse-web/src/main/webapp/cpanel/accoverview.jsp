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
	<div id="header">
		<h1>Paybar - Manage your account</h1>
	</div>
	<div id="main">
		<div id="main2">
			<jsp:include page="include/sidebar.jsp" />
			<div id="content">
				<h2>Account Overview</h2>

				<div>
					<p>User ID: ${user.id}</p>
					<p>User name: ${user.userName}</p>
					<p>First name: ${user.firstName}</p>
					<p>Last name: ${user.sureName}</p>
					<p>Phone Number: ${user.phoneNumber}</p>
					<p>Credit: ${user.credit}</p>
				</div>
			</div>
		</div>
		<div style="clear: both;"></div>
	</div>
	<jsp:include page="include/footer.jsp" />
</body>
</html>