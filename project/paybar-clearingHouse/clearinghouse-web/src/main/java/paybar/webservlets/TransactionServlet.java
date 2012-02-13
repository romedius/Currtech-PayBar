package paybar.webservlets;

import java.io.IOException;
import java.sql.*;
import java.util.Vector;

import javax.servlet.*;
import javax.servlet.http.*;

import paybar.model.DetailAccount;
import paybar.model.PointOfSale;
import paybar.model.Transaction;

/**
 * Servlet implementation class TransactionServlet
 */
public class TransactionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DetailAccount da = (DetailAccount) request.getSession().getAttribute("user");
		if (da != null) {
			Vector<Transaction> transactionvector = new Vector<Transaction>();
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection conn = DriverManager.getConnection
						("jdbc:mysql://localhost:3306/jboss", "jboss", "123produktpolizei");
				String query = "select t.id, t.amount, t.transactionTime, p.name from detailaccount d, transaction t, pointofsale p where " +
						"t.detailAccount_id = " + da.getId() + " and t.pos_id = p.id;";
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(query);
				while (rs.next()){
					Transaction t = new Transaction();
					t.setId(rs.getLong(1));
					t.setAmount(rs.getDouble(2));
					t.setTransactionTime(rs.getLong(3));
					PointOfSale pos = new PointOfSale();
					pos.setName(rs.getString(4));
					t.setPos(pos);
					transactionvector.add(t);
				}
				// append hotelVector and redirect to view
				request.setAttribute("transactions", transactionvector);
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("transactions.jsp");
				dispatcher.forward(request, response);
			} catch (Exception e) {
				request.setAttribute("error", "Some error happened during database transaction, make sure that db is running!");
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("transactions.jsp");
				dispatcher.forward(request, response);
			}
			
		} else {
			request.getSession().invalidate();
			response.sendRedirect("index.jsp");
		}		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
