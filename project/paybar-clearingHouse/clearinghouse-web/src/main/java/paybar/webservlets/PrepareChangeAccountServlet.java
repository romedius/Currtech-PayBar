package paybar.webservlets;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import paybar.data.DetailAccountResource;
import paybar.data.TransactionResource;
import paybar.model.DetailAccount;
import paybar.model.Partner;
import paybar.model.Transaction;


@WebServlet(name="PrepareChangeAccountServlet",
urlPatterns={"/cpanel/PrepareChangeAccountServlet"}) 
public class PrepareChangeAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Inject
	private DetailAccountResource dar;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		DetailAccount da = (DetailAccount) request.getSession().getAttribute(
				"user");
		if (da != null) {
				DetailAccount daOverview;
				try {
					daOverview = dar.getUserByName(da.getUserName(), false);
					request.setAttribute("daOverview", daOverview);
				} catch (Exception e) {
					request.setAttribute("error", "There was an error in loading the account data!");
					e.printStackTrace();
				}
				
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("changeAccInfo.jsp");
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
