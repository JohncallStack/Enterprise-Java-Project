

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;


@MultipartConfig(fileSizeThreshold = 1024*1024*1, //1mb
maxFileSize = 1024*1024*10, //10 mb
maxRequestSize = 1024*1024*100 //100 mb
)

/**
 * Servlet implementation class ParseXMLServlet1
 */
public class ParseXMLServlet1 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */


	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String email = null;
		String phone = null;

		// This bit of code uploads the xml file via the jsp.
		Part file = request.getPart("file");
		String filename = getFilename(file);
		InputStream filecontent = file.getInputStream();

		// File saving to the specified folder. 
		File targetFile = new File ("/Users/johnstack/Documents/File Uploads/"+filename);
		OutputStream outStream = new FileOutputStream(targetFile);
		byte [] buf = new byte [8192];
		int length;
		while ((length = filecontent.read(buf)) != -1) {
			outStream.write(buf, 0, length);
		}

		response.setContentType("text/plain");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write("File"+filename+" sucessfully uploaded");

		filecontent.reset();


		// This bit of code parses the xml file and gives a response.
		try {

			//This line has to be the uploaded file.
			File inputFile = new File("/Users/johnstack/Documents/File Uploads/"+filename);
	         DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	         DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	         Document doc = dBuilder.parse(inputFile);
	         doc.getDocumentElement().normalize();
	         NodeList nList = doc.getElementsByTagName("employee");

	         for (int temp = 0; temp < nList.getLength(); temp++) {
	            Node nNode = nList.item(temp);
	            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
	               Element eElement = (Element) nNode;

	               //Extract email and phone info
	               email = eElement.getElementsByTagName("email").item(0).getTextContent();
	               phone = eElement.getElementsByTagName("phone").item(0).getTextContent();

	               // Validate email format, see method below.
	               if (!isValidEmail(email)) {
	                   response.getWriter().append("<br>Invalid email format: " + email);
	               }

	               // Validate phone format, see method below. 
	               if (!isValidPhone(phone)) {
	                   response.getWriter().append("<br>Invalid phone format: " + phone);
	               }

	            }
	         }
	      } catch (Exception e) {
	         e.printStackTrace();
	      }
		
		
		//set the email and phone as attributes and send to AuthServlet 
		request.setAttribute("email", email);
		request.setAttribute("phone", phone);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/AuthServlet");
        dispatcher.forward(request, response);

	}

	public static String getFilename(Part part) {
		for (String cd : part.getHeader("content-disposition").split(";")) {
			if(cd.trim().startsWith("filename")) {
				String filename = cd.substring(cd.indexOf('=')+1).trim().replace("\"", "");
				return filename.substring(filename.lastIndexOf('/')+1).substring(filename.lastIndexOf('\\')+1);
			}
		}
		return null;
	}
	
	//Turn String into char array, check if it contains an @ and . symbol. Use a logical operator to return result.  
	public static boolean isValidEmail(String email) {
		boolean hasAtSymbol = false;
		boolean hasDotSymbol = false;

		char[] letters = email.toCharArray();
		for (char letter : letters) {
			if (letter == '@' ) {
				hasAtSymbol = true;
			}
			else if (letter == '.') {
				hasDotSymbol = true;
			}
		}

		 return hasAtSymbol && hasDotSymbol;

	}
	
	//To check if number is all digits, turn it into an integer (which should be a solid number with no decimel points)
	//and if this integer modulus 1 is equal to 0 (ie it's divisible by one) then it should be all digits. 
	public static boolean isValidPhone(String phone) {
		boolean isValidPhone = false;
		int intPhone = Integer.parseInt(phone);

		if  (intPhone % 1 == 0) {
			isValidPhone = true;
		}
		else {
			isValidPhone = false;
		}

		 return isValidPhone;
	}

}
