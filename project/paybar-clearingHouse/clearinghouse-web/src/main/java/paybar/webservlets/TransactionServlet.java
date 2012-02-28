package paybar.webservlets;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import paybar.data.TransactionResource;
import paybar.model.DetailAccount;
import paybar.model.Transaction;

/**
 * Servlet implementation class TransactionServlet
 * This Controller Servlet prepares all transactions
 * that are done by the user.
 * 
 */

public class TransactionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Inject
	private TransactionResource trr;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		DetailAccount da = (DetailAccount) request.getSession().getAttribute(
				"user");
		// check if user is logged in
		if (da != null) {
			// get transactions from the user
			List<Transaction> transactionList = trr
					.getTransactionsByUsername(da.getUserName());

			request.setAttribute("transactions", transactionList);
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("transactions.jsp");
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
