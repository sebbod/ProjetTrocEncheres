<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Page de connexion</title>
<%@ include file="fragment/DefaultHead.jspf"%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/login.css">
</head>
<body>
	<img id="logo" alt="Logo du site ENI ENCHERES" src="<%=request.getContextPath()%>/img/logo.png" srcset="<%=request.getContextPath()%>/img/logo.svg">
	<div class="container">
		<form method="post" action="">
		<div class="error-container">
			<c:if test="${!empty error}">
				<div class="error">${error}</div>
			</c:if>
		</div>
		<div>
			<label class="label" for="username">Identifiant :</label>
			<input id="username" type="text" name="username" placeholder="Identifiant" value="${username}"required>
		</div>
	
		<div>
			<label class="label" for="password">Mot de passe :</label>
			<input id="password" type="password" name="password" placeholder="Mot de passe" required>
		</div>
		
		<div id="bottom-zone">
			<button class="btn-accent" type="submit">Connexion</button>
			<div id="options">
				<div class="checkbox-with-label">
					<input id="remember" type="checkbox" name="remember" value="checked">
					<label for="remember">Se souvenir de moi</label>
				</div>
				<a class="link" href="/reset-password">Mot de passe oublié</a>
			</div>
		</div>
	</form>
	<a href="<%=request.getContextPath()%>/user/register">
		<div class="btn-outline">Créer un compte</div>
	</a>
	</div>
</body>
</html>