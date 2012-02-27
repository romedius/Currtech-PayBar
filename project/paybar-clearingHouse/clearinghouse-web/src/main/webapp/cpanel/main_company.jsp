<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="partner" scope="session" class="paybar.model.Partner"/>

<c:if test="${ partner.id le 1 }">
  <jsp:forward page="login_company.jsp"></jsp:forward>
</c:if>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
	<title>Paybar</title>
	<link rel="stylesheet" href="./css/main.css" type="text/css" />
</head>
<body>
	<jsp:include page="include/header_company.jsp" />
	<div id="main">
		<div id="main2">
			<jsp:include page="include/sidebar_company.jsp" />
			<div id="content">			
				<h2>Paybar Management System</h2>

      			<p>Welcome to the Paybar Partner management System. Here you can keep track off all transactions made from this company and it's customers.</p>
			</div>
		</div>
		<div style="clear:both;"></div>
	</div>
	<jsp:include page="include/footer.jsp" />
</body>
</html>