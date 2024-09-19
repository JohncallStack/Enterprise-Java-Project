

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
 * Servlet implementation class GetOneCustServlet
 */
public class GetOneCustServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//Connection details.
			private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/empdatabase?useSSL=false";
			private static final String USERNAME = "root";
			private static final String PASSWORD = "ican't480713Jstara4!2";

			/**
			 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
			 */

			
			//CustomerDetail.jsp was throwing up an error saying it did not request get requests. Solved it by adding 
			// get and post requests to the servlet.
			@Override
		    protected void doGet(HttpServletRequest request, HttpServletResponse response)
		            throws ServletException, IOException {
		        String customerNumber = request.getParameter("custid");
		        processRequest(request, response, customerNumber);
		    }

		    @Override
		    protected void doPost(HttpServletRequest request, HttpServletResponse response)
		            throws ServletException, IOException {
		        String customerNumber = request.getParameter("custid");
		        processRequest(request, response, customerNumber);
		    }


		    //Actually connection for doPost and doGet methods. Creates connection executes SQL query, populates Java Bean with 
		    //data and sends this onto jsp to display. 
		    private void processRequest(HttpServletRequest request, HttpServletResponse response, String customerNumber)
		            throws ServletException, IOException {

				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con = null;
					java.sql.Statement stmt = null;
					con = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
					stmt = con.createStatement();
					ResultSet rs = stmt.executeQuery("SELECT * FROM customers where customerNumber = '"+ customerNumber + "'");
		
		
					if (!rs.next() ) {
			
						response.getWriter().append("2: Can't access customer details");
			
						}else {
						// If query returns results, create customer object, populate data and forward to customerDetail.jsp
			
						CustomerBean customer = new CustomerBean();
						
						customer.setCustomerNumber(rs.getString("customerNumber"));
						customer.setCustomerName(rs.getString("customerName"));
						customer.setContactLastName(rs.getString("contactLastName"));
						customer.setContactFirstName(rs.getString("contactFirstName"));
						customer.setPhone(rs.getString("phone"));
						customer.setAddressLine1(rs.getString("addressLine1"));
						customer.setAddressLine2(rs.getString("addressLine2"));
						customer.setCity(rs.getString("city"));
						customer.setState(rs.getString("state"));
						customer.setPostalCode(rs.getString("postalCode"));
						customer.setCountry(rs.getString("country"));
						customer.setSalesRepEmployeeNumber(rs.getString("salesRepEmployeeNumber"));
						customer.setCreditLimit(rs.getString("creditLimit"));
			
						// Set EmployeeBean as an attribute in request scope
			            request.setAttribute("customer", customer);
			
			            // Forward the request to allCustomers.jsp
			            RequestDispatcher dispatcher = request.getRequestDispatcher("customerDetail.jsp");
			            dispatcher.forward(request, response);
						}
					}catch (Exception e) {
						e.printStackTrace();
					}
		
			}

}
