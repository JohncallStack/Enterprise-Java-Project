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

<table style = "width:400px;" class="center">
	
	<form method="post" action="/Assignment4/ParseXMLServlet1" enctype="multipart/form-data">
		<tr>
			
			<td>
				<h3>Please Upload your XML Authentification File</h1> 
			</td>
		</tr>
		<tr>	
			<td>
				<input type="file" name="file" required/>	
				<input type="submit" value="Submit Your File"/>			
			</td>
		</tr>

	</form>
</table>

</body>
</html>