<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Vendre un article</title>
<%@ include file="fragment/DefaultHead.jspf"%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/bid-details.css">
</head>
<body>
	<%@include file="./fragment/Header.jspf" %>
	<h1 id="page-title">Nouvelle vente</h1>
	<div class="container">
		<img id="product-image-viewer" src="">
		<form id="sell-form">
			<div class="input-container">
				<label for="product-name">Article :</label>
				<input id="product-name" name="product-name" type="text" maxlength="30" required>
			</div>
			<div class="input-container">
				<label for="product-description">Description :</label>
				<textarea id="product-description" name="product-description" maxlength="300" required></textarea>
			</div>
			<div class="input-container">
				<label for="product-category">Catégorie :</label>
				<select>
					
				</select>
			</div>
			<div class="input-container">
				<label for="product-image">Photo de l'article :</label>
				<input id="product-image" name="product-image" type="file" accept="image/png, image/jpeg" disabled>
			</div>
			<div class="input-container">
				<label for="product-price">Mise à prix :</label>
				<input id="product-price" name="product-price" type="number" min="1" required>
			</div>
			<div class="input-container">
				<label for="product-begin-date">Début de l'enchère :</label>
				<!-- Default +6 days -->
				<input id="product-begin-date" name="product-begin-date" type="datetime-local" required>
			</div>
			<div class="input-container">
				<label for="product-end-date">Fin de l'enchère :</label>
				<!-- Default +22 days -->
				<input id="product-end-date" name="product-end-date" type="datetime-local" required>
			</div>
			<div class="input-group">
				<p class="head">Retrait</p>
				<div class="input-container">
					<label for="pickup-street">Rue :</label>
					<input id="pickup-street" name="pickup-street" type="text" maxlength="30" required>
				</div>
				<div class="input-container">
					<label for="pickup-zip">Code postal :</label>
					<input id="pickup-zip" name="pickup-zip" type="text" maxlength="5" required>
				</div>
				<div class="input-container">
					<label for="pickup-town">Ville :</label>
					<input id="pickup-town" name="pickup-town" type="text" maxlength="30" required>
				</div>
			</div>
			<div id="form-buttons">
				<div class="btn-accent">Enregistrer</div>
				<div class="btn-outline">Annuler</div>
				<div class="btn-danger">Annuler la vente</div>
			</div>
		</form>
	</div>
	<%@include file="./fragment/Footer.jspf" %>
</body>
</html>