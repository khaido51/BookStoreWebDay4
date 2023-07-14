<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>View Cart</title>
</head>
<body>
	<c:if test="${not empty cart }">
		<h4>Book(s) in Cart:</h4>
		<hr>
		<c:set var="total" value="0" />
		<c:forEach var="item" items="${cart }" varStatus="status">
			<c:set var="total" value="${total + item.value.price}" />
			${status.count }: ${item.value.name }: ${item.value.price } --
			<a href="cart?command=REMOVE&bookId=${item.key }">Remove</a>


			<br>
		</c:forEach>
		total 
			<c:out value="${total }" />


	</c:if>

	<hr>
	<c:if test="${not empty orderDetails}">
		<h4>Your Order Details:</h4>
		<c:forEach var="book" items="${orderDetails}">
           <li> ${book.name} -- ${book.price}<li/> <br>
		</c:forEach>
		
		Total Price: ${totalPrice }
	</c:if>



	<c:if test="${empty cart }">
		<h4>Empty Cart</h4>

	</c:if>

	<br>
	<a href="home">Back to Home</a>
	<hr>
	<br>
	<input type="button" value="Submit Cart"
		onclick="window.location.href='cart?command=SUBMIT_CART'" />
</body>
</html>

