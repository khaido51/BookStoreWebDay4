<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Book Details Page</title>
</head>
<body>
<li> id : ${book.id} </li>
<li>name : ${book.name}</li>
<li>title : ${book.title} </li>
<li>author : ${book.author} </li>
<li>stock : ${book.stock} </li>

<br>
<input type="button" value="Add to cart"
	onclick="window.location.href='cart?command=ADD_TO_CART&bookId=${book.id}'"/>
</body>
</html>