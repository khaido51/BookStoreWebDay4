<%@page import="java.util.List"%>
<%@page import="coding.mentor.service.CategoryService"%>
<%@page import="coding.mentor.entity.Category"%>
<%@page import="java.util.Date"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Home</title>
<style>
* {
	box-sizing: border-box;
}

.sub-body-category {
	float: left;
	width: 20%;
	padding: 10px;
}

.sub-body-product {
	float: left;
	width: 80%;
	padding: 10px;
}

.register {
	float: right;
}

.login {
	float: right;
	margin-right: 10px;
}

.body:after {
	content: "";
	display: table;
	clear: both;
}
</style>
</head>


<body>

	<div class="header">
		<form action="home">
			<input type="text" name="bookName"
				value="${empty param.bookName ? '' : param.bookName }"> <input
				type="submit" value="Search">
		</form>


		<div class="register">
			<a href="register.jsp">Register</a>
		</div>

		<div class="login">
			<c:if test="${empty sessionScope.username}">	
				<a href="login.jsp">Login</a>
			</c:if>
			<c:if test="${not empty sessionScope.username}">
				Hello ${sessionScope.username }
				<br>
				<a href="logout">Logout</a>
			</c:if>
			<a href="cart?command=VIEW_CART">Cart(${empty sessionScope.cart? 0 : sessionScope.cart.size()})</a>



		</div>
	</div>


	<hr>
	<div class="body">
		<div class="sub-body-category">
			<ul>
				<c:forEach var="category" items="${categoryList}">
					<a href="home?categoryId=${category.id}">
						<li>${category.name}</li>
					</a>
				</c:forEach>

			</ul>
		</div>
		<div class="sub-body-product">
			<ul>
				<c:forEach var="book" items="${bookList}">
					<a href="book?bookId=${book.id}">
						<li>${book.name}</li>
					</a>
				</c:forEach>
			</ul>
		</div>


	</div>

	<hr>
	<div class="footer">
		<h2>This is coding mentor Website for be4</h2>
	</div>


</body>
</html>