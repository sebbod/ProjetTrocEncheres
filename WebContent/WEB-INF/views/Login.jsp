<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Page de connexion</title>
</head>
<body>
	<img src="">
	<form method="post" action="">
		<div class="error-container">
			<c:if test="${!empty error}">
				<div class="error">${error}</div>
			</c:if>
		</div>
		<label for="username">Identifiant :</label>
		<input type="text" name="username" placeholder="Identifiant" value="${username}"required>
		
		<label for="password">Mot de passe :</label>
		<input type="password" name="password" placeholder="Mot de passe" required>
	
		<div>
			<button type="submit">Connexion</button>
		</div>
		<div>
			<input type="checkbox" name="remember">
			<label for="remember">Se souvenir de moi</label>
			<a href="/password-reset">Mot de passe oublié</a>
		</div>
	</form>
	<a href="<%=request.getContextPath()%>/user/register">Créer un compte</a>
</body>
</html>