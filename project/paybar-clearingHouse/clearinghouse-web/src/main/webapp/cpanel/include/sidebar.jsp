<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div id="sidebar">
	<div class="box">
		<p>
			You are logged in as:<br />
			<c:out value="${user.userName}" />
		</p>
	</div>

	<h2>Menu</h2>
	<ul>
		<li><a href="main.jsp">Account Overview</a></li>
		<li><a href="tranaction.jsp">Transaction Overview</a></li>
		<li><a href="LogoutServlet">logout</a></li>
	</ul>
</div>