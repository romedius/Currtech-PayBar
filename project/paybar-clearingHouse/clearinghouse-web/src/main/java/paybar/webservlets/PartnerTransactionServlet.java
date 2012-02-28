package paybar.webservlets;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import paybar.data.TransactionResource;
import paybar.model.Partner;
import paybar.model.Transaction;

/**
 * PartnerTransactionServlet
 * Controller that prepares the Transactions
 * that belong the that company
 */
@WebServlet(name="PartnerTransactionServlet",
urlPatterns={"/cpanel/PartnerTransactionServlet"}) 
public class PartnerTransactionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Inject
	private TransactionResource trr;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Partner p = (Partner) request.getSession().getAttribute(
				"partner");
		// check if partner is logged in
		if (p != null) {
				List<Transaction> transactionList = trr
						.getTransactionsByCompanyName(p.getUserName());

				request.setAttribute("transactions", transactionList);
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("transactions_company.jsp");
				dispatcher.forward(request, response);
		} else {
			// user not logged in, forward to
			request.getSession().invalidate();
			response.sendRedirect("login_company.jsp");
		}
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
