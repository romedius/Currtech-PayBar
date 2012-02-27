<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:useBean id="partner" scope="session"
	class="paybar.model.Partner" />

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
				<c:if test="${not empty error}"><p style="color: red;">${error}</p></c:if>		
				<h2>Transactions</h2>
                <div>
                	<c:choose>
                		<c:when test="${ empty transactions }">
                			<p>You have no transactions to display</p>
                		</c:when>
                		<c:otherwise>
							<table cellspacing="5">
		                		<tr>
		                			<th>ID</th><th>Amount</th><th>Transaction time</th><th>POS</th>
		                		</tr>
		                		<c:forEach items="${ transactions }" var="t">
		                			<tr>
		                				<td>${t.id}</td><td>${t.amount/100}</td>
		                				<td>${t.transactionTime}</td>
		                				<td>${t.pos.name}</td>
		                			</tr>
		                		</c:forEach>
		                	</table>
                		</c:otherwise>
                	</c:choose>
                </div>
			</div>
		</div>
		<div style="clear: both;"></div>
	</div>
	<jsp:include page="include/footer.jsp" />
</body>
</html>