package paybar.webservlets;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import paybar.data.DetailAccountResource;
import paybar.model.DetailAccount;

@WebServlet(name = "ChangeAccInfoServlet", urlPatterns = { "/cpanel/ChangeAccInfoServlet" })
public class ChangeAccInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Inject
	private DetailAccountResource dar;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		DetailAccount da = (DetailAccount) request.getSession().getAttribute(
				"user");
		if (da != null && request.getParameter("firstname") != null
				&& request.getParameter("surename") != null
				&& request.getParameter("address") != null) {
			String firstname = request.getParameter("firstname");
			String surename = request.getParameter("surename");
			String address = request.getParameter("address");
			String phoneNumber = request.getParameter("phoneNumber");
			
			try {
				DetailAccount daNew = dar.getUserByName(da.getUserName(), false);
				daNew = dar.getUserByName(da.getUserName(), false);
				if (request.getParameter("password1") != null
						&& !request.getParameter("password1").equals("")
						&& request.getParameter("password2") != null
						&& !request.getParameter("password2").equals("")) {
					// change also new password if they are equal
					if (request.getParameter("password1").equals(
							request.getParameter("password2"))) {
						
						daNew.setFirstName(firstname);
						daNew.setSureName(surename);
						daNew.setAdress(address);
						daNew.setPhoneNumber(phoneNumber);
						daNew.setPassword(request.getParameter("password1"));
					
					} else {
						request.setAttribute("error", "Password not equal");
						RequestDispatcher dispatcher = request
								.getRequestDispatcher("changeAccInfo.jsp");
						dispatcher.forward(request, response);
					}
				} else if (request.getParameter("password1") != null
						&& request.getParameter("password1").equals("")
						&& request.getParameter("password2") != null
						&& request.getParameter("password2").equals("")) {
					// todo
					// change Account data without password
					daNew.setFirstName(firstname);
					daNew.setSureName(surename);
					daNew.setAdress(address);
					daNew.setPhoneNumber(phoneNumber);
					
					// if successfully written also change data in session
					// object

				} else {
					request.setAttribute("error",
							"Not right parameters or password does not match");
					RequestDispatcher dispatcher = request
							.getRequestDispatcher("changeAccInfo.jsp");
					dispatcher.forward(request, response);
				}
				dar.updateDetailAccount(daNew);
				response.sendRedirect("AccountOverviewServlet");
				
			}  catch (Exception e) {
				// TODO Auto-generated catch block
				request.setAttribute("error",
						"Error in transaction occured");
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("changeAccInfo.jsp");
				dispatcher.forward(request, response);
				
				e.printStackTrace();
			}

		} else {
			request.getSession().invalidate();
			response.sendRedirect("index.jsp");
		}
	}

}
