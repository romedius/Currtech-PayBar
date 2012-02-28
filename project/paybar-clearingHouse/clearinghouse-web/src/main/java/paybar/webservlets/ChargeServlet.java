package paybar.webservlets;

import java.io.IOException;
import java.util.Date;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import paybar.data.DetailAccountResource;
import paybar.data.PartnerResource;
import paybar.data.PaybarResourceException;
import paybar.data.TransactionResource;
import paybar.model.DetailAccount;
import at.ac.uibk.paybar.messages.Configuration;

/**
 * ChargeServlet
 * Servlet responsible for charging the credit of
 * a user.
 * 
 */

@WebServlet(name = "ChargeServlet", urlPatterns = { "/cpanel/ChargeServlet" })
public class ChargeServlet extends HttpServlet {

	@Inject
	private PartnerResource pr;

	@Inject
	private TransactionResource tr;

	@Inject
	private DetailAccountResource dar;

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("creditcard") != null
				&& request.getParameter("amount") != null) {
			try {
				Double d = Double.parseDouble(request.getParameter("amount")) * 100;
				long amount = d.longValue();
				DetailAccount da = (DetailAccount) request.getSession()
						.getAttribute("user");
				Date now = new Date();
				tr.createChargeTransaction(
						amount,
						"Charging Account from Credit Card: "
								+ request.getParameter("creditcard"),
						Configuration.BankPosName.toString(), da.getUserName(),
						now);
				request.setAttribute("message",
						"The Ammount has ben charged to your Account");
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("charge.jsp");
				dispatcher.forward(request, response);
				da.setCredit(dar.getUserByName(da.getUserName(), false)
						.getCredit());
			} catch (Exception e) {
				e.printStackTrace();
				if (e.getCause() != null
						&& e.getCause().getClass()
								.equals(PaybarResourceException.class)) {
					request.setAttribute("error", e.getCause().getMessage());
				} else {
					if (e.getCause() != null
							&& e.getCause().getClass()
									.equals(PaybarResourceException.class)) {
						request.setAttribute("error", e.getCause().getMessage());
					} else {
						request.setAttribute("error",
								"Service temporary not available! Maybe db is not running!"
										+ e.getMessage());
					}
				}
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("charge.jsp");
				dispatcher.forward(request, response);
			}
		}
	}

}
