package paybar.webservlets;

import java.io.IOException;

import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import paybar.data.DetailAccountResource;
import paybar.model.DetailAccount;

;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {

	@Inject
	private DetailAccountResource dar;

	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("username") != null
				&& request.getParameter("password") != null) {
			try {
				DetailAccount da = dar.getUserByName(
						request.getParameter("username"), false);
				if (da.getPassword().equals(request.getParameter("password"))) {
					request.getSession().setAttribute("user", da);
					response.sendRedirect("main.jsp");
				} else {
					request.setAttribute("error",
							"Password or username was not correct!");
					RequestDispatcher dispatcher = request
							.getRequestDispatcher("index.jsp");
					dispatcher.forward(request, response);
				}
			} catch (Exception e) {
				e.printStackTrace();
				if (e.getCause().getClass().equals(NoResultException.class)) {
					request.setAttribute("error",
							"Password or username was not correct!");
				} else {
					request.setAttribute("error",
							"Service temporary not available! Maybe db is not running!");
				}
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("index.jsp");
				dispatcher.forward(request, response);
			}
		}
	}

}
