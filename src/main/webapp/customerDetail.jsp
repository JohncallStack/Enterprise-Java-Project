<!-- Webpage to display full details of a particular Customer. Inline and External CSS styling applied.
 -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<link href="CustomerStylesheet.css" rel="stylesheet">

</head>
<body>

<h2 class="header" >Full Customer Information</h2>

<table class="center">

		<tr>
			<td style = font-weight:bold>Customer ID</td>			
			<td style = font-weight:bold>Company Name</td>	
			<td style = font-weight:bold>Phone Number</td>
			<td style = font-weight:bold>Contact Name</td>
			<td style = font-weight:bold>Address</td>
			<td style = font-weight:bold>Sales Rep Employee Number</td>
	 		<td style = font-weight:bold>Credit Limit</td>
		</tr>
		
		<tr>
			<td>${customer.customerNumber}</td>	
			<td>${customer.customerName}</td>			
			<td>${customer.phone}</td>	
			<td>${customer.contactFirstName} ${customer.contactLastName}</td>
			<td>${customer.addressLine1} ${customer.addressLine2}, ${customer.city}, ${customer.state} ${customer.postalCode}, ${customer.country}</td>
			<td>${customer.salesRepEmployeeNumber}</td>
			<td>${customer.creditLimit}</td>
		</tr>
	
	</table>


</body>
</html>