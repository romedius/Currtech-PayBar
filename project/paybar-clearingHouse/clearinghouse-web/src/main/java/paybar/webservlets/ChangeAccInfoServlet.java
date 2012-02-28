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

/**
 * ChangeAccInfoServlet
 * Servlet responsible for changing the detail account 
 * data of a user. It receives the new data from a form.
 * First it will load the actual Detail account object of
 * the user and then override with the new parameters.
 */

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
		// check whether user is logged in and parameters are passed
		if (da != null && request.getParameter("firstname") != null
				&& request.getParameter("surename") != null
				&& request.getParameter("address") != null) {
			
			// read out new parameters
			String firstname = request.getParameter("firstname");
			String surename = request.getParameter("surename");
			String address = request.getParameter("address");
			String phoneNumber = request.getParameter("phoneNumber");
			
			try {
				// get old account object
				DetailAccount daNew = dar.getUserByName(da.getUserName(), false);
				// check if password has also to be changed
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
						// password not equal, is also proved by Javascript client side
						request.setAttribute("error", "Password not equal");
						RequestDispatcher dispatcher = request
								.getRequestDispatcher("changeAccInfo.jsp");
						dispatcher.forward(request, response);
					}
				// if password don't has to be changed it is ""
				} else if (request.getParameter("password1") != null
						&& request.getParameter("password1").equals("")
						&& request.getParameter("password2") != null
						&& request.getParameter("password2").equals("")) {

					// change Account data without password
					daNew.setFirstName(firstname);
					daNew.setSureName(surename);
					daNew.setAdress(address);
					daNew.setPhoneNumber(phoneNumber);

				} else {
					// some unsopported case, something is wrong with parameters
					request.setAttribute("error",
							"Not right parameters or password does not match");
					RequestDispatcher dispatcher = request
							.getRequestDispatcher("changeAccInfo.jsp");
					dispatcher.forward(request, response);
				}
				// update the parameters persistently
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
			// user not logged in
			request.getSession().invalidate();
			response.sendRedirect("index.jsp");
		}
	}

}
