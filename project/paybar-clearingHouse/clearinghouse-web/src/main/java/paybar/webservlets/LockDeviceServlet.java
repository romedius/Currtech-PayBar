package paybar.webservlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import paybar.model.DetailAccount;


@WebServlet(name="LockDeviceServlet",
urlPatterns={"/cpanel/LockDeviceServlet"}) 
public class LockDeviceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		DetailAccount da = (DetailAccount) request.getSession().getAttribute(
				"user");
		if (da != null) {
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("");
				dispatcher.forward(request, response);
		} else {
			request.getSession().invalidate();
			response.sendRedirect("index.jsp");
		}
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
