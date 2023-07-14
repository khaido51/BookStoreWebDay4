<%@page import="coding.mentor.service.BookService"%>
<%@page import="coding.mentor.entity.Book"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>

<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h2>Welcome to Book List</h2>

	<hr>
	<ul>
		<c:forEach var="book" items="${bookList}">
			<li>${book.name}</li>
		</c:forEach>
		
	</ul>
</body>
</html>