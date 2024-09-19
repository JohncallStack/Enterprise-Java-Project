

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class GetAllCustServlet
 */
public class GetAllCustServlet extends HttpServlet {
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
		
		//Connect to Customer database and execute SQL query selecting all information from database. 
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = null;
			java.sql.Statement stmt = null;
			con = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
			stmt = con.createStatement();

			System.out.println("Making sql query from customers");
			ResultSet rs = stmt.executeQuery("SELECT * FROM customers");
			
			//Create an arraylist of type CustomerBean to store each cutomer object.
			List <CustomerBean> customerList = new ArrayList();
			
			//If query returns results, use a loop to create objects, add data to them, and add to arrayList. 
			while (rs.next() ) {
	
				System.out.println("Creating customer object and sending to allCustomers.jsp");
				//Create customer object, add the necessary variables, and add this to the arraylist. 
				CustomerBean customer = new CustomerBean();
				customer.setCustomerNumber(rs.getString("customerNumber"));
				customer.setCustomerName(rs.getString("customerName"));
				customer.setPhone(rs.getString("phone"));
				
				customerList.add(customer);
	
			}

		con.close();

		// Set EmployeeBean as an attribute in request scope
        request.setAttribute("customers", customerList);

        // Forward the request to allCustomers.jsp
        RequestDispatcher dispatcher = request.getRequestDispatcher("allCustomers.jsp");
        dispatcher.forward(request, response);

	}
		catch (Exception e) {

			e.printStackTrace();

		}

	}
}//end class
