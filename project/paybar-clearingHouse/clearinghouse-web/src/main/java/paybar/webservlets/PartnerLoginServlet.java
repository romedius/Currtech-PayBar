package paybar.webservlets;

import java.io.IOException;

import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import paybar.data.PartnerResource;
import paybar.model.Partner;

/**
 * PartnerLoginServlet
 * Login page for the Partner domain
 */
@WebServlet(name="PartnerLoginServlet",
urlPatterns={"/cpanel/PartnerLoginServlet"}) 
public class PartnerLoginServlet extends HttpServlet {

	@Inject
	private PartnerResource pr;

	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// check whether username and password are passed
		if (request.getParameter("username") != null
				&& request.getParameter("password") != null) {
			try {
				Partner p = pr.getPartnerByName(
						request.getParameter("username"), false);
				// check whether password and user match
				if (p.getPassword().equals(request.getParameter("password"))) {
					request.getSession().setAttribute("partner", p);
					response.sendRedirect("main_company.jsp");
				} else {
					request.setAttribute("error",
							"Password or username was not correct!");
					RequestDispatcher dispatcher = request
							.getRequestDispatcher("login_company.jsp");
					dispatcher.forward(request, response);
				}
			} catch (Exception e) {
				e.printStackTrace();
				if (e.getCause().getClass().equals(NoResultException.class)) {
					request.setAttribute("error",
							"Password or loginname was not correct!");
				} else {
					request.setAttribute("error",
							"Service temporary not available! Maybe db is not running!");
				}
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("login_company.jsp");
				dispatcher.forward(request, response);
			}
		} else {
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("login_company.jsp");
			dispatcher.forward(request, response);
		}
	}

}
