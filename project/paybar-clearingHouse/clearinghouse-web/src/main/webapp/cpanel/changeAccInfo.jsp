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

<!-- jquery main javascript file -->
<script src="javascript/jquery-1.7.1.js"></script>

<!-- form Validation -->
<script type="text/javascript" src="javascript/jquery.validate.js"></script>

<!-- file specific javascript -->
<script src="javascript/jquery.changeAccInfo.js"></script>

</head>
<body>
	<jsp:include page="include/header.jsp" />
	<div id="main">
		<div id="main2">
			<jsp:include page="include/sidebar.jsp" />
			<div id="content">
				<h2>Change Account Information</h2>

				<c:if test="${not empty error}"><p style="color: red;">${error}</p></c:if>

				<form name="changeForm" id="changeForm" action="ChangeAccInfoServlet" method="post">
					<div style="padding: 20px;">
						<table>
							<tr>
								<td>First name:</td>
								<td><input id="firstname" name="firstname" type="text" value="${user.firstName}"/></td>
								<td></td>
							</tr>
							<tr>
								<td>Last name:</td>
								<td><input id="surename" name="surename" type="text" value="${user.sureName}" /></td>
								<td></td>
							</tr>
							<tr>
								<td>Street:</td>
								<td><input id="address" name="address" type="text" value="${user.adress}"/></td>
								<td></td>
							</tr>
							<tr>
								<td>Password:</td>
								<td><input id="password1" name="password1" type="password" /></td>
								<td></td>
							</tr>
							<tr>
								<td>Repeat Password:</td>
								<td><input id="password2" name="password2" type="password" /></td>
								<td></td>
							</tr>
							<tr>
								<td></td>
								<td></td>
								<td><button style="width: 100px;" type="submit"
										class="submit" id="changeSubmit">Submit Change</button></td>
							</tr>
						</table>
					</div>
				</form>
				
			</div>
		</div>
		<div style="clear: both;"></div>
	</div>
	<jsp:include page="include/footer.jsp" />
</body>
</html>