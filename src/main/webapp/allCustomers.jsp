<!-- Webpage to display full List of Customers. Inline and External CSS styling applied.
 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>e</title>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<link href="CustomerStylesheet.css" rel="stylesheet">

</head>
<body>

<h2 class="header" >List of Customers</h2>

	<table class="center">
	
			<tr>
				<td style = font-weight:bold >More Details</td>
				<td style = font-weight:bold >Customer Number</td>			
				<td style = "font-weight:bold; width: 250px;">Customer Name</td>	
				<td style = font-weight:bold >Customer Phone</td>
				
			</tr>
			<c:forEach var="customer" items="${customers}">
			<tr>
				<td><a href= "/Assignment4/GetOneCustServlet?custid=${customer.customerNumber}">Link</a></td>	
				<td>${customer.customerNumber}</td>	
				<td style = "width:250px;" >${customer.customerName}</td>			
				<td>${customer.phone}</td>	
			</tr>
			</c:forEach>
		</table>
</body>
</html>