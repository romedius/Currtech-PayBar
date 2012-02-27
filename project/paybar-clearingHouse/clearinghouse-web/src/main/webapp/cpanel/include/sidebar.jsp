<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div id="sidebar">
	<div class="box">
		<p>
			You are logged in as:<br />
			<b><c:out value="${user.userName}" /></b>
		</p>
	</div>

	<h2>Menu</h2>
	<ul>
		<li><a href="main.jsp">Home</a></li>
		<li><a href="AccountOverviewServlet">Account Overview</a></li>
		<li><a href="charge.jsp">Charge Money</a></li>
		<li><a href="TransactionServlet">Transaction Overview</a></li>
		<li><a href="lockDevices.jsp">Lock Devices</a></li>		
		<li><a href="LogoutServlet">Logout</a></li>
	</ul>
</div>