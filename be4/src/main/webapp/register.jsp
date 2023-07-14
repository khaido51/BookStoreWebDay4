<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<style>
* {
	box-sizing: border-box;
}

.reg {
	float: left;
	width: 20%;
	padding: 10px;
}

label {
	display: inline-block;
	width: 110px;
}

input {
	padding: 5px 10px;
	margin-bottom: 5px;
}
</style>
<script src="js/md5.min.js"></script>
</head>
<script>
	function submitRegister(){
		var password = document.getElementById('password').value;
		var encryptedPassword = md5(password);
		document.getElementById('password').value = encryptedPassword;
		
		document.getElementById('registrationForm').submit();
	}
</script>
<h2>Please Register Your Account</h2>
<body>

	<hr>
	<c:forEach var="mess" items="${errorMessages}">
		${mess } <br>
	</c:forEach>
	${errorMessage}
	<hr>
	<div class="reg">
		<form id="registrationForm" action="register" method="POST">
			<label>UserName</label> <input type="text" name="username" id="username"> <br>
			<label>Password</label> <input type="password" name="password" id="password"><br>
			<label>Email</label> <input type="text" name="email"> <br>
			<input type="submit" onclick="submitRegister()" value="Register">
		</form>
	</div>
</body>
</html>