<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Réinitialisation du mot de passe</title>
</head>
<body>
	<img src="">
	<h1>Réinitialisation du mot de passe</h1>
	<form method="post" action="">
		<c:if test="${!empty resetMessage}">
			<div class="messsage-container">
				<p>${resetMessage}</p>
			</div>
		</c:if>
		<div class="error-container">
			<c:if test="${!empty error}">
				<div class="error">${error}</div>
			</c:if>
		</div>
		<label for="username">Identifiant :</label>
		<input type="text" name="username" placeholder="Identifiant" value="${username}"required>
		<label for="username">Identifiant :</label>
		<input type="text" name="username" placeholder="Identifiant" value="${username}"required>
	
		<div>
			<button type="submit">Réinitialiser</button>
		</div>
	</form>
</body>
</html>