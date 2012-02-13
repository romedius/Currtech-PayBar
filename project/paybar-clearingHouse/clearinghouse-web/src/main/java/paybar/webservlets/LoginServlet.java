package paybar.webservlets;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import paybar.model.DetailAccount;;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DetailAccount da = (DetailAccount)request.getSession().getAttribute("user");
		if (request.getParameter("username") != null && request.getParameter("password") != null){
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection conn = DriverManager.getConnection
						("jdbc:mysql://localhost:3306/jboss", "jboss", "123produktpolizei");
				
				String query = "select id, userName, firstName, sureName, phoneNumber, credit, password from detailaccount where " +
						"userName = '" + request.getParameter("username") + "' and password = '"+ request.getParameter("password") + "';";
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(query);
				// if result then correct user
				if (rs.next()){
					da.setId(rs.getInt(1));
					da.setUserName(rs.getString(2));
					da.setFirstName(rs.getString(3));
					da.setSureName(rs.getString(4));
					da.setPhoneNumber(rs.getString(5));
					da.setCredit(rs.getLong(6));
					response.sendRedirect("main.jsp");
				} else {
					request.setAttribute("error", "Password or username was not correct!");
					RequestDispatcher dispatcher = request
							.getRequestDispatcher("index.jsp");
					dispatcher.forward(request, response);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				request.setAttribute("error", "Service temporary not available! Maybe db is not running!");
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("index.jsp");
				dispatcher.forward(request, response);
			}
		}
	}

}
