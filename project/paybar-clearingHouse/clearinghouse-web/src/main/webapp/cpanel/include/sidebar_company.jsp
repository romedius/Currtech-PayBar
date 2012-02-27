<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div id="sidebar">
	<div class="box">
		<p>
			You are logged in as:<br />
			<b><c:out value="${partner.userName}" /></b>
		</p>
	</div>

	<h2>Menu</h2>
	<ul>
		<li><a href="main_company.jsp">Home</a></li>
		<li><a href="accoverview_company.jsp">Account Overview</a></li>
		<li><a href="PartnerTransactionServlet">Transaction Overview</a></li>
		<li><a href="LogoutServlet">Logout</a></li>
	</ul>
</div>