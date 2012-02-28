package paybar.webservlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.*;

/**
 * Servlet implementation class LogoutServlet
 * invalidates the session of a user and forwards
 * to the login page
 */
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession().invalidate();
		response.sendRedirect("index.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

}
