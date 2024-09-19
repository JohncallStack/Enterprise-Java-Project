
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AuthServlet
 */
public class AuthServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//Connection details.
		private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/empdatabase?useSSL=false";
		private static final String USERNAME = "root";
		private static final String PASSWORD = "ican't480713Jstara4!2";

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//Receive the email and phone from ParseXMLServlet
		String email = (String) request.getAttribute("email");
		String phone = (String) request.getAttribute("phone");
		System.out.println(email);
		System.out.println(phone);

		//Connect to database and execute SQL query. 
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = null;
			java.sql.Statement stmt = null;
			con = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT empID FROM emp where email='"+ email +"'and phone='"+ phone +"'");


		if (!rs.next()) {
			response.getWriter().append("1: ERROR, Login Details are not in Database");
			}

		else
		{
			// If authenticated, move to GetAllCustServlet (Challenge 02 #3)
            // Forward the request to GetDataServlet
			System.out.println("Found in Database");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/GetAllCustServlet");
            dispatcher.forward(request, response);
		}
			con.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}

	}

}
